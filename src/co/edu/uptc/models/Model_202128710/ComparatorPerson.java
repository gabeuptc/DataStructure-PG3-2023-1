package co.edu.uptc.models.Model_202128710;

import co.edu.uptc.pojos.Person;

import java.util.Comparator;

public class ComparatorPerson implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getId()- o2.getId();
    }
}
