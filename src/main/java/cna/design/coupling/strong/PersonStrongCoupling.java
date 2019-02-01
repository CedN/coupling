package cna.design.coupling.strong;

import cna.design.coupling.strong.biz.Person;
import cna.design.coupling.strong.biz.PersonAlreadyExistException;
import cna.design.coupling.strong.biz.PersonService;
import cna.design.coupling.strong.biz.PersonUnknownException;
import cna.design.coupling.strong.data.PersonEntityDao;

import java.util.List;

public class PersonStrongCoupling {

    public static void main(String[] args) throws PersonAlreadyExistException, PersonUnknownException {
        PersonService personService = new PersonService(new PersonEntityDao());

        Person joe = personService.createPerson(12345678L, "Jambon", "Joe");
        Person jane = personService.createPerson(23456789L, "Jambon", "Jane");
        Person bob = personService.createPerson(1357924L, "Jambon", "Robert");
        Long johnSocialId = 1246802L;
        Person john = personService.createPerson(johnSocialId, "Doe", "John");

        List<Person> jambonFamily = personService.searchByLastname("Jambon");
        System.out.println("Jambon Family: " + jambonFamily);

        Person johnBySocialId = personService.findBySocialId(johnSocialId);
        System.out.println("John By SocialID: " + johnBySocialId);

    }
}
