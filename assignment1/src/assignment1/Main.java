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
        // TODO: add logic to check if data exists, then either increment the count or add new node
        // for each line, add the bird to the node
        String line;
        while ((line = br.readLine()) != null){
            // temporary check
            list.addTailNode(line);
        }
        list.printNodeItems();

    }



}
