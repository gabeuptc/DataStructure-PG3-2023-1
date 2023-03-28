package co.edu.uptc.models.Model202127812;

import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TreeUPTCPerson {
    private Node head;
    private final Comparator<Person> comparator = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
    public void add(Person o){
        Node node = new Node(o);
        if (head==null){
            head=node;
        }else {
            Node temp = search(node);
            if (comparator.compare(o,temp.getInfo())>=0){
                temp.setMayorEqual(node);
            }else {
                temp.setMinor(node);
            }
        }
    }
    private Node search(Node node){
        Node aux =head;
        boolean isFounded = false;
        while (!isFounded){
            if (comparator.compare(node.getInfo(),aux.getInfo())<0){
                if (aux.getMinor()!=null){
                    aux = aux.getMinor();
                }else {
                    isFounded=true;
                }
            }else {
                if (aux.getMayorEqual()!=null){
                    aux=aux.getMayorEqual();
                }else {
                    isFounded=true;
                }
            }
        }
        return aux;
    }

    private Node search(Person value){
        if (head==null){
            return null;
        }
        Node aux =head;
        boolean isFounded = false;
        while (!isFounded){
            if (comparator.compare(value,aux.getInfo())<0){
                if (aux.getMinor()!=null){
                    aux = aux.getMinor();
                }else {
                    isFounded=true;
                }
            }else {
                if (comparator.compare(value,aux.getInfo())==0){
                    return aux;
                }
                if (aux.getMayorEqual()!=null){
                    aux=aux.getMayorEqual();
                }else {
                    isFounded=true;
                }
            }
        }
        return null;
    }
    public List<Person> getListFromTree(){
        List<Person> list= new ArrayList<>();
        if (head!=null){
            getTreePrev(head,list);
        }
        return list;
    }

    public void getTreePrev(Node node, List<Person> list){
        //System.out.println(node.getInfo());
        if (node.getMinor()!=null){
            getTreePrev(node.getMinor(),list);
        }
        list.add(node.getInfo());
        if (node.getMayorEqual()!=null){
            getTreePrev(node.getMayorEqual(),list);
        }
        //System.out.println(node.getInfo());
    }
    public boolean remove(Person value){
        Node toRemove = search(value);
        if (toRemove!=null){
            Node toAdd;
            Node before = getBeforeTo(toRemove);
            if (before==null){
                toAdd = head.getMinor();
                head = head.getMayorEqual();
            }else {
                if (comparator.compare(before.getInfo(),toRemove.getInfo())<0){
                    toAdd = toRemove.getMinor();
                    before.setMayorEqual(toRemove.getMayorEqual());
                }else {
                    toAdd= toRemove.getMayorEqual();
                    before.setMinor(toRemove.getMinor());
                }
            }
            if (toAdd!=null){
                Node temp = search(toAdd);
                if (comparator.compare(toAdd.getInfo(),temp.getInfo())>=0){
                    temp.setMayorEqual(toAdd);
                }else {
                    temp.setMinor(toAdd);
                }
            }
            return true;
        }
        return false;
    }
    private Node getBeforeTo(Node node){
        if (comparator.compare(node.getInfo(),head.getInfo())==0){
            return null;
        }
        Node aux =head;
        boolean isFounded = false;
        while (!isFounded){
            if (comparator.compare(node.getInfo(),aux.getInfo())<0){
                if (aux.getMinor()!=null){
                    if (node.equals(aux.getMinor())){
                        return aux;
                    }else {
                        aux = aux.getMinor();
                    }
                }else {
                    isFounded=true;
                }
            }else {
                if (aux.getMayorEqual()!=null){
                    if (node.equals(aux.getMayorEqual())){
                        return aux;
                    }else {
                        aux=aux.getMayorEqual();
                    }
                }else {
                    isFounded=true;
                }
            }
        }
        return null;
    }
    public Person getPerson(String attribute){
        return getNodePerson(head,attribute).getInfo();
    }
    public Node getNodePerson(Node node, String attribute){//probar
        if (node.getMinor()!=null){
            getNodePerson(node.getMinor(),attribute);
        }
        if (node.getInfo().getName().equals(attribute)||node.getInfo().getCode().equals(attribute)){
            return node;
        }
        if (node.getMayorEqual()!=null){
            getNodePerson(node.getMayorEqual(),attribute);
        }
        return null;
    }
}
