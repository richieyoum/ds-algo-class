package SinglyLinked;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainGeneric {
    public static BufferedReader getBufferedReader(String filepath) {
        try{
            File file = new File(filepath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    

	public static void main(String[] args) throws IOException {
		SinglyLinkedList <String> list = new SinglyLinkedList<String>();
		//SinglyLinkedList list = new SinglyLinkedList();
		   BufferedReader br_data = getBufferedReader("C://Users/KarimBeainy/eclipse-workspace/assignement_refactor/src/data/birds.txt");
	        // exclusion file buffered reader
	        //BufferedReader br_exclusion = getBufferedReader("C://Users/KarimBeainy/eclipse-workspace/SinglyLinkedList/src/data/birds2.txt");
	        String line;
	        while ((line = br_data.readLine()) != null){
	            list.readEntry(line);
	            //System.out.println(list);
	            
	            
	            
	        }
	        System.out.println("Unordered output: ");
	        list.printNodeItems();
	}

}