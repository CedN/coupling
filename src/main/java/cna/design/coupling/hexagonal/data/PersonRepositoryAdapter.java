package cna.design.coupling.hexagonal.data;

import cna.design.coupling.hexagonal.biz.Person;
import cna.design.coupling.hexagonal.biz.PersonAlreadyExistException;
import cna.design.coupling.hexagonal.biz.PersonRepository;
import cna.design.coupling.hexagonal.biz.PersonUnknownException;

import java.util.List;
import java.util.stream.Collectors;

public class PersonRepositoryAdapter implements PersonRepository {

    private final PersonEntityDao personEntityDao;

    public PersonRepositoryAdapter(PersonEntityDao personEntityDao) {
        this.personEntityDao = personEntityDao;
    }

    @Override
    public void save(Person person) throws PersonAlreadyExistException {
        PersonEntity personEntity = new PersonEntity(person.lastname, person.firstname, person.socialId);
        try {
            personEntityDao.create(personEntity);
        } catch (PersonEntityAlreadyExistException e) {
            throw new PersonAlreadyExistException();
        }
    }

    @Override
    public List<Person> searchByLastname(String lastname) {
        List<PersonEntity> personEntities = personEntityDao.findByLastname(lastname);
        return personEntities.stream()
                .map(personEntity -> new Person(personEntity.getSocialId(), personEntity.getLastname(), personEntity.getFirstname()))
                .collect(Collectors.toList());
    }

    @Override
    public Person findBySocialId(Long socialId) throws PersonUnknownException {
        List<PersonEntity> personEntities = personEntityDao.findBySocialId(socialId);
        return personEntities.stream()
                .findFirst()
                .map(personEntity -> new Person(personEntity.getSocialId(), personEntity.getLastname(), personEntity.getFirstname()))
                .orElseThrow(PersonUnknownException::new);
    }
}
