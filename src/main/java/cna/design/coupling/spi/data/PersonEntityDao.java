package cna.design.coupling.spi.data;

import java.util.List;

public interface PersonEntityDao {
    void create(PersonEntity personEntity) throws PersonEntityAlreadyExistException;

    PersonEntity readPerson(int personId);

    List<PersonEntity> findBySocialId(Long socialId);

    List<PersonEntity> findByLastname(String lastname);
}
