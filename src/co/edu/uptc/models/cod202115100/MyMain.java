package co.edu.uptc.models.cod202115100;

import co.edu.uptc.models.cod202115100.myPojos.BinaryTree;
import co.edu.uptc.pojos.Person;

public class MyMain {

    public static void main(String[] args) {
        BinaryTree<Person> tree = new BinaryTree<>(o -> o.getName().compareTo(o.getName()));
        tree.add(new Person(1, "Juan", "5"));
        tree.add(new Person(2, "Pedro", "3"));
        tree.add(new Person(3, "Maria", "7"));
        tree.add(new Person(4, "Luis", "1"));
        System.out.println(tree.search(tree.getRoot(), "pedro"));
    }
}
