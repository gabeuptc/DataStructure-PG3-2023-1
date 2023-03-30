package co.edu.uptc.models.model202114852;

import co.edu.uptc.pojos.Person;

import java.util.Comparator;

public class NodeComparator implements Comparator<Person> {
    public int compare(Person object1, Person object2) {
        return object1.getCode().compareToIgnoreCase(object2.getCode());
    }

}
