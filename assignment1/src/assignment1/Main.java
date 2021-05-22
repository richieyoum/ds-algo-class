package assignment1;

import java.io.*;

public class Main {

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

    public static void main(String args[]) throws IOException {
        // initialize the singly linked list
        SinglyLinkedList list = new SinglyLinkedList();
        // read file from directory
        BufferedReader br = getBufferedReader("data/birds.txt");
        // for each line, add the bird to the node
        String line;
        while ((line = br.readLine()) != null){
            list.readEntry(line);
        }

        list.sortList();

        list.printNodeItems();
    }

}
