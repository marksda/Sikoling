package org.Sikoling.ejb.abstraction.service.keycloackuser;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.entity.Credential;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.ResponToken;
import org.Sikoling.ejb.abstraction.entity.SimpleResponse;

public interface IKeyCloackUserService {
	User save(User t) throws IOException;
	User update(User t);
	User updateSandi(String idLama, User t) throws IOException;
	User delete(User t) throws IOException;
	List<User> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
	Boolean cekUserName(String nama);
	ResponToken getToken(Credential userAuthenticator);
	ResponToken refreshToken(String refreshToken);
	SimpleResponse addRegistrasi(Credential userAuthenticator, Person person);
}
