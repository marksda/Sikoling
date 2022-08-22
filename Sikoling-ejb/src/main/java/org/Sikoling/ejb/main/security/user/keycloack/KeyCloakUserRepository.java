package org.Sikoling.ejb.main.security.user.keycloack;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.repository.IUserRepository;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.user.UserData;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;

public class KeyCloakUserRepository implements IUserRepository {
	
	private final EntityManager entityManager;
	private final Keycloak keycloak;
	private final String realm;	

	public KeyCloakUserRepository(Keycloak keycloak, String realm, EntityManager entityManager) {
		super();
		this.keycloak = keycloak;
		this.realm = realm;
		this.entityManager = entityManager;
	}
	
	@Override
	public List<User> getAll() {		
		return keycloak
				.realm(realm)
				.users()
                .list()                
                .stream()
                .map(t -> {
					try {
						return convertUserRepresentationToUser(t);
					} catch (ParseException e) {						
						e.printStackTrace();
						return null;
					}
				})
                .collect(Collectors.toList());
	}

	@Override
	public User save(User user) {
		Response response = keycloak
				.realm(realm)
				.users()
				.create(convertUserToUserPresentatiton(user));
		
		if (response.getStatus() != 200) {
            throw new IllegalArgumentException("user service " + user.getUserName() + " couldn't be saved in KeyCloak: " + response.readEntity(String.class));
        }

        return user;
	}

	@Override
	public User update(User user) {
		UserResource userResource = keycloak
                .realm(realm)
                .users()
                .get(user.getId());
        
        userResource.update(convertUserToUserPresentatiton(user));

        return user;
	}

	@Override
	public List<User> getAllByPage(Integer page, Integer pageSize) {
		return keycloak
                .realm(realm)
                .users()
                .list((page - 1) * pageSize, pageSize)
                .stream()
                .map(t -> {
					try {
						return convertUserRepresentationToUser(t);
					} catch (ParseException e) {
						e.printStackTrace();
						return null;
					}
				})
                .collect(Collectors.toList());
	}

	@Override
	public List<User> getByQueryNama(String nama) {
		return keycloak
                .realm(realm)
                .users()
                .search(nama)
                .stream()
                .map(t -> {
					try {
						return convertUserRepresentationToUser(t);
					} catch (ParseException e) {
						e.printStackTrace();
						return null;
					}
				})
                .collect(Collectors.toList());
	}

	@Override
	public List<User> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return keycloak
                .realm(realm)
                .users()
                .search(nama, (page - 1) * pageSize, pageSize)
                .stream()
                .map(t -> {
					try {
						return convertUserRepresentationToUser(t);
					} catch (ParseException e) {
						e.printStackTrace();
						return null;
					}
				})
                .collect(Collectors.toList());
	}

	private UserRepresentation convertUserToUserPresentatiton(User user) {
		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setId(user.getId());
		userRepresentation.setEmail(user.getUserName());
        userRepresentation.setUsername(user.getUserName());
        userRepresentation.setFirstName(user.getPerson().getNama());
        userRepresentation.setEnabled(user.getStatusEnable());        
        
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("statusInternal", Arrays.asList(user.getStatusInternal().toString()));  
        attributes.put("registerDate", Arrays.asList(user.getRegisterDate().toString()));
        attributes.put("nik", Arrays.asList(user.getPerson().getNik()));        
 
        userRepresentation.setAttributes(attributes);
        
//      CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
//		credentialRepresentation.setTemporary(false);
//		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
//		credentialRepresentation.setValue(user.getPassword());
//		
//		userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));		
        
		return userRepresentation;
	}
	
	private User convertUserRepresentationToUser(UserRepresentation userRepresentation) throws ParseException {
		
		PersonData data = entityManager.find(PersonData.class, getAttribute(userRepresentation.getAttributes(), "nik"));
		Person person = new Person(
				data.getId(), data.getNama(),
				new JenisKelamin(data.getSex().getId(), data.getSex().getNama()),
				new Alamat(
						new Propinsi(data.getAlamat().getPropinsi().getId(), data.getAlamat().getPropinsi().getNama()), 
						new Kabupaten(data.getAlamat().getKabupaten().getId(), data.getAlamat().getKabupaten().getNama()),
						new Kecamatan(data.getAlamat().getKecamatan().getId(), data.getAlamat().getKecamatan().getNama()), 
						new Desa(data.getAlamat().getDesa().getId(), data.getAlamat().getDesa().getNama()), 
						data.getAlamat().getDetailAlamat()), 
				data.getScanKtp(),
				new Kontak(data.getKontak().getTelepone(), null, data.getKontak().getEmail()));
		
		
        User user = new User(
        		userRepresentation.getId(),
        		userRepresentation.getEmail(),
        		person,
        		new SimpleDateFormat("dd/MM/yyyy").parse(getAttribute(userRepresentation.getAttributes(), "registerDate")),
        		Boolean.valueOf(getAttribute(userRepresentation.getAttributes(), "statusInternal")),
        		userRepresentation.isEnabled()
        		);
       
		return user;
	}
	
	private String getAttribute(Map<String, List<String>> attributes, String name) {
        return Optional.ofNullable(attributes)
                .map(att -> att.get(name))
                .orElse(Collections.emptyList())
                .stream()
                .findFirst()
                .orElse("");
    }
	
	@Override
	public Boolean cekUserName(String nama) {
		
		if(cekModelAuthentication(nama) == "none") {
			return false;
		}
		else {
			return true;
		}
		
//		Integer count = keycloak
//				.realm(realm)
//				.users()
//				.count(nama);		
		
//		List<CredentialRepresentation> credentialRepresentation = keycloak
//				.realm(realm)
//				.users().get(nama).credentials();
		
//		if(count > 0) {
//			return true;
//		}
//		else {
//			return false;
//		}
		
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

	private Boolean cekPassword(String nama, String password) {
		String pwd = "";
		String modelAuthentication = cekModelAuthentication(nama);
		
		if( modelAuthentication == "none") {
			return false;
		}
		else if(modelAuthentication == "local") {
//			Query q;
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
					return false;
				}
				else {
					return true;					
				}
				
//				q = entityManager.createNativeQuery(
//					"SELECT * FROM "
//				);
//				q.setParameter(1, id);
//	            Object results = q.getSingleResult();	            
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return false;
			}			
		}
		else {
			//request token
			return true;
		}
	}
}