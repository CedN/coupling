package cna.design.coupling.spi.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonEntityDaoImpl implements PersonEntityDao {

    private final Map<String, PersonEntity> personEntities;

    public PersonEntityDaoImpl() {
        personEntities = new HashMap();
    }

    @Override
    public void create(PersonEntity personEntity) throws PersonEntityAlreadyExistException {
        if (personEntities.containsKey(personEntity.getId())) {
            throw new PersonEntityAlreadyExistException();
        }
        personEntities.put(personEntity.getId(), personEntity);
    }

    @Override
    public PersonEntity readPerson(int personId) {
        return personEntities.get(personId);
    }

    @Override
    public List<PersonEntity> findBySocialId(Long socialId) {
        return findByAttribute(socialId, personEntity -> personEntity.getSocialId() == socialId);
    }

    @Override
    public List<PersonEntity> findByLastname(String lastname) {
        return findByAttribute(lastname, personEntity -> personEntity.getLastname().equals(lastname));
    }

    private <T> List<PersonEntity> findByAttribute(T value, Predicate<PersonEntity> filteringFunction) {
        return personEntities.entrySet().stream()
                .map(personEntry -> personEntry.getValue())
                .filter(filteringFunction)
                .collect(Collectors.toList());
    }
}
