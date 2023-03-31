package co.edu.uptc.models.model202128710;

import co.edu.uptc.pojos.Person;

import java.util.Comparator;

public class ComparatorPerson202128710 implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getId()-o2.getId();
    }
}
