package pa5;

/**
 * 
 * 
 * @author Chris Fernandes, Kristina Striegnitz, TJ Schlueter
 * @version Fall 2025
 */
public class BinarySearchTree<T extends Comparable<T>> {

	private BSTNode<T> root;
	
	/**
	 * Default constructor
	 */
	public BinarySearchTree() {
		root = null;
	}
    
    /**
	 * inserts an new value into this BST    
	 * @param newValue value to insert
	 */
	public void insert(T newValue) {
		root = insert(root,newValue);
	}

	/**
	 * inserts value into tree rooted at subroot
	 * 
	 * @param subroot  subroot of tree to insert into 	//root node of a sub tree
	 * @param value  the value to insert
	 * @return   root of the subtree I've just finished inserting into
	 */
	private BSTNode<T> insert(BSTNode<T> subroot, T value) {
		if (subroot==null){
			return new BSTNode<T>(value);
		}
		else if (value.compareTo(subroot.key) > 0){
			subroot.rlink = insert(subroot.rlink,value);
			return subroot;
		}
		else {
			subroot.llink = insert(subroot.llink, value);
			return subroot;
		}
	}
	
	/**
	 * deletes value from tree.  If value not there, do nothing.
	 * @param value  value to delete
	 */
	public void delete(T value) {
		root = delete(root, value);
	}
	
	/**
	 * deletes value from tree rooted at subroot
	 * @param subroot  root of tree to be deleted from
	 * @param value  element to delete
	 * @return pointer to tree rooted at subroot that has value removed from it
	 */
	private BSTNode<T> delete(BSTNode<T> subroot, T value) {
		/**
		 * if subroot is an empty tree
		 *     return null
		 * else if victim is on the left of subroot
		 *     subroot's left link must become what recursion on subroot's left child gives you
		 * else if victim is on the right of subroot
		 *     subroot's right link must become what recursion on subroot's rlink gives you
		 * else
		 *     victim is found!
		 *     case 1) victim is a leaf
		 *         return null
		 *     case 2) victim has exactly one (right) subtree
		 *         return pointer to that right subtree
		 *     (case 2a - take care of just left subtree only)
		 *     case 3) victim has two subtrees
		 *         pick a replacement (largest value in the left subtree)
		 *         move the data from replacement node to victim node
		 *         delete the replacement
		 */
		if (subroot == null){
			return null;
		}
		else if (value.compareTo(subroot.key) < 0){
			subroot.llink = delete(subroot.llink, value);
			return subroot;
		}
		else if (value.compareTo(subroot.key) > 0){
			subroot.rlink = delete(subroot.rlink, value);
			return subroot;
		}
		
		// the value to be deleted is found

		else{

			if(subroot.isLeaf()){
				return null;
			}
			else if(subroot.hasRightChildOnly()){
				return subroot.rlink;
			}
			else if (subroot.hasLeftChildOnly()){
				return subroot.llink;
			}

			else {
				BSTNode<T> predec = maxNode(subroot.llink);
				subroot.key = predec.key;
				subroot.llink = delete(subroot.llink, predec.key);
				return subroot;
			}
		}
	}
	

    /**
     * checks whether the target value is in the tree
     * @return true or false to indicate whether the target value is in the tree
     */
    public boolean search(T target) {

		BSTNode<T> curr = root;

		while(curr != null){
			int comparingValue = target.compareTo(curr.key);

			if(comparingValue == 0){
				return true;
			}
			else if(comparingValue > 0){
				curr = curr.rlink;
			}
			else{
				curr = curr.llink;
			}
		}
		return false;
    }


	/**
	 * returns tree as printable string
	 * @return tree in string format with form (left subtree) value (right subtree)
	 */
	public String toString(){
		return toString(root);
	}

	/**
	 * recursive helper method for toString()
	 *
	 * @param N root of subtree to make into a string
	 * @return string version of tree rooted at N
	 */
	private String toString(BSTNode<T> N){
		String ret = "";
		if (N != null){
			ret += "(";
			ret += toString(N.llink);
			ret += "  " + N + "  ";
			ret += toString(N.rlink);
			ret += ")";
		}
		return ret;
	}

	// helper methods

	/**
	 * this method helps find the predecessor i.e the max
	 * node in the left subtree.
	 * @param subroot
	 * @return
	 */
	private BSTNode<T> maxNode(BSTNode<T> subroot){
		if(subroot == null){
			return null;
		}

		BSTNode<T> current = subroot;

		while(current.rlink!= null){
			current = current.rlink;
		}
		return current;
	}

	/**
	 * Checks for an empty tree. 
	 * @return
	 */
	public boolean isEmpty(){
		return root == null;
	}

	public void clear(){
		this.root = null;
	}

	/**
	 * Finds and returns a specific element in the BST
	 * @param target element to be found
	 * @return the element if it is found, null if not
	 */
	public T find(T target){
		return find(root,target);
	}

	/**
	* Helper method for find
 	* @param subroot root of subtree to search
 	* @param target element to find
 	* @return the element if found, null if not
 	*/
	private T find(BSTNode<T> subroot, T target){
		if(subroot == null) {
			return null;
		}

		int comaprisonVal = target.compareTo(subroot.key);

		if(comaprisonVal == 0){
			return subroot.key;
		}
		else if(comaprisonVal < 0){
			return find(subroot.llink, target);
		}
		else{
			return find(subroot.rlink, target);
		}

	}

}