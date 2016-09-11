/*
 * Dennis Bounded
 * CS4840 Computer Security and Cryptography
 * hwk1: Implementation of Caesar's Cryptography System
 * due Feb. 26, 2016
 */
import java.io.*;
import java.util.*;


public class MyCaesar {
	
	// Encryption Function
	// @param file name of the clear text that has correct 
	// format. Characters must be in printable ASCII characters, no symbols, &
	// in 4 characters per block 
	// @return: cipher text as an output file and to standard output
	
	public static void encrypt(String clearText) {
		
		File inputFile, outputFile;
		FileWriter fWriter;
		PrintWriter pWriter;
		Scanner scan;
		int key, clearChar, cipherChar;
		
		try {
			
			inputFile = new File(clearText);
			scan = new Scanner(inputFile);
			
			//Get Key
			key = scan.nextInt(); scan.nextLine();
			//25 Keys
			if (!(key < 26 && key >= 0)) {
				System.out.println("Invalid Key Range. 0-25");
				System.exit(0);
			}
			System.out.println("KEY: " + key + "\n");
			
			//Encrypt
			try {
				
				outputFile = new File("cipout.txt");
				fWriter = new FileWriter(outputFile);
				pWriter = new PrintWriter(fWriter);
				
				
				while(scan.hasNext())
				{
					
					//Check to see if whitespace 
					clearChar = scan.findInLine(".").charAt(0);
					if(clearChar == 32) {
						System.out.print(" ");
						pWriter.append(' ');
					}
					
					// Exit out if clear text does contain a character
					// that is NOT a capital letter
					else if (!(clearChar >= 65 && clearChar <= 90) ) {
						System.out.println("\nOnly Capital Letters in Clear Text Please");
						System.exit(0);
					}
					
					else {	
						cipherChar = (clearChar + key) % 256; //256 number of ASCII characters
						System.out.printf("%c", cipherChar);
						pWriter.append((char) cipherChar);
					}
					
				}
				
				System.out.println();
				
				fWriter.close();
				pWriter.close();
				
			} catch (IOException e) {
				
				System.out.println("Error: Could not write to cipout.txt\n" + e);
				
			}
			
			scan.close();

		} catch (FileNotFoundException e) {
			
			System.out.println("Error: Invalid file or incorrect file name\n" + e);
			
		}
		
}
	
	// Decryption Function
	// @param file name of the cipher text in correct format
	// A key and 4 cipher characters per block in ciphertext
	// @return clear text as an output file and to standard output
	public static void decrypt(String cipherText){
		
		File inputFile, outputFile;
		FileWriter fWriter;
		PrintWriter pWriter;
		Scanner scan;
		int key, clearChar, cipherChar;
		
		try {
			
			inputFile = new File(cipherText);
			scan = new Scanner(inputFile);
			
			//Get Key
			key = scan.nextInt(); scan.nextLine();
			if (!(key < 256 && key > 0)) {
				System.out.println("Invalid Key Range");
				System.exit(0);
			}
			System.out.println("KEY: " + key + "\n");
			
			//Decrypt
			try {
				
				outputFile = new File("msgout.txt");
				fWriter = new FileWriter(outputFile);
				pWriter = new PrintWriter(fWriter);
				
				
				while(scan.hasNext())
				{
					
					//Check to see if whitespace 
					cipherChar = scan.findInLine(".").charAt(0);
					if(cipherChar == 32) {
						System.out.print(" ");
						pWriter.append(' ');
					}
					
					else {
						
						clearChar = Math.abs(cipherChar - key) % 256; // Number of Ascii characters
						System.out.printf("%c", clearChar);
						pWriter.append((char) clearChar);	
						
					}
				
				}
				
				System.out.println();
				
				fWriter.close();
				pWriter.close();
				
			} catch (IOException e) {
				
				System.out.println("Error: Could not write to cipout.txt\n" + e);
				
			}
			
			scan.close();

		} catch (FileNotFoundException e) {
			
			System.out.println("Error: Invalid file or incorrect file name\n" + e);
			
		}	
	}

	public static void main(String[] args){
	
		if (args[0].equals("enc"))
			encrypt(args[1]);
		else if (args[0].equals("dec"))
			decrypt(args[1]);
		else
			System.out.println("Arguments: { enc | dec } filename.txt");
				
		
	}
}
