package src.main.java;

/**
 * Created by Jordan on 4/16/2017.
 */
public class Node {

    private Object item;
    private Node next;

    public static void main(String[] args) {
        Node first = new Node();
        Node second = new Node();
        Node third = new Node();

        first.item = "to";
        second.item = "be";
        third.item = "or";

        first.next = second;
        second.next = third;
    }

}
