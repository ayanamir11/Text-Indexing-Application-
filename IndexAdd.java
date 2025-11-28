package pa5;

/**
 * This class allows a word to be added to the Index along with
 * the 4 distinct page numbers it appears on. If a word appears
 * on the 5th page, it is removed from the Index and added to
 * the dictionary.
 * Invariants:
 * 1) at most 4 distinct page numbers for one word.
 * 2) page numbers are arranged in ascending order.
 */

public class IndexAdd implements Comparable<IndexAdd> {
    
    // Instance variables 
    private String word; // the word to be added
    private Pagelist pages; // the page numbers the word appears on

    // Constructor
    public IndexAdd(String word, int page){
        this.word = word;
        this.pages = new Pagelist();
        this.pages.addPage(page);
    }

    public String getWord(){
        return word;
    }

    public Pagelist getPages(){
        return pages;
    }

    @Override
    public String toString(){
        String result = "";
        result += word + " ";
        result += pages.toString();
        
        return result;
    }

    @Override
    public int compareTo(IndexAdd other){
        return this.word.compareTo(other.word);
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if(!(other instanceof IndexAdd)){
            return false;
        }

        // casting to IndexAdd type
        IndexAdd idx = (IndexAdd) other;
        
        return this.word.equals(idx.word);
    }

    // wrapper methods that would be used in Client.java

    public boolean addPage(int page){
        
        // If the same word appears on the same page again
        if(pages.contains(page)){
            return false;
        }

        // If the word has appeared on 4 distinct pages.
        if(pages.isFull()){
            return false;
        }

        // If both cases above are not satisfied, add the word
        pages.addPage(page);
        return true;
    }

    public boolean isFull(){
        return pages.isFull();
    }
}
