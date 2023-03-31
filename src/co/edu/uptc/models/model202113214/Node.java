package co.edu.uptc.models.model202113214;

import co.edu.uptc.pojos.Person;

public class Node {

    private  Person info;
    private  Node less =null;
    private  Node majorAndEqual = null;
    public Person getInfo() {
        return info;
    }
    public Node(Person info){
        this.info = info;
    }
    public void setInfo(Person info) {
        this.info = info;
    }
    public Node getLess() {
        return less;
    }
    public void setLess(Node less) {
        this.less = less;
    }
    public Node getmajorAndEqual() {
        return majorAndEqual;
    }
    public void setmajorAndEqual(Node majorAndEqual) {
        this.majorAndEqual = majorAndEqual;
    }
    public Node(Person info, Node less, Node majorAndEqual) {
        this.info = info;
        this.less = less;
        this.majorAndEqual = majorAndEqual;
    }   
}
