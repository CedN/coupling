package cna.design.coupling.hexagonal.biz;

import java.util.List;

public interface PersonRepository {
    public void save(Person person) throws PersonAlreadyExistException;

    public List<Person> searchByLastname(String lastname);

    public Person findBySocialId(Long socialId) throws PersonUnknownException;
}
