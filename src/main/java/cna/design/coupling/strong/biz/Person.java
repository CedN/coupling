package cna.design.coupling.strong.biz;

public class Person {

    public final long socialId;

    public final String lastname;

    public final String firstname;

    public Person(long socialId, String lastname, String firstname) {
        this.socialId = socialId;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "socialId=" + socialId +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}
