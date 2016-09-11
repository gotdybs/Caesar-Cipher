import java.util.Hashtable;

/*
 * Dennis Bounded
 * CS4840 Computer Security and Cryptography
 * CaesarAlphabet.java : implements the value of each letter in Caesar's CS
 * uses a Hashtable to assign a value to each letter and vice versa.
 * due March 4, 2016
 */

public class CaesarAlphabet {
	
	// Using two hashtables since 1-1 relation
	private Hashtable<Character, Integer> alpha = new Hashtable<Character, Integer>();
	private Hashtable<Integer, Character> beta = new Hashtable<Integer, Character>();
	
	// Default Constructor
	CaesarAlphabet()
	{
		for(int i = 0 ; i < 26; i++) 
		{
			alpha.put((char) ('A'+ i), i);
			beta.put(i, (char) ('A'+ i));
		}
	}
	
	// Accessors
	public int getValue(char letter){
		return alpha.get(letter);
	}
	
	public char getLetter(int key){
		return beta.get(key);
	}
	
}
	


