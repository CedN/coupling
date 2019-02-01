package cna.design.coupling.delegate.biz;

import java.util.List;
import java.util.Objects;

/**
 * @since 1.0
 */
public class PersonService {

    private final PersonServiceDelegate personServiceDelegate;

    public PersonService(PersonServiceDelegate personServiceDelegate) {
        this.personServiceDelegate = personServiceDelegate;
    }

    // Person creation

    public Person createPerson(long socialId, String lastname, String firstname) throws PersonAlreadyExistException {
        assertPersonValues(socialId, lastname, firstname);
        Person person = new Person(socialId, lastname, firstname);
        savePerson(person);
        return person;
    }

    private void savePerson(Person person) throws PersonAlreadyExistException {
        personServiceDelegate.savePerson(person);
    }

    private void assertPersonValues(long socialId, String lastname, String firstname) {
        Objects.<Long>requireNonNull(socialId, "Le numéro de sécurité social est obligatoire");
        Objects.<String>requireNonNull(lastname, "Le nom de famille est obligatoire");
        Objects.<String>requireNonNull(firstname, "Le prénom est obligatoire");
    }

    // Person search

    public List<Person> searchByLastname(String lastname) {
        return personServiceDelegate.searchByLastname(lastname);
    }

    public Person findBySocialId(Long socialId) throws PersonUnknownException {
        return personServiceDelegate.findBySocialId(socialId);
    }


}
