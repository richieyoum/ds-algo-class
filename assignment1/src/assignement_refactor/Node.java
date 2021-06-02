package assignement_refactor;



public class Node {
	  
    String data;
    int frequency;
    Node next;

    // Constructor
	
    public Node(String data, int frequency){
        this.data = data;
        this.frequency = frequency;
    }


    /**
     * Get the bird name
     * @return bird's name
     */
    public String getName() {
        return data;
    }

    /**
     * Get the bird appearance count
     *
     * @return bird appearance count
     */
    public int getFrequency(){
        return frequency;
    }

    /**
     * Increment the count of the node by 1
     */
    public void incrementFrequency() { frequency++; }

    /**
     * Get the next node current node is pointing at. Can be null.
     *
     * @return the node current node is pointing to
     */
    public Node getNextNode(){ return next; }
    
    
    public void setNextNode(Node newNode){ next = newNode; }
}
