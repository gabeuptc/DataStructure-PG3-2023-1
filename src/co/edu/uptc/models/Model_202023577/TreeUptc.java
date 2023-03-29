package co.edu.uptc.models.Model_202023577;

import co.edu.uptc.pojos.Person;

public class TreeUptc<T>{

    PersonComparator comparatorPerson;
    Node<T> header;

    public TreeUptc(){
        comparatorPerson = new PersonComparator();
    }

    public void add(T info){
        Node<T> aux = new Node(info);
        if(header == null){
            header = aux;
        }else {
            Node<T> tmp = search(aux);
            if(comparatorPerson.compare((Person) info, (Person) tmp.getInfo()) >= 1){
                tmp.setMajorAndEqual(aux);
            }else{
                tmp.setLess(aux);
            }
        }
    }

    public void showTree(){
        showTreePreOrder(header);
    }

    public void showTreePreOrder(Node<T> node){
        if(node.getLess()!=null) {
            showTreePreOrder(node.getLess());
        }
        System.out.println(node.getInfo());
        if (node.getMajorAndEqual() != null){
            showTreePreOrder(node.getMajorAndEqual());
        }
    }

    private Node<T> search(Node<T> node){
        Node<T> aux = header;
        boolean isFound = false;
        while (!isFound){
            if(comparatorPerson.compare((Person) node.getInfo(), (Person) aux.getInfo()) < 0){
                if(aux.getLess() != null) {
                    aux = aux.getLess();
                }else{
                    isFound = true;
                }
            }else {
                if(aux.getMajorAndEqual() != null) {
                    aux = aux.getMajorAndEqual();
                }else {
                    isFound = true;
                }
            }
        }
        return aux;
    }
}
