package pa5;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * 
 * Write a description of class StackTest here.
 * 
 * @author Ayan Amir 
 * @version 11/6/2025
 *
 */
public class AyanAmirBSTTest {

    @Rule
    public Timeout timeout = Timeout.millis(100);

    private BinarySearchTree<Integer> BST = new BinarySearchTree<>();

    @Before
    public void setUp() throws Exception{
        BST = new BinarySearchTree<>();
    }

    @After
    public void tearDown() throws Exception{
        BST.clear();
    }


    @Test
    public void testInsert(){
        BST.insert(5);
        BST.insert(3);
        BST.insert(7);

        assertEquals("((  3  )  5  (  7  ))", BST.toString());
        
    }

    @Test
    public void testInsertDuplicate(){
        BST.insert(5);
        BST.insert(5);

        assertEquals("((  5  )  5  )", BST.toString());
    }

    @Test
    public void testDelete(){
        BST.insert(5);
        BST.insert(3);
        BST.insert(7);

        BST.delete(7);

        assertEquals("((  3  )  5  )", BST.toString());
    }

    @Test
    public void testDeleteLeafNode(){
        BST.insert(5);
        BST.insert(3);

        BST.delete(3);

        assertEquals("(  5  )", BST.toString());
    }

    @Test
    public void testDeleteInEmptyTree(){

        BST.delete(5);
        assertEquals("", BST.toString());
    }

    @Test // tests if it deletes a non-existent element
    public void testDeleteNonExistent(){
        BST.insert(5);
        BST.insert(3);
        BST.insert(7);

        BST.delete(9);

        assertEquals("((  3  )  5  (  7  ))", BST.toString()); // tree stays the same
    }

    @Test // deleting with right child only
    public void testDeleteRightChild(){
        BST.insert(5);
        BST.insert(3);
        BST.insert(7);
        BST.insert(2);
        BST.insert(4);
        BST.insert(8);

        BST.delete(8);

        assertFalse(BST.search(8)); // deletes the only right child of 7 (i.e 8)
    }

    @Test // deleting a node with 2 children
    public void testDeleteWithTwoChildren(){
        BST.insert(5);
        BST.insert(3);
        BST.insert(7);
        BST.insert(2);
        BST.insert(4);
        BST.insert(8);

        BST.delete(5);

        assertFalse(BST.search(5)); // 5 no longer exists
        assertTrue(BST.search(4)); // 4 exists
        assertEquals("(((  2  )  3  )  4  (  7  (  8  )))", BST.toString()); // tree structure
    }

    @Test
    public void testSearch(){
        BST.insert(5);
        BST.insert(3);
        BST.insert(7);

        assertTrue(BST.search(5));
        assertTrue(BST.search(7));
        assertTrue(BST.search(3));
        assertFalse(BST.search(4)); // searches for a non-existent element
    }

    // testing search() in an empty BST
    @Test 
    public void testSearchinEmpty(){
        assertFalse(BST.search(2));
    }

    // testing isEmpty()
    @Test 
    public void testIsEmpty(){
        assertTrue(BST.isEmpty());
    }

    // testing clear()
    @Test
    public void testClear(){
        BST.insert(5);
        BST.insert(3);
        BST.insert(7);

        BST.clear();
        assertTrue(BST.isEmpty());
    }
}
