package SinglyLinked;


public class Node<E> {
	String data;
	public E element; // reference to the element stored at this node
	public Node<E> next; // reference to the subsequent node in the list
    public int frequency;
	public Node(int frequency, String data) {
	
		this.frequency = frequency;
		this.data = data;
	}

	public E getElement() {
		return element;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> n) {
		next = n;
	}
	
    public String getName() {
        return data;
    }
    public int getFrequency(){
        return frequency;
    }

    public void incrementFrequency() { frequency++; }



}


