package cna.design.coupling.hexagonal.data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Persons are stored as Entity by PersonEntityDao.
 *
 * @since 1.0
 */
public class PersonEntity {

    private final String id;

    private final String lastname;

    private final String firstname;

    private final long socialId;

    private final LocalDateTime creationDateTime;

    public PersonEntity(String lastname, String firstname, long socialId) {
        this.id = UUID.randomUUID().toString();
        this.lastname = lastname;
        this.firstname = firstname;
        this.socialId = socialId;
        this.creationDateTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public long getSocialId() {
        return socialId;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id='" + id + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", socialId=" + socialId +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
