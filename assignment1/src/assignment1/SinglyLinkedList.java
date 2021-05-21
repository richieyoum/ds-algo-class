package assignment1;

public class SinglyLinkedList {
    private Node<String, Integer> head = null;
    private Node<String, Integer> tail = null;
    private int size;

    // Constructor for SinglyLinkedList
    public SinglyLinkedList(){
    }

    // Access methods
    /**
     * Returns number of elements in linked list
     * @return number of elements in the linked list
     */
    public int getSize(){
        return size;
    }

    /**
     * Checks whether linked list is empty
     * @return boolean indicating whether list is empty
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Returns list head node
     * @return list head node
     */
    public Node getHeadNode(){
        return head;
    }

    /**
     * Returns list tail node
     * @return list tail node
     */
    public Node getTailNode(){
        return tail;
    }


    //Update methods
    /**
     * Add a new head node to the list. Should only be called when there is no existing node with the name yet
     * @param name new bird name
     */
    public void addHeadNode(String name){
        // create new node with the given bird name
        Node newNode = new Node(name, 1);
        // point to the current head node
        newNode.setNextNode(head);
        // set the new node as head
        head = newNode;
        // if list was empty, the head will also be a tail
        if (isEmpty()){
            tail = newNode;
        }
        // increment the size
        size++;
    }

    /**
     * Add a new tail node to the list. Should only be called when there is no existing node with the name yet
     * @param name new bird name
     */
    public void addTailNode(String name){
        // create new node with the given bird name
        Node newNode = new Node(name, 1);
        // if list was empty, the tail will also be a head
        if (isEmpty()){
            head = newNode;
        }
        // otherwise point the previous tail to the new node
        else{
            tail.setNextNode(newNode);
        }
        // set the new node as the tail
        tail = newNode;
        // increment the size
        size++;
    }

    /**
     * Remove the head node
     */
    public void removeHeadNode(){
        // set head to the node previous head was pointing to. This effectively removes old head from list
        head = head.getNextNode();
        // decrement size
        size--;
        // if head node was the only item, then set tail to null
        if (isEmpty()){
            tail = null;
        }
    }

    /**
     * Remove the tail node
     */
    public void removeTailNode(){
        // set head and tail to null if there is only 1 element, which is to be removed
        if (size == 1){
            head = null;
            tail = null;
            size--;
        }
        // iterate from head node to get the node just before the current tail
        Node prevNode = head;
        while (prevNode.getNextNode() != tail){
            prevNode = prevNode.getNextNode();
        }
        // untie the pointer to the current tail, then make that node as new tail
        prevNode.setNextNode(null);
        tail = prevNode;
        // decrement size
        size--;
    }


    // temporary check
    public void printNodeItems(){
        Node prevNode = head;
        while (prevNode.getNextNode() != tail){
            System.out.println(prevNode.getName() + " " + prevNode.getCount());
            prevNode = prevNode.getNextNode();
        }
    }




    /**
     * Node class for singly linked list.
     */
    private static class Node<N, C>{
        private String name;
        private int count;
        private Node nextNode = null;

        // Constructor for Node class
        public Node(String name, int count){
            /**
             * Constructor for Node class
             *
             * @param name bird name
             * @param count number of times the bird appeared
             * @param nextNode Pointer node. Can be null.
             */

            this.name = name;
            this.count = count;
        }

        /**
         * Get the bird name
         * @return bird's name
         */
        public String getName() {
            return name;
        }

        /**
         * Get the bird appearance count
         * @return bird appearance count
         */
        public int getCount(){
            return count;
        }

        /**
         * Get the next node current node is pointing at. Can be null.
         * @return the node current node is pointing to
         */
        public Node getNextNode(){
            return nextNode;
        }

        /**
         * Set node pointer to the new node. Can be null
         * @param newNode the node to point to
         */
        public void setNextNode(Node newNode){
            this.nextNode = newNode;
        }
    }
}
