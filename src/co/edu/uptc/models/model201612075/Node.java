package co.edu.uptc.models.model201612075;

import co.edu.uptc.pojos.Person;

public class Node {
    private Person person;
    private Node minor=null;
    private Node greaterEqual =null;

    public Node(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public Node getMinor() {
        return minor;
    }

    public void setMinor(Node minor) {
        this.minor = minor;
    }

    public Node getGreaterEqual() {
        return greaterEqual;
    }

    public void setGreaterEqual(Node greaterEqual) {
        this.greaterEqual = greaterEqual;
    }
}
