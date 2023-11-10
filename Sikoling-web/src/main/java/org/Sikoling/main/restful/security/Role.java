package org.Sikoling.main.restful.security;


public enum Role {
	ADMINISTRATOR, UMUM;
	
	public String toString(){
        switch(this){
        case ADMINISTRATOR :
            return "ADMINISTRATOR";
        case UMUM :
            return "UMUM";
        }
        return null;
    }
}
