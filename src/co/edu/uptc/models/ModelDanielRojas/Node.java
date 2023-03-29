package co.edu.uptc.models.ModelDanielRojas;

public class Node {
    private int info;
    private Node less;
    private Node major;

    public Node() {
    }

    public Node(int info) {
        this.info = info;
        this.less = null;
        this.major = null;
    }

    public Node(int info, Node less, Node major) {
        this.info = info;
        this.less = less;
        this.major = major;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public Node getLess() {
        return less;
    }

    public void setLess(Node less) {
        this.less = less;
    }

    public Node getMajor() {
        return major;
    }

    public void setMajor(Node major) {
        this.major = major;
    }

}
