package co.edu.uptc.models.Alex202128687;

import co.edu.uptc.pojos.Person;

import java.util.Comparator;

public class PersonCompare implements Comparator<Person> {
    public PersonCompare() {
    }
    @Override
    public int compare(Person person1, Person person2) {
        return person1.getId() - person2.getId();
    }
}
