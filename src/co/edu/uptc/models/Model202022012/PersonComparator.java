package co.edu.uptc.models.Model202022012;

import co.edu.uptc.pojos.Person;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getCode().compareTo(o2.getCode());
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
