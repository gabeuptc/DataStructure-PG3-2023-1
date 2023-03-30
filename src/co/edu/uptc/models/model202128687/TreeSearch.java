package co.edu.uptc.models.model202128687;

import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TreeSearch<T> {
    private Node<Person> header;
    private Comparator<T> comparator;
    private List<T> list;

    public TreeSearch(Comparator<T> comparator){
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
                tmp.setMajorAndEqual(aux);
                aux.setNodeFather(tmp);
            } else {
                tmp.setLess(aux);
                aux.setNodeFather(tmp);
            }
        }
        list.add(info);
    }

    private Node search(Node node) {
        Node aux = header;
        boolean found = false;
        while (!found) {
            if (comparator.compare((T) aux.getInfo(), (T) node.getInfo()) >= 0) {
                if (aux.getLess() == null) {
                    aux.setLess(node);
                } else {
                    found = true;
                }
            } else {
                if (aux.getMajorAndEqual() != null) {
                    aux = aux.getMajorAndEqual();
                } else {
                    found = true;
                }
            }
        }
        return aux;
    }

    public void showTree() {
        if (header == null) {
        } else {
            showTreeOrder(header);
        }
    }

    public void showTreeOrder(Node node) {
        if (node.getLess() != null) {
            showTreeOrder(node.getLess());
        }
        if (node.getMajorAndEqual() != null) {
            showTreeOrder(node.getMajorAndEqual());
        }
    }

    private Node getFather(Node node) {
        return search(node).getNode().getNodeFather();
    }

    private Node getLess(Node node) {
        return search(node).getLess();
    }

    private Node getMajorAndEqual(Node node) {
        return search(node).getMajorAndEqual();
    }

    private Node getToDelete(Node node) {
        return search(node);
    }

    public void delete(T info) {
        Node nodeToDelete = getToDelete(new Node(info));
        Node nodeFather = getFather(nodeToDelete);
        if (getLess(nodeToDelete) == null && getMajorAndEqual(nodeToDelete) == null) {
            getFather(nodeToDelete).setLess(null);
        } else if (getLess(nodeToDelete) != null && getMajorAndEqual(nodeToDelete) == null) {
            nodeFather.setLess(getLess(nodeToDelete));
        } else if (getLess(nodeToDelete) == null && getMajorAndEqual(nodeToDelete) != null) {
            nodeFather.setMajorAndEqual(getMajorAndEqual(nodeFather));
        } else {
            nodeToDelete.setInfo(getLastLeftChild(nodeToDelete).getInfo());
        }
    }

    private Node getLastLeftChild(Node father){
        Node aux = father;
        while (aux.getLess() != null){
            aux = aux.getLess();
        }
        father = aux.getNodeFather();
        father.setLess(null);
        return aux;
    }

    public List<T> getList() {
        return list;
    }
}
