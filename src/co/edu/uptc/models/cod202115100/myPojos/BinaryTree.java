package co.edu.uptc.models.cod202115100.myPojos;

import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T> {
    private NodeDouble<T> root;
    private Comparable<T> comparable;
    public BinaryTree(Comparable<T> comparable) {
        root = null;
        this.comparable = comparable;
    }

    public BinaryTree(T data) {
        root = new NodeDouble<>(data);
    }

    public NodeDouble<T> getRoot() {
        return root;
    }

    public void add(T data){
        root = addRecursive(root, data);
    }

    private NodeDouble<T> addRecursive(NodeDouble<T> current, T data) {
        if(current == null){
            return new NodeDouble<>(data);
        }
        if (comparable.compareTo(data)<0) {
            current.setPrevious(addRecursive(current.getPrevious(), data));
        }
        if (comparable.compareTo(data)>=0) {
            current.setNext(addRecursive(current.getNext(), data));
        }
        return current;
    }
    public void remove(T data){
        NodeDouble<T> nodeBehind = searchRecursive(root, data);
        removeRecursive(root, data);
    }

    private NodeDouble<T> searchRecursive(NodeDouble<T> root, T data) {
//pendiente
        return null;
    }

    private NodeDouble<T> removeRecursive(NodeDouble<T> current, T data) {

        return current;
    }
    public List<T> toList(NodeDouble<T> current) {
        List<T> list = new ArrayList<>();
        if (current != null) {
            list.addAll(toList(current.getPrevious()));
            list.add(current.getData());
            list.addAll(toList(current.getNext()));
        }
        return list;
    }

    public static void main(String[] args) {
        BinaryTree<Person> tree = new BinaryTree<>(o -> o.getName().compareTo(o.getName()));
        tree.add(new Person(1, "123456","5"));
        tree.add(new Person(2, "123456","3"));
        tree.add(new Person(3, "123456","7"));
        tree.add(new Person(4, "123456","1"));
        tree.add(new Person(5, "123456","6"));
        tree.add(new Person(6, "123456","8"));
        tree.add(new Person(7, "123456","2"));
        tree.add(new Person(8, "123456","9"));
        List<Person> list = tree.toList(tree.getRoot());
        System.out.println(list);
        System.out.println(list.size());
    }
}