package org.Sikoling.ejb.main.security.user.keycloack;

import java.io.IOException;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import org.Sikoling.ejb.main.repository.DataConverter;
import org.Sikoling.ejb.main.repository.hakakses.HakAksesData;
import org.Sikoling.ejb.main.repository.otoritas.OtoritasData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.user.UserData;
import org.apache.http.client.HttpClient;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;

public class KeyCloakUserJPA implements IKeyCloackUserRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;
	private final Keycloak keycloak;
	private final HttpClient client;
	private final Properties properties;

	public KeyCloakUserJPA(Keycloak keycloak, EntityManager entityManager, HttpClient client, DataConverter dataConverter, Properties properties) {
		this.entityManager = entityManager;
		this.keycloak = keycloak;
		this.client = client;
		this.dataConverter = dataConverter;
		this.properties = properties;
	}
	
	@Override
	public User save(User t) throws IOException {
		RealmResource realmResource = keycloak.realm("dlhk");
		Response response = null;
		UserRepresentation userRepresentation = null;
		CredentialRepresentation credentialRepresentation = null;
		
		switch (cekModelAuthentication(t.getPerson().getNik(), t.getCredential().getUserName())) {
			case "local": {	//penambahan user lama ke server keycloack -> migrasi user
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
						
						response = realmResource.users().create(userRepresentation);
						
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
				
				response = realmResource.users().create(userRepresentation);
				
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
		RealmResource realmResource = keycloak.realm("dlhk");
		UserResource userResource = realmResource.users().get(t.getPerson().getNik());		
		UserRepresentation userRepresentation = dataConverter.convertUserToUserRepresentationKeyCloack(t);
		userResource.update(userRepresentation);	
		
        return t;
	}
		
	@Override
	public User updateSandi(String sandiLama, User t) throws IOException {
		
		RealmResource realmResource = keycloak.realm("dlhk");
		UserResource userResource = realmResource.users().get(t.getPerson().getNik());	
		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
		credentialRepresentation = new CredentialRepresentation();
	 	credentialRepresentation.setTemporary(false);
		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
		credentialRepresentation.setValue(t.getCredential().getPassword());
		userResource.resetPassword(credentialRepresentation);

		return t;
	}
	
	@Override
	public Boolean cekUserName(String nama) {		
		if(cekModelAuthentication(null, nama) == "none") {
			return false;
		}
		else {
			return true;
		}		
	}

	@Override
	public ResponToken getToken(Credential t) throws IOException {
		 Map<String, Object> clientCredential = new HashMap<String, Object>();
		 clientCredential.put("grant_type", OAuth2Constants.CLIENT_CREDENTIALS);
		 clientCredential.put("client_secret", properties.getProperty("SSO_CLIENT_SECRET"));
		 
		 Configuration configuration = new Configuration(
				properties.getProperty("SSO_AUTH_URL"), 
				properties.getProperty("SSO_REALM"), 
				properties.getProperty("SSO_CLIENT_ID"), 
				clientCredential, 
				client);
		 
		AuthzClient authzClient = AuthzClient.create(configuration);
		AuthorizationRequest request = new AuthorizationRequest();
		try {
			AuthorizationResponse response = authzClient.authorization(t.getUserName(), t.getPassword()).authorize(request);
			if(response != null) {
				OtoritasData autorisasiData = entityManager.createNamedQuery("OtoritasData.findByUserName", OtoritasData.class)
						.setParameter("userName", t.getUserName()).getSingleResult();
				Token token = new Token(
						autorisasiData.getId(), 
						autorisasiData.getUserName(), 
						autorisasiData.getUserName(), 
						response.getToken(), 
						response.getRefreshToken(), 
						response.getExpiresIn(), 
						autorisasiData.getHakAkses().getNama()
						);
				
				return new ResponToken("oke", token);
			}
	        
			throw new IOException("token error");
		}
		catch (AuthorizationDeniedException e) {
			throw new IOException("token error");
		}        
	}
	
	@Override
	public ResponToken refreshToken(String userName, String refreshToken) throws IOException {
		Map<String, Object> clientCredential = new HashMap<String, Object>();
		 clientCredential.put("grant_type", OAuth2Constants.REFRESH_TOKEN);
		 clientCredential.put("refresh_token",refreshToken);
		 clientCredential.put("client_secret", properties.getProperty("SSO_CLIENT_SECRET"));
		 
		 Configuration configuration = new Configuration(
				properties.getProperty("SSO_AUTH_URL"), 
				properties.getProperty("SSO_REALM"), 
				properties.getProperty("SSO_CLIENT_ID"), 
				clientCredential, 
				client);
		 
		AuthzClient authzClient = AuthzClient.create(configuration);
		AuthorizationRequest request = new AuthorizationRequest();
		try {
			AuthorizationResponse response = authzClient.authorization().authorize(request);
			if(response != null) {
				OtoritasData autorisasiData = entityManager.createNamedQuery("OtoritasData.findByUserName", OtoritasData.class)
						.setParameter("userName", userName).getSingleResult();
				Token token = new Token(
						autorisasiData.getId(), 
						autorisasiData.getUserName(), 
						autorisasiData.getUserName(), 
						response.getToken(), 
						response.getRefreshToken(), 
						response.getExpiresIn(), 
						autorisasiData.getHakAkses().getNama()
						);
				
				return new ResponToken("oke", token);
			}
	        
			throw new IOException("token error");
		}
		catch (AuthorizationDeniedException e) {
			throw new IOException("token error");
		}
	}
		
	@Override
	public SimpleResponse addRegistrasi(Credential credential, Person person) {	
		User t = new User(credential, person);
		RealmResource realmResource = keycloak.realm("dlhk");
		Response response = null;
		UserRepresentation userRepresentation = null;
		CredentialRepresentation credentialRepresentation = null;
		
		SimpleResponse hasil;
		
		switch (cekModelAuthentication(person.getNik(), credential.getUserName())) {
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
						userRepresentation = dataConverter.convertUserToUserRepresentationKeyCloack(t);
						userRepresentation.setCreatedTimestamp(System.currentTimeMillis()/1000);
						credentialRepresentation = new CredentialRepresentation();
					 	credentialRepresentation.setTemporary(false);
						credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
						credentialRepresentation.setValue(t.getCredential().getPassword());					
						userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));	
						
						response = realmResource.users().create(userRepresentation);						
						
						if (response.getStatus() != 201) {	//gagal					
							hasil = new SimpleResponse("gagal", "data autentikasi tidak bisa ditambahkan ke server identification provider");
				        }
						else {	
							try {
								UserData userData = entityManager.createNamedQuery("UserData.findByQueryNama", UserData.class)
										.setParameter("nama", credential.getUserName())
										.getSingleResult();								
								
								PersonData personData = dataConverter.convertPersonToPersonData(person);
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
				userRepresentation = dataConverter.convertUserToUserRepresentationKeyCloack(t);
				userRepresentation.setCreatedTimestamp(System.currentTimeMillis()/1000);
				credentialRepresentation = new CredentialRepresentation();
			 	credentialRepresentation.setTemporary(false);
				credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
				credentialRepresentation.setValue(t.getCredential().getPassword());
				userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));		
				
				response = realmResource.users().create(userRepresentation);				
				
				if (response.getStatus() != 201) {					
					hasil = new SimpleResponse("gagal", "data autentikasi tidak bisa ditambahkan ke server identification provider");
		        }
				else {	
					try {						
						PersonData personData = dataConverter.convertPersonToPersonData(person);
						
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

	private String cekModelAuthentication(String id, String nama) {
		Integer count = 0;
		
		count = entityManager.createNamedQuery("UserData.findByQueryNama", UserData.class)
		.setParameter("nama", nama)
		.getResultList()
		.size();
		
		if(count > 0) {
			return "local";
		}

		RealmResource realmResource = keycloak.realm("dlhk");
		UserResource userResource = realmResource.users().get(id);		
		
		if(userResource != null) {
			return "remote";
		}
		
		return "none";
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