package co.edu.uptc.models.Model_202023577;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.tree.TreeUptc;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}