package pa5;

/**
 * For a specific word, it stores upto 4 page numbers
 * it appears on. Doesn't add a page number if same word
 * appears more than once on a page. 
 * Invariants:
 * 1) pageNumbers has size 4 at all costs.
 * 2) page numbers are arranged in ascending order.
 * 3) No duplicate page number for any page number.
 */

public class Pagelist {

    int[] pageNumbers = new int[4];
    int size; // how many pages are currently stored
    
    public Pagelist(){
        this.pageNumbers = new int[4];
        size = 0;
    }

    public void addPage(int pageNum){
        if(size < 4 && !contains(pageNum)){
            pageNumbers[size] = pageNum;
            size++;
        }
    }

    /**
     * Checks if page number is already recorded
     * @param pageNum
     * @return
     */
    public boolean contains(int pageNum){
        for(int i = 0; i < size; i++){
            if(pageNumbers[i] == pageNum){
                return true;
            }
        }
        return false;
    }

    public boolean isFull(){
        return size == 4;
    }

    /*
     * Converts to String
     */
   public String toString(){
    if(size == 0){
        return "{}";
    }
    
    String result = "{";
    for(int i = 0; i < size - 1; i++){
        result += pageNumbers[i];
        result += ", ";
    }
    result += pageNumbers[size - 1]; 
    result += "}";
    return result;
    }

}
