package cna.design.coupling.delegate.biz;

import cna.design.coupling.delegate.biz.Person;
import cna.design.coupling.delegate.biz.PersonAlreadyExistException;
import cna.design.coupling.delegate.biz.PersonUnknownException;
import cna.design.coupling.delegate.data.PersonEntity;
import cna.design.coupling.delegate.data.PersonEntityAlreadyExistException;
import cna.design.coupling.delegate.data.PersonEntityDao;

import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceDelegate {

    private final PersonEntityDao personEntityDao;

    public PersonServiceDelegate(PersonEntityDao personEntityDao) {
        this.personEntityDao = personEntityDao;
    }

    public void savePerson(Person person) throws PersonAlreadyExistException {
        PersonEntity personEntity = new PersonEntity(person.lastname, person.firstname, person.socialId);
        try {
            personEntityDao.create(personEntity);
        } catch (PersonEntityAlreadyExistException e) {
            throw new PersonAlreadyExistException();
        }
    }

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