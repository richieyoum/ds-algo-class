package assignement_refactor;



public class SinglyLinkedList {
	
      private Node head = null;
      private Node tail = null; 
      private int size;
      
      public SinglyLinkedList(){
   	   
	  }
      public boolean isEmpty(){
          return size == 0;
      }
      public void addNode(String data, int frequency){
          // create new node with the given bird name
          Node newNode = new Node(data, frequency);
      
          /* 4. If the Linked List is empty, then make the
                 new node as head */
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
      public void readEntry(String name){
          Node tempNode = head;
          boolean nameExists = false;
          int i = 0;
          // iterate through each node in list
          while (i < size){
              //if (isEmpty()) break;
              // increment count by 1 if name already exists in the list
        	  if (name.equals(tempNode.getName())){
                  tempNode.incrementFrequency();
                  nameExists = true;
                  break;
              }
              tempNode = tempNode.getNextNode();
              i++;
          }
          // if name didn't exist yet, then create a new node and append it. Set count to 1.
          if (!nameExists) {
        	  addNode(name, 1);
          }
      }
      public void printNodeItems(){
          Node tempNode = head;
          int i=0;
          while (i < size){
              System.out.println(tempNode.getName() + " " + tempNode.getFrequency());
              tempNode = tempNode.getNextNode();
              //System.out.println(tempNode.getName());
              i++;
          }
      }

}
