package pa5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Demonstration of using Java's Scanner class to read input 
 * texts word by word, stripping away all punctuation and numeric characters.
 */
public class FileReadingDemo {

    private Scanner myReader;

    /**
     * Initializes the demo with the given file.
     * @param filename
     */
    public FileReadingDemo (String filename) {
        try {
            myReader = new Scanner(new File(filename));
            myReader.useDelimiter("[^a-zA-Z#]+");
        } catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
    }

    /**
     * Reads and prints the file word by word.
     */
    public void run () {
        while (myReader.hasNext()) {
            String nextExpression = myReader.next();
            System.out.println(nextExpression);
        }
    }
}
