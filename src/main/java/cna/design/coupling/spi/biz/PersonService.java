package cna.design.coupling.spi.biz;

import cna.design.coupling.spi.data.PersonEntity;
import cna.design.coupling.spi.data.PersonEntityAlreadyExistException;
import cna.design.coupling.spi.data.PersonEntityDao;

import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * @since 1.0
 */
public class PersonService {

    private final PersonEntityDao personEntityDao;

    public PersonService() {
        ServiceLoader<PersonEntityDao> serviceLoader = ServiceLoader.load(PersonEntityDao.class);
        personEntityDao = serviceLoader.iterator().next();
    }

    // Person creation

    public Person createPerson(long socialId, String lastname, String firstname) throws PersonAlreadyExistException {
        assertPersonValues(socialId, lastname, firstname);
        Person person = new Person(socialId, lastname, firstname);
        savePerson(person);
        return person;
    }

    private void savePerson(Person person) throws PersonAlreadyExistException {
        PersonEntity personEntity = new PersonEntity(person.lastname, person.firstname, person.socialId);
        try {
            personEntityDao.create(personEntity);
        } catch (PersonEntityAlreadyExistException e) {
            throw new PersonAlreadyExistException();
        }
    }

    private void assertPersonValues(long socialId, String lastname, String firstname) {
        Objects.<Long>requireNonNull(socialId, "Le numéro de sécurité social est obligatoire");
        Objects.<String>requireNonNull(lastname, "Le nom de famille est obligatoire");
        Objects.<String>requireNonNull(firstname, "Le prénom est obligatoire");
    }

    // Person search

    public List<Person> searchByLastname(String lastname) {
        List<PersonEntity> personEntities = personEntityDao.findByLastname(lastname);
        return personEntities.stream()
                .map(personEntity -> new Person(personEntity.getSocialId(), personEntity.getLastname(), personEntity.getFirstname()))
                .collect(Collectors.toList());
    }

    public Person findBySocialId(Long socialId) throws PersonUnknownException {
        List<PersonEntity> personEntities = personEntityDao.findBySocialId(socialId);
        return personEntities.stream()
                .findFirst()
                .map(personEntity -> new Person(personEntity.getSocialId(), personEntity.getLastname(), personEntity.getFirstname()))
                .orElseThrow(PersonUnknownException::new);


    }
}
