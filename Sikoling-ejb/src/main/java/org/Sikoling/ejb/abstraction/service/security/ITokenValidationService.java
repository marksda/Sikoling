package org.Sikoling.ejb.abstraction.service.security;

import java.util.Map;

public interface ITokenValidationService {
	Map<String, Object> validate(String jwt);
}
