package edu.miracosta.cs113;
import java.io.*;
import java.util.*;

import org.w3c.dom.Node;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> implements Serializable {

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.
	
	protected String[] tab;
	protected Scanner iFile ;
	protected static final char EMPTY = '-';
	

	// Default constructor 
	public MorseCodeTree() {
		root = new Node(EMPTY);
	}
	
	// constructor that uses a file 
	public MorseCodeTree(String file) {
		root = new Node(EMPTY) ;
		readTextFile(file);
	}
	
	// copy constuctor
	public MorseCodeTree(MorseCodeTree t) {
		this.root = t.root ;
		this.tab = t.tab ;
	}
	
	// sets the array of strings 
	public void setTab(String[] tab) {
		this.tab = tab;
	}	
	
	// gets the array of strings
	public String[] getTab() {
		return this.tab;
	}
	
	// adding a letter to the Tree
	public void addLetter(char letter, String code) {
		Node current = root;
		int loop = code.length() ;
		for(int i = 0; i < loop; i++) {
			if(code.charAt(0) == '*') {
				if(current.left != null) {
					current = current.left ;
				}
				else {
					current.setLeft(new Node(null));
					current = current.left ;
				}
			}
			else {
				if (current.right != null) {
					current = current.right ;
				} else {
					current.setRight(new Node(null));
					current = current.right;
				}
			}
			code = code.substring(1);
		}
		current.setData(letter);
	}
	
	// this method will read from a file 
	protected void readTextFile(String file) {
		String newLine = "";
		char letter = ' ';
		String code = " ";
		
		// try to open the file 
		try {
			iFile = new Scanner(new FileInputStream(file));
		}
		catch (FileNotFoundException e) {
			System.out.println("The file was not found: " + file);
			System.exit(0);
		}
	
		while (iFile.hasNext()) {
			newLine = iFile.nextLine();
			letter = newLine.charAt(0);
			code = newLine.substring(2).trim();
			addLetter(letter,code);
		}
		iFile.close(); // making sure to close the file for no data loss. 
			
	}
	
    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) {
    	String[] array = morseCode.split(" ") ;
        StringBuilder answer = new StringBuilder();

        for (String s : array) {
            Node current = root;
            int loop = s.length();

            // check for length
            if (loop > 4) {
                throw new StringIndexOutOfBoundsException() ;
            }

            for (int i = 0; i < loop; i++) {

                if (s.charAt(0) == '*') { // left
                    if (current.left != null) {
                        current = current.left;
                    } else {
                        System.exit(0);
                    }
                } else if (s.charAt(0) == '-') { // right
                    if (current.right != null) {
                        current = current.right;
                    } else {
                        System.exit(0);
                    }
                } else {  // incorrect character
                    if (s.charAt(0) != '*' || s.charAt(0) != '-') {
                        System.out.println("This is not a character. Exiting!") ;
                        throw new NumberFormatException() ;
                    }
                }
                // the last char in the code
                if (s.length() == 1) {
                    answer = answer.append(current.getData());
                }
                s = s.substring(1); // removing first char
            }
        }
        return  answer.toString() ;
    }
    
}

 // End of class MorseCodeTree