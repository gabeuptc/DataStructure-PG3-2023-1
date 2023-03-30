package co.edu.uptc.models.model202127061;

import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryTreeList<T> {
    private Node<Person> header;
    private Comparator<T> comparator;
    private List<T> list;

    public BinaryTreeList(Comparator<T> comparator){
        this.comparator = comparator;
        header = null;
        list = new ArrayList<>();
    }

    public void add(T info) {
    Node aux = new Node(info);
    if (header == null) {
        header = aux;
    } else {
        Node tmp = search(aux);
            if (comparator.compare((T) tmp.getInfo(), info) >= 0) {
                tmp.setMejorAndEqual(aux);
                aux.setNodeFather(tmp);
            } else {
                tmp.setLess(aux);
                aux.setNodeFather(tmp);
            }
        }
        list.add(info);
    }
    private Node search(Node node){
        Node aux = header;
        boolean found = false;
        while (!found) {
            if (comparator.compare((T) aux.getInfo(), (T) node.getInfo()) >= 0) {
                if (aux.getLess() == null) {
                    aux.setLess(node);
                } else {
                    found = true;
                }
            }else {
                if(aux.getMejorAndEqual()!=null) {
                    aux = aux.getMejorAndEqual();
                }else {
                    found = true;
                }
            }
        }
        return aux;
    }
    public void showTree(){
        showTreeOrden(header);
    }
    public void showTreeOrden(Node node){
        if(node.getLess()!=null){
            showTreeOrden(node.getLess());
        }
        if(node.getMejorAndEqual()!=null){
            showTreeOrden(node.getMejorAndEqual());
        }
    }
    public void remove(){
        Node aux = new Node();
        if(false) {

        }
    }
    public List<T> getList() {
        return list;
    }
}
