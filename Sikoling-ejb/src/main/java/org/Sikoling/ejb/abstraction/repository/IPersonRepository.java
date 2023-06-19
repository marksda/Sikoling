package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.Person;

public interface IPersonRepository extends IRepository<Person> {
	Person updateId(String idLama, Person t) throws IOException;
}
