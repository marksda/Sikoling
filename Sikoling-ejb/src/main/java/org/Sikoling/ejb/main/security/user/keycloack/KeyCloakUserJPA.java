package org.Sikoling.ejb.main.security.user.keycloack;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.ResponToken;
import org.Sikoling.ejb.abstraction.entity.SimpleResponse;
import org.Sikoling.ejb.abstraction.entity.Token;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.entity.Credential;
import org.Sikoling.ejb.abstraction.repository.IKeyCloackUserRepository;
import org.Sikoling.ejb.abstraction.service.security.ITokenValidationService;
import org.Sikoling.ejb.main.repository.DataConverter;
import org.Sikoling.ejb.main.repository.alamat.AlamatData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.hakakses.HakAksesData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.kontak.KontakData;
import org.Sikoling.ejb.main.repository.otoritas.OtoritasData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;
import org.Sikoling.ejb.main.repository.user.UserData;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class KeyCloakUserJPA implements IKeyCloackUserRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;
	private final Keycloak keycloak;
	private final String realm;	
	private final String tokenURL;
	private final Client client;
	private final ITokenValidationService tokenValidationService;

	public KeyCloakUserJPA(Keycloak keycloak, String realm, EntityManager entityManager, 
			String tokenURL, Client client, ITokenValidationService tokenValidationService, DataConverter dataConverter) {
		this.keycloak = keycloak;
		this.realm = realm;
		this.entityManager = entityManager;
		this.tokenURL = tokenURL;
		this.client = client;
		this.tokenValidationService = tokenValidationService;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public User save(User t) throws IOException {
		String statusUser = cekModelAuthentication(t.getCredential().getUserName());
		
		Response response = null;
		UserRepresentation userRepresentation = null;
		CredentialRepresentation credentialRepresentation = null;
		
		switch (statusUser) {
		case "local": {	//penambahan user lama (transisi) ke server keycloack
			String pwd = "";
			try {
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.reset();
				messageDigest.update(t.getCredential().getPassword().getBytes());
                byte[] digest = messageDigest.digest();
                StringBuilder sb = new StringBuilder();
                for (int i=0;i<digest.length;i++){
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }
                
                pwd=sb.toString(); 
                
                Integer count = entityManager.createNamedQuery("UserData.authenticationQuery", UserData.class)
						.setParameter("nama", t.getCredential().getUserName())
						.setParameter("password", pwd)
						.getResultList()
						.size();
				
				if(count == 0) {	//sandi tidak sesuai
					throw new IOException("sandi tidak sesuai");
				}
				else {	//proses pemindahan data user lama menuju ke server keycloack
					userRepresentation = dataConverter.convertUserToUserRepresentationKeyCloack(t);
					userRepresentation.setCreatedTimestamp(System.currentTimeMillis()/1000);
					credentialRepresentation = new CredentialRepresentation();
				 	credentialRepresentation.setTemporary(false);
					credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
					credentialRepresentation.setValue(t.getCredential().getPassword());					
					userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));	
					
					response = keycloak
							.realm(realm)
							.users()
							.create(userRepresentation);	
					
					if (response.getStatus() != 201) {	// data lama gagal ditambahkan		
						throw new IOException("data autentikasi tidak bisa ditambahkan ke server identification provider");
			        } else {	
						try {
							UserData userData = entityManager.createNamedQuery("UserData.findByQueryNama", UserData.class)
									.setParameter("nama", t.getCredential().getUserName())
									.getSingleResult();								

							entityManager.remove(userData);	
							entityManager.flush();
							
							return t;
						} catch (Exception e) {
							throw new IOException("malfunction");
						}
					}
				}	
			} catch (Exception e) {
				throw new IOException("sandi tidak sesuai");
			}
		}
		case "remote":
			throw new IOException("data sudah ada");
		case "none":
			userRepresentation = dataConverter.convertUserToUserRepresentationKeyCloack(t);
			userRepresentation.setCreatedTimestamp(System.currentTimeMillis()/1000);
			credentialRepresentation = new CredentialRepresentation();
		 	credentialRepresentation.setTemporary(false);
			credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
			credentialRepresentation.setValue(t.getCredential().getPassword());
			userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));			
			
