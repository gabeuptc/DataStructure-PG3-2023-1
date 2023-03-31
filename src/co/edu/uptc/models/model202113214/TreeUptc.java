package co.edu.uptc.models.model202113214;

import java.util.Comparator;

import co.edu.uptc.pojos.Person;

public class TreeUptc {
    Node header;

    public void add(Person info) {
        Node aux = new Node(info);
        if (header == null) {
            header = aux;
        } else {
            Node current = header;
            while (current.getmajorAndEqual() != null) {
                
                current = current.getmajorAndEqual();
            }
            current.setmajorAndEqual(aux);
        }
    }
    
    public void showTreePreOrden(Node node){
        if(node.getLess()!=null){
            showTreePreOrden(node.getLess());
            System.out.println(node.getInfo());
        }
        if(node.getmajorAndEqual() != null){
            showTreePreOrden(node.getmajorAndEqual());
            System.out.println(node.getInfo());
        }
    }
    private Node search(Node node){
        Node aux = header;
        boolean isFound = false;
        while(!isFound){
            if(aux.getInfo().getId() > node.getInfo().getId()){
                if(aux.getLess() != null){
                    aux =  aux.getLess();
                }else{
                    isFound = true;
                }
            }else{
                if(aux.getLess() !=null){
                    aux = aux.getmajorAndEqual();
                }else{
                    isFound = true;
                }
            }
        } 
        return aux;
    }

    public Node getHeader() {
        return header;
    }

    public void setHeader(Node header) {
        this.header = header;
    }

    /*public void DeleteNode(Node node){
        Node aux = search(node);
        if(aux.getLess() != null && aux.getLess().getLess() != null){
            aux.getLess().setmajorAndEqual(aux.getmajorAndEqual());
            
        }
    }*/
}


