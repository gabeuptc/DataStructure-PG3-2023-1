package co.edu.uptc.models.cod202115100;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {
    private NodeDouble<T> root;
    public BinaryTree() {
        root = null;
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
        if (data.compareTo(current.getData())<0) {
            current.setPrevious(addRecursive(current.getPrevious(), data));
        }
        if (data.compareTo(current.getData())>=0) {
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
}