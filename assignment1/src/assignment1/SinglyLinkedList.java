package assignment1;

public class SinglyLinkedList<E> {
    private Node<E> head = null;
    private Node<E> tail = null;
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
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Returns first element of the list
     * @return first element of the list, null if empty
     */
    public E getFirst(){
        return !isEmpty() && head != null ? head.getElement() : null;
    }

    /**
     * Returns last element of the list
     * @return last element of the list, null if empty
     */
    public E getLast(){
        return !isEmpty() && tail != null ? tail.getElement() : null;
    }

    //Update methods
    /**
     * Add a new node to the front of the linked list
     * @param newElem new element to add
     */
    public void addFirst(E newElem){
        // assigns the new head as current head, and points to the former head
        this.head = new Node(newElem, head);
        // if list was empty, the head will also be a tail
        if (isEmpty()){
            tail = head;
        }
        // increment the size
        size++;
    }

    /**
     * Add a new node to the end of the linked list
     * @param newElem new element to add
     */
    public void addLast(E newElem){
        // create new node with the given data value
        Node<E> newNode= new Node(newElem, null);
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

    public E removeFirst(){
        if (isEmpty()) return null;
        // store value of the to-be removed head node
        E elemRemoved = head.getElement();
        // set head to the node previous head was pointing to. This effectively removes old head from list
        head = head.getNextNode();
        // decrement size
        size--;
        // if head node was the only item, then set tail to null
        if (isEmpty()){
            tail = null;
        }
        return elemRemoved;
    }

    public E removeLast(){
        if (isEmpty()) return null;
        E elemRemoved = tail.getElement();
        // set head and tail to null if there is only 1 element, which is to be removed
        if (size == 1){
            head = null;
            tail = null;
            size--;
            return elemRemoved;
        }
        // iterate from head node to get the node just before the current tail
        Node<E> prevNode = head;
        while (prevNode.getNextNode() != tail){
            prevNode = prevNode.getNextNode();
        }
        // untie the pointer to the current tail, then make that node as new tail
        prevNode.setNextNode(null);
        tail = prevNode;
        // decrement size
        size--;
        return elemRemoved;
    }


    /**
     * Node of singly linked list
     */
    private static class Node<E>{
        private E element;
        private Node<E> nextNode;

        // Constructor for Node class
        public Node(E element, Node<E> nextNode){
            /**
             * Constructor for Node class
             *
             * @param element Data of the node
             * @param nextNode Pointer node. Can be null.
             */
            this.element = element;
            this.nextNode = nextNode;
        }

        /**
         * Get the value of the node
         * @return value of the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Get the next node current node is pointing at. Can be null.
         * @return the node current node is pointing to
         */
        public Node<E> getNextNode(){
            return nextNode;
        }

        /**
         * Set node pointer to the new node. Can be null
         * @param newNode the node to point to
         */
        public void setNextNode(Node<E> newNode){
            this.nextNode = newNode;
        }
    }
}
