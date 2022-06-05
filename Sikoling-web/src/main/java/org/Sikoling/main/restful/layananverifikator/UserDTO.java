package org.Sikoling.main.restful.layananverifikator;

import java.io.Serializable;

import org.Sikoling.ejb.abstraction.entity.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -7409219419826819411L;
	
	private String email;
	private String password;
	
	public UserDTO() {
		
	}
	
	public UserDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public UserDTO(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
    
    public User toUser(){
        return new User(null, this.email, this.password, null, null, null);
    }

}
