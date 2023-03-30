package co.edu.uptc.models.model202114852;

import co.edu.uptc.models.model_202115100.myPojos.NodeDouble;

import java.util.ArrayList;
import java.util.Comparator;


public class BinaryTree<T> {

	private Node<T> root;
	private Comparator<T> comparator;
	private ArrayList<T> aux;

	public BinaryTree(Comparator<T> comparator) {
		initializeAux();
		this.root = null;
		this.comparator = comparator;
	}

	public void initializeAux() {
		aux = new ArrayList<T>();
	}

	public void insert(T data) {
		if (!isEmpty()) {
			Node<T> aux = root;
			Node<T> previous;
			while (aux != null) {
				previous = aux;
				if (comparator.compare(data, aux.getValue()) < 0) {
					aux = aux.getLeft();
					if (aux == null) {
						previous.setLeft(new Node<T>(data));
					}
				} else {
					aux = aux.getRight();
					if (aux == null) {
						previous.setRight(new Node<T>(data));
					}
				}
			}
		} else {
			root = new Node<T>(data);
		}
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean exits(T value) {
		boolean aux = false;
		if (root.getValue() == value) {
			aux = true;
		} else {
			Node<T> auxNode = root;
			while (!aux) {
//				if(auxNode == null) {
//					throw new NotValueTreeBinary();
//				}
				if (comparator.compare(value, auxNode.getValue()) < 0) {
					if (auxNode.getLeft() != null && comparator.compare(auxNode.getLeft().getValue(), value) == 0) {
						aux = true;
					} else {
						auxNode = auxNode.getLeft();
					}
				} else if (comparator.compare(value, auxNode.getValue()) > 0) {
					if (auxNode.getRight() != null &&  comparator.compare(auxNode.getRight().getValue(), value) == 0) {
						aux = true;
					} else {
						auxNode = auxNode.getRight();
					}
				}
			}
		}
		return aux;
	}
	public T searchObject(T data) {
		T auxData = null;
		if (!isEmpty()) {
			Node<T> aux = root;
			while (aux != null && auxData == null) {
				if (comparator.compare(data, aux.getValue()) == 0) {
					auxData = aux.getValue();
				} else if (comparator.compare(data, aux.getValue()) < 0) {
					aux = aux.getLeft();
				} else {
					aux = aux.getRight();
				}
			}
		}
		return auxData;
	}

	public void printPreOrder() {
		printPreOrder(root);
	}

	public void printPosOrder() {
		printPostOrder(root);
	}

	public void printInOrder() {
		printInOrder(root);
	}

	private void printPreOrder(Node<T> node) {
		if (node != null) {
			aux.add(node.getValue());
			printPreOrder(node.getLeft());
			printPreOrder(node.getRight());
		}
	}

	private void printInOrder(Node<T> node) {
		if (node != null) {
			printInOrder(node.getLeft());
			aux.add(node.getValue());
			printInOrder(node.getRight());
		}
	}

	private void printPostOrder(Node<T> node) {
		if (node != null) {
			printPreOrder(node.getLeft());
			printPreOrder(node.getRight());
			aux.add(node.getValue());
		}
	}

	public ArrayList<T> getDatasOrder() {
		return aux;
	}

	public T getPerson(String attribute){
		return search(root, attribute);
	}

	public T search(Node<T> root, String attribute) {
		if (root == null) {
			return null;
		}
		if (root.getValue().toString().toLowerCase().contains(attribute.toLowerCase())) {
			return root.getValue();
		}
		T result = search(root.getLeft(), attribute);
		if (result != null) {
			return result;
		}
		return search(root.getRight(), attribute);
	}
}
