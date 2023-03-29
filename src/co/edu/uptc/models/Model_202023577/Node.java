package co.edu.uptc.models.Model_202023577;

public class Node<T>{

    private T info;
    private Node<T> less;
    private Node<T> majorAndEqual;

    public Node(T info, Node<T> less, Node<T> majorAndEqual) {
        this.info = info;
        this.less = less;
        this.majorAndEqual = majorAndEqual;
    }

    public Node(T info) {
        this.info = info;
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

    public Node<T> getMajorAndEqual() {
        return majorAndEqual;
    }

    public void setMajorAndEqual(Node<T> majorAndEqual) {
        this.majorAndEqual = majorAndEqual;
    }
}