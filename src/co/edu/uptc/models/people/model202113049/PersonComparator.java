package co.edu.uptc.models.people.model202113049;

import co.edu.uptc.pojos.Person;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {



    @Override
    public int compare(Person o1, Person o2) {
        int output =-1;
        if(o1.getId()>o2.getId()){
            output=0;
        }
        return output;
    }

}
