package co.edu.uptc.models.model202128687;

// Vamos a usar arboles binarios de busqueda
public class Node<T>{
    private T info;
    private Node<T> less = null;
    private Node<T> majorAndEqual = null;
    private Node<T> nodeFather;

    public Node(){

    }
    public Node(T info,Node less, Node majorAndEqual){
        this.info = info;
        this.less = less;
        this.majorAndEqual = majorAndEqual;
    }

    public Node(T info){
        this.info = info;
    }

    public T getInfo() {
        return this.info;
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

    public Node getMajorAndEqual() {
        return majorAndEqual;
    }

    public void setMajorAndEqual(Node majorAndEqual) {
        this.majorAndEqual = majorAndEqual;
    }

    public Node getNodeFather() {
        return nodeFather;
    }

    public void setNodeFather(Node nodeFather) {
        this.nodeFather = nodeFather;
    }

    public Node getNode(){
        return this;
    }

    @Override
    public String toString() {
        return "Node [info=" + info + ", less=" + less + ", majorAndEqual=" + majorAndEqual + "]";
    }
}
