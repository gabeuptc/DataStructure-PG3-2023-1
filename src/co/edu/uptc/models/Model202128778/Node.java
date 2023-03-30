package co.edu.uptc.models.Model202128778;

public class Node<T> {
    private T info;
    private Node<T> less = null;
    private Node<T> mejorAndEqual = null;
    public Node() {}
    public Node(T info) {
        this.info = info;
    }
    public Node(T info, Node<T> less, Node<T> mejorAndEqual) {
        this.info = info;
        this.less = less;
        this.mejorAndEqual = mejorAndEqual;
    }
    public T getInfo() {
        return info;
    }
    public void setInfo(T info) {
        this.info = info;
    }
    public Node<T> getLess() {
        return less;
    }
    public void setLess(Node<T> less) {
        this.less = less;
    }
    public Node<T> getMejorAndEqual() {
        return mejorAndEqual;
    }
    public void setMejorAndEqual(Node<T> mejorAndEqual) {
        this.mejorAndEqual = mejorAndEqual;
    }
}