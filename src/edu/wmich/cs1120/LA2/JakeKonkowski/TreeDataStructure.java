package edu.wmich.cs1120.LA2.JakeKonkowski;

/**
 * Class that be used to instantiate nodes and form a binary tree.
 * @author Jake Konkowski
 *
 */
public class TreeDataStructure implements INode {
	
	/**
	 * ID of this node.
	 */
	private String ID;
	
	/**
	 * Parent node of this node.
	 */
	private INode parent;
	
	/**
	 * Child of this node on the left.
	 */
	private INode leftChild;
	
	/**
	 * Child of this node on the right.
	 */
	private INode rightChild;
	
	/**
	 * Constructs an empty TreeDataStructure object
	 */
	public TreeDataStructure() {

	}
	
	/**
	 * Constructs a TreeDataStructure object with an ID.
	 * @param ID ID of the new TreeDataStructure object.
	 */
	public TreeDataStructure(String ID) {
		this.ID = ID;
	}
	
	/**
	 * Constructs a TreeDataStructure object with an ID and
	 * parent object of type INode.
	 * @param ID ID of the new TreeDataStructure object.
	 * @param parent Parent object of the new TreeDataStructure object.
	 */
	public TreeDataStructure(String ID, INode parent) {
		this.ID = ID;
		this.parent = parent;
	}
	
	/**
	 * Constructs a TreeDataStructure object with an ID,
	 * parent object of type INode, and a left child
	 * object of type INode.
	 * @param ID ID of the new TreeDataStructure object.
	 * @param parent Parent object of the new TreeDataStructure object.
	 * @param leftChild Left child of the new TreeDataStructure object.
	 */
	public TreeDataStructure(String ID, INode parent, INode leftChild) {
		this.ID = ID;
		this.parent = parent;
		this.leftChild = leftChild;
	}
	
	/**
	 * Constructs a TreeDataStructure object with an ID,
	 * parent object of type INode, a left child
	 * object of type INode, and a right child object
	 * of type INode.
	 * @param ID ID of the new TreeDataStructure object.
	 * @param parent Parent object of the new TreeDataStructure object.
	 * @param leftChild Left child of the new TreeDataStructure object.
	 * @param rightChild Right child of the new TreeDataStructure object.
	 */
	public TreeDataStructure(String ID, INode parent, INode leftChild, 
			INode rightChild) {
		this.ID = ID;
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public boolean addChild(String ID, String parentID) {
		
		if (ID.equals(parentID)) {
			System.out.println("Cannot add a node of same ID to parent ID.");
			return false;
		}
		
		//If the node has no ID, we created it through this method,
		//so we should assign it's ID and return true because we
		//succesfully created the node.
		if (this.getId() == null) {
			this.setID(ID);
			return true;
		}

		if (this.getId().equals(parentID)) {
			
			if (this.hasLeftChild() && this.hasRightChild()) {
				System.out.println("Parent already has 2 children.");
				return false;
			}
			
			if (this.hasLeftChild()) {
				//Creates an empty node to edit later.
				this.rightChild = new TreeDataStructure();
				return this.rightChild.addChild(ID, parentID);
			}
			
			//Creates an empty node to edit later.
			this.leftChild = new TreeDataStructure();
			return this.leftChild.addChild(ID, parentID);
			
		}
		
		if (this.find(parentID) != null) {
			return this.find(parentID).addChild(ID, parentID);
		} else {
			return false;
		}
		
	}

	public INode find(String value) {
		
		INode result = null;
		
		if (value.equals(this.ID)) {
			return this;
		}
		
		//Check the left side of the tree.
		if (this.leftChild != null) {
			result = this.leftChild.find(value);
		}
		
		//If we found it, that's great! We're done!
		if (result != null) {
			return result;
		}
		
		//Check right side of the tree.
		if (this.rightChild != null) {
			result = this.rightChild.find(value);
		}
		
		//Return whatever we've got
		return result;
	}

	public INode getParent() {
		return parent;
	}
	
	public int size() {
		
		int size = 1;
		
		//Add the size of the left side of the tree
		if (this.hasLeftChild()) {
			size += this.leftChild.size();
		}
		
		//Add the size of the right side of the tree
		if (this.hasRightChild()) {
			size += this.rightChild.size();
		}
		
		//Return the size of the happy tree family
		return size;
	}
	
	//toString and getID are the same and return ID
	public String toString() {
		return this.ID;
	}
	
	public String getId() {
		return this.ID;
	}

	public void printTree() {
		
		if (this.hasLeftChild() && this.hasRightChild()) {
			System.out.printf("%s %s %s\n", this.toString(), this.leftChild.toString(),
					this.rightChild.getId());
			
			this.leftChild.printTree();
			this.rightChild.printTree();
		} else if (this.hasLeftChild()) {
			System.out.printf("%s %s\n", this.toString(), this.leftChild.toString());
			
			this.leftChild.printTree();
		} else if (this.hasRightChild()) {
			System.out.printf("%s %s\n", this.toString(), this.rightChild.toString());
			
			this.rightChild.printTree();
		} else {
			System.out.printf("%s\n", this.toString());
		}
		
	}
	
	/**
	 * 
	 * @return Returns the left child of this node.
	 */
	public INode getLeftChild() {
		return this.leftChild;
	}
	
	/**
	 * 
	 * @return Returns the right child of this node.
	 */
	public INode getRightChild() {
		return this.rightChild;
	}
	
	/**
	 * 
	 * @return Returns true if this node has a parent.
	 */
	public boolean hasParent() {
		return (this.parent != null);
	}
	
	/**
	 * 
	 * @return Returns true if this node has a left child.
	 */
	public boolean hasLeftChild() {
		return (this.leftChild != null);
	}
	
	/**
	 * 
	 * @return Returns true if this node has a right child.
	 */
	public boolean hasRightChild() {
		return (this.rightChild != null);
	}
	
	/**
	 * 
	 * @param ID ID to set this node to.
	 */
	private void setID(String ID) {
		this.ID = ID;
	}

}
