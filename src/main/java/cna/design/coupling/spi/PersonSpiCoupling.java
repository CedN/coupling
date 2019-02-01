package cna.design.coupling.spi;

import cna.design.coupling.spi.biz.Person;
import cna.design.coupling.spi.biz.PersonAlreadyExistException;
import cna.design.coupling.spi.biz.PersonService;
import cna.design.coupling.spi.biz.PersonUnknownException;

import java.util.List;

public class PersonSpiCoupling {

    public static void main(String[] args) throws PersonAlreadyExistException, PersonUnknownException {
        PersonService personService = new PersonService();

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
