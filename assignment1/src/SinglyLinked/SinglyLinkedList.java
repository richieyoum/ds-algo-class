package SinglyLinked;



public class SinglyLinkedList<E> {
//... (nested Node class goes here)
// instance variables of the SinglyLinkedList
	public Node<E> head = null; // head node of the list (or null if empty)
	public Node<E> tail = null; // last node of the list (or null if empty)
	public int size = 0; // number of nodes in the list

	public SinglyLinkedList() {
	} // constructs an initially empty list
		// access methods

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() { // returns (but does not remove) the first element
		if (isEmpty())
			return null;
		return head.getElement();
	}

	public E last() { // returns (but does not remove) the last element
		if (isEmpty())
			return null;
		return tail.getElement();
	}

	// update methods
	public void addFirst(int frequency, String data) { // adds element e to the front of the list
		Node<E> head = new Node<E>(frequency, data); // create and link a new node
		if (size == 0)
			tail = head; // special case: new node becomes tail also
		size++;
	}

	public void addLast(String data) { // adds element e to the end of the list
		Node<E> newest = new Node<E>(size, data); // node will eventually be the tail
		if (isEmpty())
			head = newest; // special case: previously empty list
		else
			tail.setNext(newest); // new node after existing tail
		tail = newest; // new node becomes the tail
		size++;
	}

	
	public E removeFirst( ) { // removes and returns the first element
		 if (isEmpty( )) return null; // nothing to remove
		 E answer = head.getElement( );
		 head = head.getNext( ); // will become null if list had only one node
		 size--;
		 if (size == 0)
		 tail = null; // special case as list is now empty
		 return answer;
		 }
	
    public void readEntry(String data){
        Node<E> tempNode = head;
        boolean nameExists = false;
        int i = 0;
        // iterate through each node in list
 
        	 while (i < size){
                 //if (isEmpty()) break;
                 // increment count by 1 if name already exists in the list
                 if (data.equalsIgnoreCase(tempNode.getName())){
                     tempNode.incrementFrequency();
                     nameExists = true;
                     break;
                 }
			 
            tempNode = tempNode.getNext();
            i++;
        }
        // if name didn't exist yet, then create a new node and append it. Set count to 1.
        if (!nameExists) {
        	addLast(data);
        }
    }
    public void printNodeItems(){
        Node<E> tempNode = head;
        int i=0;
        while (i < size){
            System.out.println(tempNode.getName() + " " + tempNode.getFrequency());
            tempNode = tempNode.getNext();
            i++;
        }
    }

    
	 
}