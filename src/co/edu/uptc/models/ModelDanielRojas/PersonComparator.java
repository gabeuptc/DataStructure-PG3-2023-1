package co.edu.uptc.models.ModelDanielRojas;

import co.edu.uptc.pojos.Person;

import java.util.Comparator;

public class PersonComparator {

    static Comparator orderNamePerson = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Person p1 = (Person)o1;
            Person p2 = (Person)o2;
            return p1.getName().compareTo(p2.getName());
        }
    };

    static Comparator orderCodePerson = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Person p1 = (Person)o1;
            Person p2 = (Person)o2;
            return p1.getCode().compareTo(p2.getCode());
        }
    };

    /*static Comparator<Person> orderIdPerson = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            int aux = p1.getId().compareTo(p2.getId());
            if (aux == 0) {
                aux = p1.getId().compareTo(p2.getId());
            }
            return aux;
        }

    };*/
}
