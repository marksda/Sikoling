package org.Sikoling.main.restful.security;


public enum Role {
	ADMIN, PEMRAKARSA;
	
	public String toString(){
        switch(this){
        case ADMIN :
            return "ADMIN";
        case PEMRAKARSA :
            return "PEMRAKARSA";
        }
        return null;
    }
}
