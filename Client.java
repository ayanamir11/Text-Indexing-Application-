package pa5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver for the index maker project
 * 
 * @author Ayan Amir
 * @version 11/14/2025
 */
public class Client
{
    public static void main(String[] args)
    {
    	makeIndex("pa5/uscons.txt"); //replace with correct path
    }

    // instance variables

    private BinarySearchTree<IndexAdd> index;
    private BinarySearchTree<String> dictionary;
    private int page;

    /*
     * constructor -  Makes an Index, dictrionary and initializes page to 1
     */
    public Client(){
        index = new BinarySearchTree<>();
        dictionary = new BinarySearchTree<>();
        page = 1;
    }

    /**
     * Makes an index out of fileName. Gradescope needs this function.
     * 
     * @param fileName path to text file that you want to index
     */
    public static void makeIndex(String fileName) {
        Client client = new Client();
        client.processFile(fileName);
    }


    private void processFile(String fileName) {
        Scanner scanner = null;
        
        try {
            scanner = new Scanner(new File(fileName));
            scanner.useDelimiter("[^a-zA-Z#]+");
            
            while (scanner.hasNext()) {
                String token = scanner.next();
                String cleanedToken = cleanToken(token);
                processToken(cleanedToken);
            }
            
            // Print results
            printIndex();
            System.out.println();
            printDictionary();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Cleans a token from input file in the following ways:
     * 1) removes everything from text except letters and #
     * 2) is case sensitive
     * @param token read from file
     * @return cleaned token
     */
    private String cleanToken(String token){
        if(token == null){
            return "";
        }

        token = token.replaceAll("[^a-zA-Z#]", "");

        return token;
    }

    private void processToken(String token){
        
        // first two if-statements determine whether or not to treat
        // the token as a word.
        if(token.equals("#")){
            page++;
            return;
        }

        if(token.length() < 3){
            return;
        }

        // if word is already in the dictioanry, ignore it
        if(dictionary.search(token)){
            return;
        }

      // Search for the word in the index
        IndexAdd existingEntry = findInIndex(token);
        
        if (existingEntry != null) {
            // Word already in index so try adding the current page
            boolean added = existingEntry.addPage(page);
            
            // For the case when pagelist is full
            if (!added && existingEntry.isFull()) {
                // Print the deletion message
                System.out.println("Deleting '" + existingEntry + "' from index.");
                
                // Delete from index and add to dictionary
                index.delete(existingEntry);
                dictionary.insert(token);
            }
        } else {
            // Word is not in index - create new entry and insert
            IndexAdd newEntry = new IndexAdd(token, page);
            index.insert(newEntry);
        }
        
    }

    /**
     * helper method to find an IndexAdd object in the index by word
     * @param word the word to search for
     * @return the IndexAdd object if found, null otherwise
     */
    private IndexAdd findInIndex(String word) {
        // Create a temporary IndexAdd object to search with
        IndexAdd dummy = new IndexAdd(word, 0);
        return index.find(dummy);
    }


    /**
     * Prints the complete index
     */
    private void printIndex() {
        String indexString = index.toString();
        System.out.println("Index:");
        printEntries(indexString);
    }

    /**
     * Prints the complete dictionary
     */
    private void printDictionary() {
        String dictString = dictionary.toString();
        System.out.println("Dictionary:");
        printEntries(dictString);
    }

    /*
     * Helper method to print and make code cleaner
     * @param treeStr : string representation of tree
     */
    private void printEntries(String treeStr) {
        
        treeStr = treeStr.replace("(", "").replace(")", "");
        
        // Split on 2 or more spaces (BST uses "  " around each value)
        String[] parts = treeStr.split("\\s{2,}");
        
        // Print each non-empty part
        for (String part : parts) {
            part = part.trim();
            if (!part.isEmpty()) {
                System.out.println(part);
            }
        }
    }
}
