/**
* This class extends the BinarySearchTree class to allow the submission checks to have access to the
* internal fields of the BinarySearchTree class.
*/
public class P10SubmissionCheckBST extends BinarySearchTree<Integer> {

	/**
	* Helper method to print a warning message.
	* @param warningMessage the warning message to print
	*/
	static void printWarning(String warningMessage) {
		System.out.println("Warning submission fails check: " + warningMessage);
	}

	/**
	* Submission check that checks if the root node of a newly created tree is null.
	* @return true is check passes, false if it fails
	*/
	public boolean testEmptyRoot() {
    	// check if root node is null after instantiation
		if (this.root != null) {
			// if it is not, print a warning
        	printWarning("Root reference of BinarySearchTree right after creation is not null");
			// then let the check fail
			return false;
		}
		// check passes
		return true;
    }

	/**
	* Submission check that tests the insert method of the BinarySearchTree.
	* @return true is check passes, false if it fails
	*/
    public boolean insertIntoEmptyTree() {
		// if insert does not return true as expected
		if (!this.insert(4)) {
			// print warning
			printWarning("insert(4) into an empty tree does not return 'true' as expected");
			// fail check
			return false;
		}
		if (!this.insert(5)) {
			printWarning("insert(5) after inserting 4 into an empty tree does not return 'true' as expected");
			return false;
		}
		if (!this.insert(6)) {
			printWarning("insert(6) after inserting 4, then 5 into an empty tree does not return 'true' as expected");
			return false;
		}
		// check that the root is not null after insertions and that root only has right child
		if (this.root == null) {
			printWarning("Root reference of BinarySearchTree after inserting 4, 5, and 6 is null, but should not be");
			return false;
		}
		if (!this.root.data.equals(4)) {
			printWarning("Root node of BinarySearchTree does not contain 4 after inserting 4, 5, and 6, but should");
			return false;
		}
		if (this.root.down[0] != null) {
			printWarning("Root node of BinarySearchTree does have a non-null left child after inserting 4, 5, and 6, but should not");
			return false;
		}
		if (this.root.down[1] == null) {
			printWarning("Root node of BinarySearchTree does not have a non-null right child after inserting 4, 5, and 6, but should");
			return false;
		}
		// test passes
		return true;
	}

	/**
	* Test a simple left rotation not at the root node of the tree.
	* @return true is check passes, false if it fails
	*/
	public boolean testSimpleLeftRotation() {
		// insert keys into empty tree
		this.insert(4);
		this.insert(2);
		this.insert(1);
		this.insert(3);
		this.insert(6);
		this.insert(5);
		this.insert(7);

		// find nodes for 4, 2, and 3
		Node<Integer> four = this.root;
		Node<Integer> two = four.down[0];
		Node<Integer> three = two.down[1];
		// then rotate 3 and 2
		this.rotate(three, two);
    	
		// check level order string of tree after rotation
		if (!this.toLevelOrderString().equals("[ 4, 3, 6, 2, 5, 7, 1 ]")) {
			printWarning("After inserting 4, 2, 1, 3, 6, 5, 7 into an empty tree and rotating 3 and 2, level order is not [ 4, 3, 6, 2, 5, 7, 1 ], but should be");
			return false;
		}

		//test passes
		return true;
	}

	/**
	* Test a simple right rotation at the root node of the tree.
	* @return true is check passes, false if it fails
	*/
	public boolean testRightRotationAtRoot() {
		// insert keys into empty tree
		this.insert(4);
		this.insert(2);
		this.insert(1);

		// find nodes for 4, 2, and 1
        Node<Integer> four = this.root;
        Node<Integer> two = four.down[0];
        Node<Integer> one = two.down[0];
        // then rotate node with 2 and 4
		this.rotate(two, four);
		// check level order of tree after rotation
        if(!this.toLevelOrderString().equals("[ 2, 1, 4 ]")) {
			printWarning("After inserting 4, 2, 1 into an empty tree and rotating 2 and 4, level order is not [ 2, 1, 4 ], but should be");
			return false;
		}

		// test passes
		return true;
	}

	/**
	* This main method runs all checks and reports back to gitlab is one of them failed.
	*/
	public static void main(String[] args) {
		if (!(new P10SubmissionCheckBST()).testEmptyRoot()) {
			// stop execution and return a non 0 value which will cause gitlab to show that a test failed
			System.exit(1);
		}	
		if (!(new P10SubmissionCheckBST()).insertIntoEmptyTree()) {
			System.exit(1);
		}	
		if (!(new P10SubmissionCheckBST()).testSimpleLeftRotation()) {
			System.exit(1);
		}
		if (!(new P10SubmissionCheckBST()).testRightRotationAtRoot()) {
			System.exit(1);
		}
		// all test passe
		System.out.println("Submission passed basic scan");
	}

}
