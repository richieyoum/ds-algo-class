package assignment1;

public class SinglyLinkedList {
    private Node head = null;
    private Node tail = null;
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
     * Add a new head node to the list.
     * @param name new bird name
     */
    public void addHeadNode(String name, int count){
        // create new node with the given bird name
        Node newNode = new Node(name, count);
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
     * Add a new tail node to the list.
     * @param name new bird name
     */
    public void addTailNode(String name, int count){
        // create new node with the given bird name
        Node newNode = new Node(name, count);
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
        // if head node was the only item, then set tail to null as well
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

    /**
     * Handles new entry to the list.
     * Increment count by 1 if the name already exists, if not create & append a new tail node for the name.
     * @param name name of the bird
     */
    public void readEntry(String name){
        // iterate through each nodes until end of node (tail)
        Node tempNode = head;
        boolean nameExists = false;
        int i = 0;
        // iterate through each element in the list
        while (i < size){
            if (isEmpty()) break;
            // increment count by 1 if name already exists in the list
            if (name.equalsIgnoreCase(tempNode.getName())){
                tempNode.incrementCount();
                nameExists = true;
                break;
            }
            tempNode = tempNode.getNextNode();
            i++;
        }
        // if name didn't exist yet, then create a new node and append it. Set count to 1.
        if (!nameExists) {
            addTailNode(name, 1);
        }
    }

    // Sorting functions
    /**
     * Get the middle node of the linked list
     * @param tempHead starting point of the list
     * @return middle node of the linked list
     */
    private Node getMiddleNode(Node tempHead){
        // return null in the case given head (or its next item) is null. Happens when the specific list has no element
        // or only contains 1 element
        if (tempHead == null || tempHead.getNextNode() == null) return null;
        // assign two nodes starting from head
        Node slow = tempHead; Node fast = tempHead;
        // fast node moves twice as fast than slow node. This way, by the time it reaches end of list,
        // slow node will be approximately at the middle of the linked list
        while (fast.getNextNode() != null && fast.getNextNode().getNextNode() != null){
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
        }
        // return the middle node
        return slow;
    }

    /**
     * Recursive calls to sort all items between the two linked list, and then merge them together
     * @param left a node to be compared with right node
     * @param right a node to be compared with left node
     * @return merged node from left and right nodes in ascending order
     */
    private Node sortedMerge(Node left, Node right){
        // recursion call to sort is completed, it'll get null on either of the node; return the other node in such case
        if (left == null) return right;
        if (right == null) return left;

        // set a temporary node to act as a smaller node, which will point to the bigger node
        Node tempNode;
        // if right node is greater or equal than left node
        if (left.getCount() <= right.getCount()){
            // assign left as the temporary (smaller) node
            tempNode = left;
            // recursion call to sort between the node next of left and right node
            tempNode.setNextNode(sortedMerge(left.getNextNode(), right));
        } else{
            // vice versa for case when left node is greater than right node
            tempNode = right;
            // recursion call to sort between left node and the node next of right
            tempNode.setNextNode(sortedMerge(left, right.getNextNode()));
        }
        // return the sorted & merged node
        return tempNode;
    }

    /**
     * Implementation of merge sort in singly linked list
     * @param tempHead
     * @return sorted linked list
     */
    private Node mergeSort(Node tempHead){
        // no need to sort when list is empty or only 1 element
        if (tempHead == null || tempHead.getNextNode() == null) return tempHead;

        // get the middle node. This is still connected to the tempHead node
        Node middleNode = getMiddleNode(tempHead);

        // get the node next to the middle node, which will be the starting point of the right half
        Node nextToMiddleNode = middleNode.getNextNode();

        // sever middleNode's connection with nextToMiddleNode to separate into two lists
        middleNode.setNextNode(null);

        // recursion call keeps splitting the list until reaching atomic pair comparison
        Node left = mergeSort(tempHead);
        Node right = mergeSort(nextToMiddleNode);
        // then sort and merge those pairs back until all pairs are fully merged
        return sortedMerge(left, right);
    }

    /**
     * Merge sort the current linked list
     */
    public void sortList(){
        // set the head to the sorted list
        head = mergeSort(head);
    }

    /**
     * Print the linked list items
     */
    public void printNodeItems(){
        Node tempNode = head;
        int i=0;
        while (i < size){
            System.out.println(tempNode.getName() + " " + tempNode.getCount());
            tempNode = tempNode.getNextNode();
            i++;
        }
    }

    /**
     * Node class for singly linked list.
     */
    private static class Node{
        private String name;
        private int count;
        private Node nextNode = null;

        /**
         * Constructor for Node class
         *
         * @param name bird name
         * @param count number of times the bird appeared
         */
        public Node(String name, int count){
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
         *
         * @return bird appearance count
         */
        public int getCount(){
            return count;
        }

        /**
         * Increment the count of the node by 1
         */
        public void incrementCount() { count++; }

        /**
         * Get the next node current node is pointing at. Can be null.
         *
         * @return the node current node is pointing to
         */
        public Node getNextNode(){ return nextNode; }

        /**
         * Set node pointer to the new node. Can be null
         * @param newNode the node to point to
         */
        public void setNextNode(Node newNode){ nextNode = newNode; }
    }
}
