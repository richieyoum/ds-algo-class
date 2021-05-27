package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SinglyLinkedList {
	// 3 operations, constant
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
    	// 1 operation, constant
        return size;
    }

    /**
     * Checks whether linked list is empty
     * @return boolean indicating whether list is empty
     */
    public boolean isEmpty(){
    	// 1 operation, constant
        return size == 0;
    }

    /**
     * Returns list head node
     * @return list head node
     */
    public Node getHeadNode(){
    	// 1 operation , constant
        return head;
    }

    /**
     * Returns list tail node
     * @return list tail node
     */
    public Node getTailNode(){
    	// 1 operation, constant
        return tail;
    }

    //Update methods
    /**
     * Add a new head node to the list.
     * @param name new bird name
     */
    public void addHeadNode(String name, int count){
        // create new node with the given bird name
    	// 4 operationss, constant
    	// get value of name and count, create a new node, assign it to newNode
        Node newNode = new Node(name, count);
        // point to the current head node
        // 2 operationss, constant: get value of head, send to newNode.setNextNode
        newNode.setNextNode(head);
        // set the new node as head
        head = newNode;
        // if list was empty, the head will also be a tail
        // 3 operations, constant
        if (isEmpty()){
            tail = newNode;
        }
        // increment the size
        // 1 operation, constant
        size++;
    }

    /**
     * Add a new tail node to the list.
     * @param name new bird name
     */
    public void addTailNode(String name, int count){
        // create new node with the given bird name
    	// 4 operations
        Node newNode = new Node(name, count);
        // if list was empty, the tail will also be a head
        // 2 operations
        if (isEmpty()){
            head = newNode;
        }
        // otherwise point the previous tail to the new node
        else{
        	// 2 operations
            tail.setNextNode(newNode);
        }
        // set the new node as the tail
        // 2 operations
        tail = newNode;
        // increment the size
        // 2 operations
        size++;
    }

    /**
     * Remove the head node
     */
    public void removeHeadNode(){
        // set head to the node previous head was pointing to. This effectively removes old head from list
    	// 3 operations
        head = head.getNextNode();
        // decrement size
        // 2 operations
        size--;
        // if head node was the only item, then set tail to null as well
        // 1 operation
        if (isEmpty()){
        	// 1 operation
            tail = null;
        }
    }

    /**
     * Remove the tail node
     */
    public void removeTailNode(){
        // set head and tail to null if there is only 1 element, which is to be removed
    	// 1 operation
        if (size == 1){
        	//4 total operations
            head = null;
            tail = null;
            size--;
        }
        // iterate from head node to get the node just before the current tail
        // 2 operations
        Node prevNode = head;
        // this can happen 0 times to n times, so it's linear "n"
        while (prevNode.getNextNode() != tail){
            prevNode = prevNode.getNextNode();
        }
        // untie the pointer to the current tail, then make that node as new tail
        // 2 operations, constant
        prevNode.setNextNode(null);
        // 2 operations
        tail = prevNode;
        // decrement size
        // 1 operation
        size--;
    }

    /**
     * Handles new entry to the list.
     * Increment count by 1 if the name already exists, if not create & append a new tail node for the name.
     * @param name name of the bird
     */
    public void readEntry(String name){
    	// 2 operations
        Node tempNode = head;
        // 1 operation
        boolean nameExists = false;
        // 1 operation
        int i = 0;
        // iterate through each node in list
        // O(n) operations
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
        // 3 operations, constant
        if (!nameExists) {
            addTailNode(name, 1);
        }
    }

    /**
     * Remove nodes that equals to the given name
     * @param name entries with matching name to be excluded
     */
    public void removeEntry(String name){
        if (!isEmpty()){
            // start from the head node
            Node prevNode = head;
            // in order to successfully remove a node, we need to ensure the previous node is not to be excluded
            // O(n) operations
            while (prevNode.getName().equalsIgnoreCase(name)){
                // skips until prevNode is not equal to entry to remove
                prevNode = prevNode.getNextNode();
                // decrement size by 1
                size--;
                // terminate loop if end of list (ie - every node was to be excluded)
                if (prevNode==null) break;
            }
            // from the clean prevNode, check if the current node (called tempNode) is to be excluded
            // O(n) operations
            while(prevNode != null && prevNode.getNextNode() != null){
                Node tempNode = prevNode.getNextNode();
                // if current node is to be excluded, point the previous node to the node after
                if (tempNode.getName().equalsIgnoreCase(name)){
                    prevNode.setNextNode(tempNode.getNextNode());
                    // decrement size by 1
                    size--;
                }
                // move on to next node
                prevNode = prevNode.getNextNode();
            }
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
    	// 3 operations, constant
        if (tempHead == null || tempHead.getNextNode() == null) return null;
        // assign two nodes starting from head
        // 2 operations each, constant (4 operations on line 232)
        Node slow = tempHead; Node fast = tempHead;
        // fast node moves twice as fast than slow node. This way, by the time it reaches end of list,
        // slow node will be approximately at the middle of the linked list
        // O(n) operations
        while (fast.getNextNode() != null && fast.getNextNode().getNextNode() != null){
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
        }
        // return the middle node
        // 1 operation
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
    	// 2 operations each here, 4 constant)
        if (left == null) return right;
        if (right == null) return left;

        // set a temporary node to act as a smaller node, which will point to the bigger node
        // 1 operation
        Node tempNode;
        // if right node is greater or equal than left node
        // 2 operations, constant
        if (left.getCount() <= right.getCount()){
            // assign left as the temporary (smaller) node
        	// 2 operations constant
            tempNode = left;
            // recursion call to sort between the node next of left and right node
            // 4 operations, constant
            tempNode.setNextNode(sortedMerge(left.getNextNode(), right));
        } else{
            // vice versa for case when left node is greater than right node
        	// 2 operations, constant
            tempNode = right;
            // recursion call to sort between left node and the node next of right
            // 4 operations, constant
            tempNode.setNextNode(sortedMerge(left, right.getNextNode()));
        }
        // return the sorted & merged node
        // 1 operation, constant
        return tempNode;
    }

    /**
     * Implementation of merge sort in singly linked list
     * @param tempHead
     * @return sorted linked list
     */
    private Node mergeSort(Node tempHead){
    	// merge sorts run O(log n)
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
    	// 3 operations, constant
        head = mergeSort(head);
    }

    /**
     * Print the linked list items
     */
    public void printNodeItems(){
    	// 2 operations, constant
        Node tempNode = head;
        // 1 operation, constant
        int i=0;
        // O(n) here
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
    	// 1 operation, constant
        private String name;
        // 1 operation, constant
        private int count;
        // 2 operations, constant
        private Node nextNode = null;

        /**
         * Constructor for Node class
         *
         * @param name bird name
         * @param count number of times the bird appeared
         */
        public Node(String name, int count){
        	// 2 operations each, constant... total of 4 operations
            this.name = name;
            this.count = count;
        }

        /**
         * Get the bird name
         * @return bird's name
         */
        public String getName() {
        	// 1 operation, constant
            return name;
        }

        /**
         * Get the bird appearance count
         *
         * @return bird appearance count
         */
        public int getCount(){
        	// 1 operation, constant
            return count;
        }

        /**
         * Increment the count of the node by 1
         * 1 operation, constant
         */
        public void incrementCount() { count++; }

        /**
         * Get the next node current node is pointing at. Can be null.
         *
         * @return the node current node is pointing to
         * 1 operation, constant
         */
        public Node getNextNode(){ return nextNode; }

        /**
         * Set node pointer to the new node. Can be null
         * @param newNode the node to point to
         * 2 operations, constant
         */
        public void setNextNode(Node newNode){ nextNode = newNode; }
    }

    /**
     * Get instance of buffered reader to read a file
     * @param filepath file path
     * @return BufferedReader object to the file
     */
    public static BufferedReader getBufferedReader(String filepath) {
        try{
        	// 2 operations, constant
            File file = new File(filepath);
            // 4 operations, constant
            BufferedReader br = new BufferedReader(new FileReader(file));
            // 1 operation, constant
            return br;
        } catch (IOException e){
        	// 1 operation, constant
            e.printStackTrace();
        }
        // 1 operation, constant
        return null;
    }

    public static void main(String[] args) throws IOException{
        // initialize the singly linked list
    	// 2 operations, constant
        SinglyLinkedList list = new SinglyLinkedList();
        // entry file buffered reader
        // 2 operations, constant
        BufferedReader br_data = getBufferedReader("data/birds.txt");
        // exclusion file buffered reader
        // 2 operations, constant
        BufferedReader br_exclusion = getBufferedReader("data/birds2.txt");

        // for each line, add the bird to the linked list
        // 1 operation
        String line;
        // O(n)
        while ((line = br_data.readLine()) != null){
            list.readEntry(line);
        }
        // print and check unordered output
        // 1 operation, constant
        System.out.println("Unordered output: ");
        // 1 operation, constant
        list.printNodeItems();

        // sort the list in ascending order
        // 1 operation, constant
        list.sortList();
        // 1 operation, constant
        System.out.println("\n\nSorted output: ");
        // print and check the sorted output
        // 1 operation, constant
        list.printNodeItems();
        // O(n)
        while ((line = br_exclusion.readLine()) != null){
            list.removeEntry(line);
        }
        // 1 operation
        System.out.println("\n\nAfter exclusion: ");
        // print and check the sorted output
        // 1 operation
        list.printNodeItems();
    }

}
