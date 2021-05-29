package assignment1;
/*	Count of method operations.
 * 	3c + 1c + 1c + 1c + 
 * 
 */


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
     * @return number of elements in the linked list
     */
    public int getSize(){
    	// 1 operation, constant
        return size;
    }

    /**
     * @return boolean indicating whether list is empty
     */
    public boolean isEmpty(){
    	// 1 operation, constant
        return size == 0;
    }

    /**
     * @return list head node
     */
    public Node getHeadNode(){
    	// 1 operation , constant
        return head;
    }

    /**
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
    	// addHeadNode: 11c
        // create new node with the given bird name
    	// get value of name and count, create a new node, assign it to newNode
        Node newNode = new Node(name, count);
        
        // point to the current head node
        newNode.setNextNode(head);
        
        // set the newly created node as head
        head = newNode;
        
        // if list was empty, the head (aka: the newly created node) will also be a tail
        if (isEmpty()){
            tail = newNode;
        }
        // increment the size of the list
        size++;
    }

    /**
     * Add a new tail node to the list.
     * @param name new bird name
     */
    public void addTailNode(String name, int count){
    	// addTailNode: 12c
        // create new node with the given bird name
        Node newNode = new Node(name, count);
        
        // if list was empty, the tail will also be a head
        if (isEmpty()){
            head = newNode;
        }
        // otherwise point the previous tail to the newly created node
        else{
            tail.setNextNode(newNode);
        }
        // set the newly created node as the tail
        tail = newNode;
        // increment the size of the list
        size++;
    }

    /**
     * Remove the head node
     */
    public void removeHeadNode(){
    	// removeHeadNode: 7c
        // set head to the node previous head was pointing to. This effectively removes old head from list
        head = head.getNextNode();
        // decrement the size of the list
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
    	// removeTailNode: 12c + O(n)
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
        // decrement the size of the list
        size--;
    }

    /**
     * Handles new entry to the list.
     * Increment count by 1 if the name already exists, if not create & append a new tail node for the name.
     * @param name name of the bird
     */
    public void readEntry(String name){
    	// readEntry: 7c + O(n)
    	// we need to start at the beginning of the list, so read the head
        Node tempNode = head;
        boolean nameExists = false;
        int i = 0;
        // iterate through each node in list
        while (i < size){
        	// if the list is empty we don't need to be reading through it
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
        // if the name doesn't exist in the list, then create a new node and append it. Set its view count to 1.
        if (!nameExists) {
            addTailNode(name, 1);
        }
    }

    /**
     * Remove nodes that equals to the given name. This is for birds2.txt, and removing the non-indigenous birds from the list
     * @param name entries with matching name to be excluded
     */
    public void removeEntry(String name){
    	// removeEntry: 3c + O(2n)
        if (!isEmpty()){
            // start from the head node
            Node prevNode = head;
            // in order to successfully remove a node, we need to ensure the previous node is not to be excluded
            while (prevNode.getName().equalsIgnoreCase(name)){
                // skips until prevNode is not equal to entry to remove
                prevNode = prevNode.getNextNode();
                // decrement the list size by 1
                size--;
                // terminate loop if end of list (ie - every node was to be excluded)
                if (prevNode == null) break;
            }
            // from the clean prevNode, check if the current node (called tempNode) is to be excluded
            while(prevNode != null && prevNode.getNextNode() != null){
                Node tempNode = prevNode.getNextNode();
                // if current node is to be excluded, point the previous node to the node after
                if (tempNode.getName().equalsIgnoreCase(name)){
                    prevNode.setNextNode(tempNode.getNextNode());
                    // decrement the list size by 1
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
    	// getMiddleNode: 8c + O(n)
        // return null in the case given head (or its next item) is null. Happens when the specific list has no element
        // or only contains 1 element
        if (tempHead == null || tempHead.getNextNode() == null) return null;
        // assign two nodes starting from head
        Node slow = tempHead; Node fast = tempHead;
        // fast node moves twice as fast as the slow node. This way, by the time it reaches end of list,
        // slow node will be approximately at the middle of the linked list
        while (fast.getNextNode() != null && fast.getNextNode().getNextNode() != null){
        	// slow moves one node
            slow = slow.getNextNode();
            // fast moves two nodes
            fast = fast.getNextNode().getNextNode();
        }
        // return the middle node
        return slow;
    }

    /**
     * Recursive calls to sort all items between the two linked list, and then merge them together
     * @param left		: a node to be compared with right node
     * @param right 	: a node to be compared with left node
     * @return merged	: node from left and right nodes in ascending order
     */
    private Node sortedMerge(Node left, Node right){
    	// sortedMerge: 20c OR O(Log(n))
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
    	// mergeSort: O(log n)
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
    	// sortList: 3c
        // set the head to the sorted list
        head = mergeSort(head);
    }

    /**
     * Print the linked list items
     */
    public void printNodeItems(){
    	// printNodeItems: 3c + 0(n)
    	// need to start at the beginning of the list
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
    	// Node: 4c

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
        	// Node: 4c
            this.name = name;
            this.count = count;
        }

        /**
         * Get the bird name
         * @return bird's name
         */
        public String getName() {
        	// getName: 1c
            return name;
        }

        /**
         * Get the bird appearance count
         * @return bird appearance count
         */
        public int getCount(){
        	// getCount: 1c
            return count;
        }

        /**
         * Increment the count of the node by 1
         * incrementCount: 2c
         */
        public void incrementCount() { count++; }

        /**
         * Get the next node current node is pointing to. Can be null.
         *
         * @return the node current node is pointing to
         * getNextNode: 1c
         */
        public Node getNextNode(){ return nextNode; }

        /**
         * Set node pointer to the new node. Can be null
         * @param newNode the node to point to
         * setNextNode: 2c
         */
        public void setNextNode(Node newNode){ nextNode = newNode; }
    }

    /**
     * Get instance of buffered reader to read a file
     * @param filepath file path
     * @return BufferedReader object to the file
     */
    public static BufferedReader getBufferedReader(String filepath) {
    	// BufferedReader: 9c
        try{
            File file = new File(filepath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br;
        } catch (IOException e){
        	// if there is a problem reading the file, print out the line number/method
        	// where the error occured for debugging
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException{
    	// main: 14c + O(2n)
        // initialize the singly linked list
        SinglyLinkedList list = new SinglyLinkedList();
        // entry file buffered reader
        BufferedReader br_data = getBufferedReader("data/birds.txt");
        // exclusion file buffered reader
        BufferedReader br_exclusion = getBufferedReader("data/birds2.txt");

        // for each line, add the bird to the linked list
        String line;
        while ((line = br_data.readLine()) != null){
            list.readEntry(line);
        }
        // print and check unordered output
        System.out.println("Unordered output: ");
        list.printNodeItems();

        // sort the list in ascending order
        list.sortList();
        System.out.println("\n\nSorted output: ");
        // print and check the sorted output
        list.printNodeItems();
        while ((line = br_exclusion.readLine()) != null){
            list.removeEntry(line);
        }
        System.out.println("\n\nAfter exclusion: ");
        // print and check the sorted output
        list.printNodeItems();
    }
}