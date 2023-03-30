package co.edu.uptc.models.Model_201721961;
import co.edu.uptc.pojos.Person;

public class Node {

    private Person dato;
    private Node izq, der;

    public Node(Person dato, Node izq, Node der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public Person getDato() {
        return dato;
    }

    public void setDato(Person dato) {
        this.dato = dato;
    }

    public Node getIzq() {

        return izq;
    }

    public void setIzq(Node izq) {

        this.izq = izq;
    }

    public Node getDer() {

        return der;
    }

    public void setDer(Node der) {

        this.der = der;
    }
}
