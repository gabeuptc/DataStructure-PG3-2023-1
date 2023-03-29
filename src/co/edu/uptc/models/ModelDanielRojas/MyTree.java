package co.edu.uptc.models.ModelDanielRojas;

public class MyTree {
    Node header;

    public void add(int info) {
        Node newNode = new Node(info);
        if (header == null) {
            header = newNode;
        } else {
            Node tmp = search(newNode);
            if (info > tmp.getInfo()) {
                tmp.setMajor(newNode);
            } else {
                tmp.setLess(newNode);
            }
        }
    }

    private Node search(Node newNode) {
        Node aux = header;
        boolean isFound = false;
        while (!isFound) {
            if (aux.getInfo() > newNode.getInfo()) {
                if (aux.getLess() != null) {
                    aux = aux.getLess();
                } else {
                    isFound = true;
                }
            } else {
                if (aux.getMajor() != null) {
                    aux = aux.getMajor();
                } else {
                    isFound = true;
                }
            }
        }
        return aux;
    }

    public void delete(int value) {
        if (value != header.getInfo()) {
            this.delete(value, header);
        } else header = null;
    }

    public void delete(int value, Node node) {
        if (value == node.getInfo()) {
            node = null;
        } else if (value < node.getInfo()) {
            if (value == node.getLess().getInfo() && node.getLess() != null) {
                node.setLess(null);
            } else delete(value, node.getLess());
        } else if (value > node.getInfo()) {
            if (value == node.getMajor().getInfo() && node.getMajor() != null) {
                node.setMajor(null);
            } else delete(value, node.getMajor());
        }
    }

    public boolean exist(int info) {
        return this.exist(info, header);
    }

    private boolean exist(int info, Node node) {
        if (node == null) {
            return false;
        } else if (info == node.getInfo()) {
            return true;
        } else if (info < node.getInfo()) {
            return exist(info, node.getLess());
        } else return exist(info, node.getMajor());
    }

    public void showTree() {
//        System.out.println(header.getInfo());
//        showTreePreOrder(header);
        showTreeInOrder(header);
//        showTreePostOrder(header);
    }

    public void showTreePreOrder(Node node) {
        System.out.println(node.getInfo());
        if (node.getLess() != null) {
            showTreePreOrder(node.getLess());
        }
        if (node.getMajor() != null) {
            showTreePreOrder(node.getMajor());
        }
    }

    public void showTreeInOrder(Node node) {
        if (node != null) {
            if (node.getLess() != null) {
                showTreeInOrder(node.getLess());
            }
            System.out.println(node.getInfo());
            if (node.getMajor() != null) {
                showTreeInOrder(node.getMajor());
            }
        } else {
            System.out.println("No hay árbol.");
        }
    }

    public void showTreePostOrder(Node node) {
        if (node.getLess() != null) {
            showTreePostOrder(node.getLess());
        }
        if (node.getMajor() != null) {
            showTreePostOrder(node.getMajor());
        }
        System.out.println(node.getInfo());
    }
}
