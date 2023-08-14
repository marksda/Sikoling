package org.Sikoling.ejb.abstraction.repository;

import java.util.Map;

public interface ITokenValidationRepository {
	Map<String, Object> validate(String jwt);
}
