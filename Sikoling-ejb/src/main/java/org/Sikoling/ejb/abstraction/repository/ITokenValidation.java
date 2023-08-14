package org.Sikoling.ejb.abstraction.repository;

import java.util.Map;

public interface ITokenValidation {
	Map<String, Object> validate(String jwt);
}
