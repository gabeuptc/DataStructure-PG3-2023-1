package co.edu.uptc.models.Model_201721961;
import co.edu.uptc.pojos.Person;

public class BinaryTree {


    private Node raiz;
    int cant;
    int altura;

    public BinaryTree() {
        this.raiz = null;
    }

    public void agregar(Person dato) {
        Node nuevo = new Node(dato, null, null);
        addNode(nuevo, raiz);
    }

    public void addNode(Node nuevo, Nodo pivote) {
        if (this.raiz == null) {
            raiz = nuevo;
        } else {
            if (nuevo.getDato().getId() <= pivote.getDato().getId()) {
                if (pivote.getIzq() == null) {
                    pivote.setIzq(nuevo);
                } else {
                    addNode(nuevo, pivote.getIzq());
                }
            } else {
                if (pivote.getDer() == null) {
                    pivote.setDer(nuevo);
                } else {
                    addNode(nuevo, pivote.getDer());
                }
            }
        }

    }

    //metodos nuevos
    /*
    public boolean existe(Person info) {
        Node reco = raiz;
        while (reco != null) {
            if (info == reco.getDato()) {
                return true;
            } else if (info > reco.getDato()) {
                reco = reco.getDer();
            } else {
                reco = reco.getIzq();
            }
        }
        return false;
    }


     */
    public int cantidad() {
        cant = 0;
        cantidad(raiz);
        return cant;
    }

    private void cantidad(Node reco) {
        if (reco != null) {
            cant++;
            cantidad(reco.getIzq());
            cantidad(reco.getDer());
        }
    }

}
