package org.Sikoling.ejb.main.repository.user;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;
import org.Sikoling.ejb.abstraction.entity.Filter;
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
import org.Sikoling.ejb.main.security.keycloack.KeycloakClient;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.core.Response;

public class KeyCloakUserJPA implements IKeyCloackUserRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;
	private final Keycloak keycloak;
	private final KeycloakClient keycloakClient;

	public KeyCloakUserJPA(KeycloakClient keycloakClient, Keycloak keycloak, EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.keycloak = keycloak;
		this.keycloakClient = keycloakClient;
		this.dataConverter = dataConverter;
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
						userRepresentation.setEmailVerified(true);
						userRepresentation.setEnabled(true);
						
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
				userRepresentation.setEmailVerified(true);
				userRepresentation.setEnabled(true);
				
				response = realmResource.users().create(userRepresentation);
				
				if (response.getStatus() != 201) {					
					throw new IOException("data autentikasi tidak bisa ditambahkan ke server identification provider");
		        }
				
				return t;
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
		AccessTokenResponse accessTokenResponse = keycloakClient.getAccessTokenResponse(t.getUserName(), t.getPassword());
		if(accessTokenResponse != null) {
			OtoritasData autorisasiData = entityManager.createNamedQuery("OtoritasData.findByUserName", OtoritasData.class)
					.setParameter("userName", t.getUserName()).getSingleResult();
			Token token = new Token(
					autorisasiData.getId(), 
					autorisasiData.getUserName(), 
					autorisasiData.getUserName(), 
					accessTokenResponse.getToken(), 
					accessTokenResponse.getRefreshToken(), 
					accessTokenResponse.getExpiresIn(), 
					autorisasiData.getHakAkses().getNama()
					);
			
			return new ResponToken("oke", token);
		}
		else {
			throw new IOException("token error");
		}
	}
	
	@Override
	public ResponToken refreshToken(String userName, String refreshToken) throws IOException {
		try {
			OtoritasData autorisasiData = entityManager.createNamedQuery("OtoritasData.findByUserName", OtoritasData.class)
					.setParameter("userName", userName).getSingleResult();
			AccessTokenResponse accessTokenResponse = keycloakClient.getAccessTokenResponseRefreshToken(refreshToken);
			
			if(accessTokenResponse != null) {
				
				Token token = new Token(
						autorisasiData.getId(), 
						autorisasiData.getUserName(), 
						autorisasiData.getUserName(), 
						accessTokenResponse.getToken(), 
						accessTokenResponse.getRefreshToken(), 
						accessTokenResponse.getExpiresIn(), 
						autorisasiData.getHakAkses().getNama()
						);
				
				return new ResponToken("oke", token);
			}
			else {
				throw new IOException("refresh token tidak berlaku");
			}
		} catch (NoResultException e) {
			throw new IOException("user tidak dikenali sistem");
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
//		Integer count = 0;
//		
//		count = entityManager.createNamedQuery("UserData.findByQueryNama", UserData.class)
//		.setParameter("nama", nama)
//		.getResultList()
//		.size();
//		
//		if(count > 0) {
//			return "local";
//		}
		
//		RealmResource realmResource = keycloak.realm("dlhk");
//		UserResource userResource = null;
//		if(id == null) {
//			List<UserRepresentation> daftarUser = realmResource.users().searchByEmail(nama, false);
//			if(!daftarUser.isEmpty()) {
//				return "remote";
//			}
//		}
//		else {
//			userResource = realmResource.users().get(id);
//			if(userResource != null) {
//				return "remote";
//			}
//		}		
			
		RealmResource realmResource = keycloak.realm("dlhk");
		List<UserRepresentation> daftarUser = realmResource.users().searchByEmail(nama, false);
		
		if(daftarUser.isEmpty()) {
			return "none";
		}
		else {
			return "remote";
		}
		
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