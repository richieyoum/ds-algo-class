package assignment1;

/**
 * simple testing of singly linked list
 */

public class Testing {

    public static void printHeadTail(SinglyLinkedList<Integer> list) {
        System.out.println("Head val: " + list.getFirst() + " Tail val: " + list.getLast());
    }

    public static void printSize(SinglyLinkedList<Integer> list){
        System.out.println("Size: " + list.getSize() + "\n");
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        printHeadTail(list);
        printSize(list);

        System.out.println("Adding 5 to head");
        list.addFirst(5);
        printHeadTail(list);
        printSize(list);

        System.out.println("Adding 10 to tail");
        list.addLast(10);
        printHeadTail(list);
        printSize(list);

        System.out.println("Adding 12 to tail");
        list.addLast(12);
        printHeadTail(list);
        printSize(list);

        System.out.println("Adding 7 to head");
        list.addFirst(7);
        printHeadTail(list);
        printSize(list);

        System.out.println("Removing head: " + list.removeFirst());
        printHeadTail(list);
        printSize(list);

        System.out.println("Removing tail: " + list.removeLast());
        printHeadTail(list);
        printSize(list);

        System.out.println("Removing tail: " + list.removeLast());
        printHeadTail(list);
        printSize(list);

        System.out.println("Removing tail: " + list.removeLast());
        printHeadTail(list);
        printSize(list);

        System.out.println("Removing tail: " + list.removeLast());
        printHeadTail(list);
        printSize(list);
    }
}
