package edu.wmich.cs1120.LA2.JakeKonkowski;

import java.util.Scanner;

/**
 * 
 * @author Jake Konkowski
 *
 */
public class BinaryTree {
	
	/**
	 * Scanner attribute used to get user input
	 */
	private static Scanner sc;

	public static void main(String[] args) {
		
		//Instantiate new node as a root
		TreeDataStructure root = new TreeDataStructure("A");
		
		//Add the children
		root.addChild("B", "A");
		root.addChild("C", "A");
		root.addChild("D", "B");
		root.addChild("E", "B");
		root.addChild("F", "C");
		root.addChild("G", "C");
		root.addChild("H", "D");
		root.addChild("I", "D");
		root.addChild("J", "E");
		root.addChild("K", "E");
		root.addChild("L", "F");
		
		//Display the current tree and size
		root.printTree();
		System.out.printf("There are %s nodes in this tree.\n", root.size());
		
		//-1 isn't an option so we just set this as -1 since int can't be null
		int option = -1;
		
		//Option 0 will complete the program
		while (option != 0) {
			
			printMenu();
			
			option = promptForMenuOption();
			
			//I hate switches. They make code harder to read.
			//They are basically glorified if statements with more work.
			
			if (option == 1) {
				addNode(root);
			}
			
			if (option == 2) {
				getTreeSize(root);
			}
			
			if (option == 3) {
				findNode(root);
			}
			
		}
		
	}

	
	/**
	 * Method to print menu options for the user to choose from.
	 */
	public static void printMenu() {
		System.out.println("\nPlease select from one of the following options:");
		System.out.println("1. Add Node");
		System.out.println("2. Tree Size");
		System.out.println("3. Find Node");
		System.out.println("0. Exit");
		System.out.print("-> ");
	}
	
	/**
	 * This method acts as a menu option. When selected it will
	 * add a child node to a parent of the user's choice. If the
	 * parent doesn't exist or has two children, an error will be
	 * displayed.
	 * @param root The root of the binary tree add a node to.
	 */
	public static void addNode(INode root) {
		System.out.println("\nPlease input the node you want to add:");
		String newNodeID = promptForCharacter();
		System.out.printf("\nPlease input the parent node of %s:\n", newNodeID);
		String parentNodeID = promptForCharacter();
		
		if (root.find(parentNodeID) == null) {
			System.out.println("Parent node was not found!");
		}
		
		if (root.addChild(newNodeID, parentNodeID)) {
			System.out.println("Node successfully added!");
			root.printTree();
		}
		
	}
	
	/**
	 * This method acts as a menu option. When selected it will
	 * find if a node exists with a character the user enters.
	 * @param root The root of the binary tree to find the node in.
	 */
	public static void findNode(INode root) {
		System.out.println("\nPlease input the node you want to add:");
		String searchCharacter = promptForCharacter();
		INode node = root.find(searchCharacter);
		if (node == null) {
			System.out.printf("Node %s does not exist.\n", searchCharacter);
		} else {
			System.out.printf("Node %s found!\n", searchCharacter);
		}
	}

	/**
	 * This method acts as a menu option. When selected it will
	 * retrieve the size of a binary tree with a root the user
	 * enters.
	 * @param root The root of the binary tree to get the size of.
	 */
	public static void getTreeSize(INode root) {
		System.out.println("\nPlease input the root node:");
		String rootCharacter = promptForCharacter();
		INode rootNode = root.find(rootCharacter);
		if (rootNode == null) {
			System.out.printf("Node %s does not exist.\n", rootCharacter);
			return;
		}
		System.out.printf("There are %s nodes in that tree.\n", rootNode.size());
		rootNode.printTree();
	}
	
	/**
	 * Propmts the user to enter a character. If the input
	 * is invalid, the user is forced to enter a valid character.
	 * Otherwise, the character is returned.
	 * @return The character the user entered.
	 */
	public static String promptForCharacter() {
		Scanner sc = new Scanner(System.in);
		
		String character = "";
		
		if (sc.hasNext()) {
			String input = sc.next();
			if (input.length() > 0 && input.length() < 2) {
				character = input;
			} else {
				System.out.println("Invalid input. Please enter a valid character:");
				promptForCharacter();
			}
		}
		
		return character.toUpperCase();
	}
	
	/**
	 * Prompts the user to select a menu option. If it fails, it tells
	 * the user that their input was invalid, and returns -1. Otherwise,
	 * it returns the value the user entered.
	 * @return The menu option the user selected.
	 */
	public static int promptForMenuOption() {
		Scanner sc = new Scanner(System.in);
		
		if (sc.hasNextInt()) {
			int input = sc.nextInt();
			if (input >= 0 && input <=3) {
				return input;
			} else {
				System.out.println("Invalid input. Please enter a valid menu option.");
				return -1;
			}
		}
		
		return -1;
	}

}
