package co.edu.uptc.models.model202114852;

public class Node<T> {
	
	private T data;
	private Node<T> left, right;

	public Node(T data) {
		this.data = data;
	}
	
	public Node(T data, Node<T> left, Node<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public T getValue() {
		return data;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}
	
}