//			Map<String, List<String>> attributes = new HashMap<>();
//	        attributes.put("statusInternal", Arrays.asList(user.getStatusInternal().toString()));  
//	        attributes.put("registerDate", Arrays.asList(user.getRegisterDate().toString()));
//	        attributes.put("nik", Arrays.asList(user.getPerson().getNik())); 
//	        userRepresentation.setAttributes(attributes);
			
			response = keycloak
					.realm(realm)
					.users()
					.create(userRepresentation);
			
			if (response.getStatus() != 201) {					
				throw new IOException("data autentikasi tidak bisa ditambahkan ke server identification provider");
	        }
			else {	
				try {		
					Otoritas autority = new Otoritas(
							null, 
							LocalDate.now(), 
							t.getPerson(), 
							new HakAkses("09", null, null), 
							false, 
							false, 
							t.getCredential().getUserName());
					
					OtoritasData autorisasiData = dataConverter.convertAuthorityToAutorisasiData(autority);
					entityManager.persist(autorisasiData);
					entityManager.flush();
					
					return t;
				} catch (Exception e) {
					throw new IOException("malfunction");
				}
			}
		default:
			throw new IOException("malfunction");
		}
	}

	@Override
	public User update(User t) {		
		UserResource userResource = keycloak
                .realm(realm)
                .users()
                .get(t.getCredential().getUserName());
		
		UserRepresentation userRepresentationLama = userResource.toRepresentation();
		userRepresentationLama.setFirstName(t.getPerson().getNama());
//		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
//	 	credentialRepresentation.setTemporary(false);
//		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
//		credentialRepresentation.setValue(t.getCredential().getPassword());		
//		userRepresentationLama.setCredentials(Collections.singletonList(credentialRepresentation));			
		
		userResource.update(userRepresentationLama);

        return t;
	}
	
	@Override
	public User updateSandi(String sandiLama, User t) throws IOException {
//		UserResource userResource = keycloak
//                .realm(realm)
//                .users()
//                .get(t.getCredential().getUserName());
//		
//		UserRepresentation userRepresentationLama = userResource.toRepresentation();
//		CredentialRepresentation credentialRepresentationLama = userRepresentationLama.getCredentials().get(0);
//		
//		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
//		credentialRepresentation.setTemporary(false);
//		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
//		credentialRepresentation.setValue(t.getCredential().getPassword());
		
//		if(credentialRepresentationLama.getValue() == )
		
		return null;
	}
	
	@Override
	public Boolean cekUserName(String nama) {		
		if(cekModelAuthentication(nama) == "none") {
			return false;
		}
		else {
			return true;
		}		
	}

	@Override
	public ResponToken getToken(Credential userAuthenticator) {
		
		Token token;
		String hasil = cekPassword(userAuthenticator.getUserName(), userAuthenticator.getPassword());
		if( hasil == "remote") {
			Form form = new Form()
					.param("grant_type", "password")
	                .param("client_id", "admin-cli")
	                .param("username", userAuthenticator.getUserName())
	                .param("password", userAuthenticator.getPassword());
			Response response = client.target(tokenURL)
	                .request(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
	                .accept(MediaType.APPLICATION_JSON_TYPE)
	                .post(Entity.form(form));
			
			if (response.getStatus() != 200) {
//				return new ResponToken("error", null);
	            throw new IllegalStateException("The tokens couldn't be gotten " + response.readEntity(String.class));
	        }
	
	        Map<String, String> map = response.readEntity(new GenericType<HashMap<String, String>>() { });
	        
	        Map<String, Object> claims = tokenValidationService.validate(map.get("access_token"));
	        
	        OtoritasData autorisasiData = entityManager.createNamedQuery("AutorisasiData.findByUserName", OtoritasData.class)
					.setParameter("userName", userAuthenticator.getUserName())
					.getSingleResult();
	        
	        token = new Token(
	        		autorisasiData.getId(), 
	        		getClaim(claims, "given_name"), //+ " " + getClaim(claims, "family_name"), 
	        		getClaim(claims, "email"), 
	        		map.get("access_token"), 
	        		map.get("refresh_token"), 
	        		map.get("expires_in"),
	        		autorisasiData.getHakAkses().getNama()
	    		);
	        return new ResponToken("oke", token);
			
		}
		else if(hasil == "need pid"){
			return new ResponToken("need pid", null);
		}
		else {
			return new ResponToken("error", null);
		}
        
	}
	
	@Override
	public ResponToken refreshToken(String refreshToken) {
		Form form = new Form()
                .param("client_id", "admin-cli")
                .param("grant_type", "refresh_token")
                .param("refresh_token", refreshToken);
		Response response = client.target(tokenURL)
                .request(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.form(form));
		
		if (response.getStatus() != 200) {
            throw new IllegalStateException("The tokens couldn't be gotten " + response.readEntity(String.class));
        }
		
		Map<String, String> map = response.readEntity(new GenericType<HashMap<String, String>>() { });
		Map<String, Object> claims = tokenValidationService.validate(map.get("access_token"));
		
		OtoritasData autorisasiData = entityManager.createNamedQuery("AutorisasiData.findByUserName", OtoritasData.class)
				.setParameter("userName", getClaim(claims, "email"))
				.getSingleResult();
        
		Token token = new Token(
        		autorisasiData.getId(), 
        		getClaim(claims, "given_name"), 
        		getClaim(claims, "email"), 
        		map.get("access_token"), 
        		map.get("refresh_token"),
        		map.get("expires_in"),
        		autorisasiData.getHakAkses().getNama()
    		);
        return new ResponToken("oke", token);
	}
		
	@Override
	public SimpleResponse addRegistrasi(Credential credential, Person person) {		
		SimpleResponse hasil;
		Response response;
		switch (cekModelAuthentication(credential.getUserName())) {
			case "local": 
				String pwd = "";
				try {
					MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
					messageDigest.reset();
					messageDigest.update(credential.getPassword().getBytes());
	                byte[] digest = messageDigest.digest();
	                StringBuilder sb = new StringBuilder();
	                for (int i=0;i<digest.length;i++){
	                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                    }
	                
	                pwd=sb.toString(); 
	                
	                Integer count = entityManager.createNamedQuery("UserData.authenticationQuery", UserData.class)
							.setParameter("nama", credential.getUserName())
							.setParameter("password", pwd)
							.getResultList()
							.size();
					
					if(count == 0) {
						hasil = new SimpleResponse("gagal", "password tidak sesuai");
					}
					else {
						response = keycloak
								.realm(realm)
								.users()
								.create(convertRegistrasiToUserPresentatiton(credential, person));		
						if (response.getStatus() != 201) {	//gagal					
							hasil = new SimpleResponse("gagal", "data autentikasi tidak bisa ditambahkan ke server identification provider");
				        }
						else {	
							try {
								UserData userData = entityManager.createNamedQuery("UserData.findByQueryNama", UserData.class)
										.setParameter("nama", credential.getUserName())
										.getSingleResult();								
								
								PersonData personData = convertPersonToPersonData(person);
								OtoritasData autorisasiData = new OtoritasData();
								
								HakAksesData hakAksesData = new HakAksesData();
								hakAksesData.setId("09");
								autorisasiData.setHakAkses(hakAksesData);
								autorisasiData.setStatusInternal(false);
								autorisasiData.setIsVerified(false);
								autorisasiData.setUserName(credential.getUserName());
								
								entityManager.remove(userData);	
								entityManager.persist(personData);
								entityManager.flush();
								
								
								hasil = new SimpleResponse("berhasil", "data autentifiksi berhasil ditambahkan");
							} catch (Exception e) {
								entityManager.getTransaction().rollback();
								hasil = new SimpleResponse("gagal", "data autentifiksi gagal ditambahkan");
							}
						}
					}	                
				} catch (Exception e) {
					hasil = new SimpleResponse("gagal", "password tidak sesuai");
				}		
				break;
			case "remote": 		
				hasil = new SimpleResponse("gagal", "data user sudah terdaftar di server identification provider");			
				break;
			case "none":
				response = keycloak
						.realm(realm)
						.users()
						.create(convertRegistrasiToUserPresentatiton(credential, person));
				if (response.getStatus() != 201) {					
					hasil = new SimpleResponse("gagal", "data autentikasi tidak bisa ditambahkan ke server identification provider");
		        }
				else {	
					try {						
						PersonData personData = convertPersonToPersonData(person);
						
						//data authority
						OtoritasData autorisasiData = new OtoritasData();						
						HakAksesData hakAksesData = new HakAksesData();
						hakAksesData.setId("09");
						autorisasiData.setHakAkses(hakAksesData);
						autorisasiData.setStatusInternal(false);
						autorisasiData.setIsVerified(false);
						autorisasiData.setUserName(credential.getUserName());	

						entityManager.persist(personData);
						entityManager.persist(autorisasiData);
						entityManager.flush();
						hasil = new SimpleResponse("sukses", "data autentifiksi berhasil ditambahkan");
					} catch (Exception e) {
						entityManager.getTransaction().rollback();
						hasil = new SimpleResponse("gagal", "data autentifiksi gagal ditambahkan");
					}
				}
				break;
			default:
				hasil = new SimpleResponse("gagal", "malfunction");				
			
		}	
		
		return hasil;
	}
	
	private PersonData convertPersonToPersonData(Person person) {
		PersonData personData = new PersonData();
		personData.setId(person.getNik());
		personData.setNama(person.getNama());
		
		PropinsiData propinsiData = new PropinsiData();
		propinsiData.setId(person.getAlamat().getPropinsi().getId());
		
		KabupatenData kabupatenData = new KabupatenData();
		kabupatenData.setId(person.getAlamat().getKabupaten().getId());
		
		KecamatanData kecamatanData = new KecamatanData();
		kecamatanData.setId(person.getAlamat().getKecamatan().getId());
		
		DesaData desaData = new DesaData();
		desaData.setId(person.getAlamat().getDesa().getId());
		
		AlamatData alamatPersonData = new AlamatData();
		alamatPersonData.setPropinsi(propinsiData);
		alamatPersonData.setKabupaten(kabupatenData);
		alamatPersonData.setKecamatan(kecamatanData);
		alamatPersonData.setDesa(desaData);
		alamatPersonData.setDetailAlamat(person.getAlamat().getKeterangan());
		
		personData.setAlamat(alamatPersonData);
		
		JenisKelaminData jenisKelaminData = new JenisKelaminData();
		jenisKelaminData.setId(person.getSex().getId());
		personData.setSex(jenisKelaminData);
		
		KontakData kontakPersonData = new KontakData();
		kontakPersonData.setTelepone(person.getKontak().getTelepone());
		kontakPersonData.setEmail(person.getKontak().getEmail());
		personData.setKontak(kontakPersonData);
		
		personData.setScanKtp(person.getScanKTP());
		
		return personData;
	}	
	
	private String getClaim(Map<String, Object> claims, String name){
        return Optional.ofNullable(claims.get(name))
                .map(String.class::cast)
                .orElseThrow(() -> new IllegalStateException("Claim " + name + " was expected"));
    }
	
	private String cekModelAuthentication(String nama) {
		Integer count = 0;
		
		count = entityManager.createNamedQuery("UserData.findByQueryNama", UserData.class)
		.setParameter("nama", nama)
		.getResultList()
		.size();
		
		if(count > 0) {
			return "local";
		}
		
		count = keycloak
				.realm(realm)
				.users()
				.count(nama);
		
		if(count > 0) {
			return "remote";
		}
		
		return "none";
	}

	private String cekPassword(String nama, String password) {
		String pwd = "";
		String modelAuthentication = cekModelAuthentication(nama);
		
		if( modelAuthentication == "none") {
			return "none";
		}
		else if(modelAuthentication == "local") {
			try {				
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.reset();
				messageDigest.update(password.getBytes());
                byte[] digest = messageDigest.digest();
                StringBuilder sb = new StringBuilder();
                for (int i=0;i<digest.length;i++){
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                    }
                pwd=sb.toString();                

				Integer count = entityManager.createNamedQuery("UserData.authenticationQuery", UserData.class)
						.setParameter("nama", nama)
						.setParameter("password", pwd)
						.getResultList()
						.size();
				
				if(count == 0) {
					return "none";
				}
				else {
					return "pid not found";					
				}
				
//				q = entityManager.createNativeQuery(
//					"SELECT * FROM "
//				);
//				q.setParameter(1, id);
//	            Object results = q.getSingleResult();	            
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return "none";
			}			
		}
		else {	
	        return "remote";
		}
	}
//	
//	private UserRepresentation convertUserToUserPresentatiton(User user) {
//		UserRepresentation userRepresentation = new UserRepresentation();
//		userRepresentation.setId(user.getId());
//		userRepresentation.setEmail(user.getUserName());
//        userRepresentation.setUsername(user.getUserName());
//        userRepresentation.setFirstName(user.getPerson().getNama());
//        userRepresentation.setEnabled(user.getStatusEnable());        
//        
//        Map<String, List<String>> attributes = new HashMap<>();
//        attributes.put("statusInternal", Arrays.asList(user.getStatusInternal().toString()));  
//        attributes.put("registerDate", Arrays.asList(user.getRegisterDate().toString()));
//        attributes.put("nik", Arrays.asList(user.getPerson().getNik()));        
// 
//        userRepresentation.setAttributes(attributes);
//        
//		return userRepresentation;
//	}
//	
//	private User convertUserRepresentationToUser(UserRepresentation userRepresentation) throws ParseException {
//		
//		PersonData data = entityManager.find(PersonData.class, getAttribute(userRepresentation.getAttributes(), "nik"));
//		Person person = new Person(
//				data.getId(), data.getNama(),
//				new JenisKelamin(data.getSex().getId(), data.getSex().getNama()),
//				new Alamat(
//						new Propinsi(data.getAlamat().getPropinsi().getId(), data.getAlamat().getPropinsi().getNama()), 
//						new Kabupaten(data.getAlamat().getKabupaten().getId(), data.getAlamat().getKabupaten().getNama()),
//						new Kecamatan(data.getAlamat().getKecamatan().getId(), data.getAlamat().getKecamatan().getNama()), 
//						new Desa(data.getAlamat().getDesa().getId(), data.getAlamat().getDesa().getNama()), 
//						data.getAlamat().getDetailAlamat()), 
//				data.getScanKtp(),
//				new Kontak(data.getKontak().getTelepone(), null, data.getKontak().getEmail()));
//		
//		
//        User user = new User(
//        		userRepresentation.getId(),
//        		userRepresentation.getEmail(),
//        		person,
//        		new SimpleDateFormat("dd/MM/yyyy").parse(getAttribute(userRepresentation.getAttributes(), "registerDate")),
//        		Boolean.valueOf(getAttribute(userRepresentation.getAttributes(), "statusInternal")),
//        		userRepresentation.isEnabled()
//        		);
//       
//		return user;
//	}
//	
//	private String getAttribute(Map<String, List<String>> attributes, String name) {
//        return Optional.ofNullable(attributes)
//                .map(att -> att.get(name))
//                .orElse(Collections.emptyList())
//                .stream()
//                .findFirst()
//                .orElse("");
//    }
	
	private UserRepresentation convertRegistrasiToUserPresentatiton(Credential userAuthenticator, Person person) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
		   
		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setId(person.getNik());
		userRepresentation.setEmail(person.getKontak().getEmail());
        userRepresentation.setUsername(person.getKontak().getEmail());
        userRepresentation.setFirstName(person.getNama());
        userRepresentation.setEnabled(true);  
        userRepresentation.setGroups(Arrays.asList("external"));
        
        Map<String, List<String>> attributes = new HashMap<>();
//        attributes.put("statusInternal", Arrays.asList("external"));  
        attributes.put("registerDate", Arrays.asList(dtf.format(now)));
        attributes.put("nik", Arrays.asList(person.getNik()));        
 
        userRepresentation.setAttributes(attributes);
        
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
	 	credentialRepresentation.setTemporary(false);
		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
		credentialRepresentation.setValue(userAuthenticator.getPassword());
		
		userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));		
        
		return userRepresentation;
	}

	
	@Override
	public User delete(User t) throws IOException {
		return null;
	}

	
	@Override
	public List<User> getDaftarData(QueryParamFilters queryParamFilters) {
		return null;
	}

	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return null;
	}

	
		
}