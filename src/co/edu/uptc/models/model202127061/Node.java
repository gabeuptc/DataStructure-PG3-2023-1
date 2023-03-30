package co.edu.uptc.models.model202127061;

public class Node<T> {
    private T info;
    private Node<T> less = null;

    private Node<T> majorAndEqual = null;
    private Node<T> nodeFather;
    public Node() {}
    public Node(T info) {
        this.info = info;
    }
    public Node(T info,Node less, Node majorAndEqual){
        this.info = info;
        this.less = less;
        this.majorAndEqual = majorAndEqual;
    }
    public T getInfo() {
        return info;
    }
    public void setInfo(T info) {
        this.info = info;
    }
    public Node getLess() {
        return less;
    }
    public void setLess(Node less) {
        this.less = less;
    }
    public Node getMejorAndEqual() {
        return majorAndEqual;
    }
    public void setMejorAndEqual(Node mejorAndEqual) {
        this.majorAndEqual = mejorAndEqual;
    }

    public Node<T> getNodeFather() {
        return nodeFather;
    }

    public void setNodeFather(Node<T> nodeFather) {
        this.nodeFather = nodeFather;
    }
}