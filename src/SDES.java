package sdes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/***
 * 
 * @author Jamie Walder, Matthew Mallon, David Smits
 *
 */
public class SDES {
	private final static int[] EP= {3,0,1,2,1,2,3,0};
	private final static int[] P4= {1,3,2,0};
	private final static int[] InitPerm= {1,5,2,0,3,7,4,6};
	private final static int[] InvInitPerm= {3,0,2,4,6,1,7,5};
	private final static int[] K1Perm= {0,1,0,1,1,1,1,1};
	private final static int[] K2Perm= {1,1,1,1,1,1,0,0};
	public boolean[] key = new boolean[10];
	//boolean arrays when turned into bytes are equivalent to one of these sixteen arrays ie [0,0,0,0] is 0000 in 
	//bytes and 0 in base ten and thus position 0 in our table
	//s0
	//0000:01,0001:11,0010:00,0011:10,0100:11,0101:01,0110:10,0111:00
	//1000:00,1001:11,1010:10,1011:01,1100:01,1101:11,1110:11,1111:10
	private final static boolean[][] s0= {
			{false,true},//0000
			{true,true},//0001
			{false,false},//0010
			{true,false},//0011
			{true,true},//0100
			{false,true},//0101
			{true,false},//0110
			{false,false},//0111
			{false,false},//1000
			{true,true},//1001
			{true,false},//1010
			{false,true},//1011
			{false,true},//1100
			{true,true},//1101
			{true,true},//1110
			{true,false}//1111
	};
	//boolean arrays whened turn into bytes are equivalent to one of these sixteen arrays ie [0,0,0,0] is 0000 in 
	//bytes and 0 in base ten and thus position 0 in our table
		//s1
		//0000:00,0001:10,0010:01,0011:00,0100:10,0101:01,0110:11,0111:11
		//1000:11,1001:10,1010:00,1011:01,1100:01,1101:00,1110:00,1111:11
		private final static boolean[][] s1= {
				{false,false},//0000
				{true,false},//0001
				{false,true},//0010
				{false,false},//0011
				{true,false},//0100
				{false,true},//0101
				{true,true},//0110
				{true,true},//0111
				{true,true},//1000
				{true,false},//1001
				{false,false},//1010
				{false,true},//1011
				{false,true},//1100
				{false,false},//1101
				{false,false},//1110
				{true,true}//1111
		};
	public SDES() {
	}
	/***
	 * @author Matthew Mallon
	 * Convert the given bit array to a single byte
	 * @param inp A bit array, max length is 8 bits
	 * @return
	 */
	public byte bitArrayToByte(boolean[] inp) {
		byte temp = 0;
	        for (int bit = 0; bit < 8; bit++) {
	            if (inp[bit]) {
	                temp |= (128 >> bit);
	            }
	        }
	    return temp;
	}
	/***
	 * @author Matthew Mallon
	 * Convert the given byte array to a String
	 * @param inp An array of bytes, hopefully storing the codes of printable characters.
	 * @return The characters as a String.
	 */
	public String byteArrayToString(byte[] inp) {
		return new String(inp);
	}
	/***
	 * @author Matthew Mallon
	 * Convert the given byte to a bit array, of the given size.
	 * @param b a byte to be converted
	 * @param size The size of the resulting bit array. The operator >>> can be used for an unsigned right shift.
	 * @return
	 */
	public boolean[] byteToBitArray(byte b,int size) {
		 boolean[] boolArr = new boolean[8];
		    boolArr[7] = ((b & 0x01) != 0);
		    boolArr[6] = ((b & 0x02) != 0);
		    boolArr[5] = ((b & 0x04) != 0);
		    boolArr[4] = ((b & 0x08) != 0);
		    boolArr[3] = ((b & 0x10) != 0);
		    boolArr[2] = ((b & 0x20) != 0);
		    boolArr[1] = ((b & 0x40) != 0);
		    boolArr[0] = ((b & 0x80) != 0);
		    return boolArr;
	}
	/***
	 *  Concatenate the two bit arrays, x || y
	 * @param x
	 * @param y
	 * @return the concatenation of x and y
	 */
	public boolean[] concat(boolean[] x, boolean[] y) {
		int size = x.length + y.length;
		boolean[] concat = new boolean[size];
		for(int i = 0; i < x.length; i++) {
			concat[i] = x[i];
		}
		for(int i = 0; i < y.length; i++) {
			concat[i + x.length] = y[i];
		}
		return concat;
	}
	
	/***
	 * @author David Smits
	 * Encrypt the given string using SDES Each character produces a byte of cipher.
	 * @param msg The message to be encrypted
	 * @return An array of bytes representing the cipher text.
	 */
	public byte[] encrypt(String plain) {
		byte[] plainBytes = plain.getBytes();
		byte[] result = new byte[plainBytes.length];
		
		
		for(int i = 0; i < plainBytes.length; i++)
			result[i] = encryptByte(plainBytes[i]);
		
		return result;
	}
	
	/***
	 * @author David Smits
	 * Encrypt a single byte using SDES
	 * @param b The byte to be encrypted
	 * @return The encrypted byte
	 */
	public byte encryptByte(byte b) {
		boolean[] bitArray = byteToBitArray(b, 10);
		boolean[] k1 = expPerm(key, K1Perm);
		boolean[] k2 = expPerm(key, K2Perm);
		
		boolean[] IP = expPerm(bitArray, InitPerm);
		boolean[] fk1 = concat(xor(lh(IP), feistel(k1, rh(IP))), rh(IP));
		boolean[] swapfk1 = concat(rh(fk1), lh(fk1));
		boolean[] fk2 = concat(xor(lh(swapfk1), feistel(k2, rh(swapfk1))), rh(swapfk1));
		boolean[] cipher = expPerm(fk2, InvInitPerm);
		
		return bitArrayToByte(cipher);
	}
	
	/***
	 * @author David Smits
	 * Decrypt the given byte array.
	 * @param cipher An array of bytes representing the cipher text.
	 * @return An array of bytes representing the original plain text.
	 */
	public byte[] decrypt(byte[] cipher) {
		byte[] result = new byte[cipher.length];
		
		for(int i = 0; i < cipher.length; i++)
			result[i] = decryptByte(cipher[i]);
		return result;
	}
	
	/***
	 * @author David Smits
	 * Decrypt a single byte using SDES
	 * @param b the byte to be encyrpted
	 * @return The decrypted byte
	 */
	public byte decryptByte(byte b) {
		boolean[] arrayByte = byteToBitArray(b, 0);
		boolean[] k1 = expPerm(key, K1Perm);
		boolean[] k2 = expPerm(key, K2Perm);
		
		boolean[] IP = expPerm(arrayByte, InitPerm);
		boolean[] inversefk2 = concat(xor(lh(IP), feistel(k2, rh(IP))), rh(IP));
		boolean[] swapfk2 = concat(rh(inversefk2), lh(inversefk2));
		boolean[] fk1 = concat(xor(lh(swapfk2), feistel(k1, rh(swapfk2))), rh(swapfk2));
		boolean[] plain = expPerm(fk1, InvInitPerm);
		
		return bitArrayToByte(plain);
	}
	
	/***
	 * @author David Smits
	 * Send the bitArray to stdout as 1's and 0's
	 * @param inp input
	 */
	public void show(boolean[] inp) {
		System.out.println(bitArrayToByte(inp));
	}
	
	/***
	 * @author David Smits
	 * Send the byteArray to stdout
	 * @param byteArray input
	 */
	public void show(byte[] byteArray) {
		System.out.println(byteArrayToString(byteArray));
	}
	
	/***
	 * @author Matthew Mallon
	 * Expand and/or permute and/or select from the bit array, 
	 * inp, producing an expanded/permuted/selected 
	 * bit array. Use the expansion/permutation vector epv.
	 * @param inp A bit array represented as booleans, true=1, false=0.
	 * @param evp An expansion and/or permutation and/or selection vector; 
	 * all numbers in epv must be in the range 0..inp.length-1, i.e. they must 
	 * be valid subscripts for inp.
	 * @return The permuted/expanded/selected bit array, or null if there is an error.
	 * @throws java.lang.IndexOutOfBoundsException
	 */
	public boolean[] expPerm(boolean[] inp, int[] evp) throws IndexOutOfBoundsException {
		boolean[] expPerm = new boolean[evp.length];
		for(int i = 0; i < evp.length; i++) {
			if(evp[i] < 0 || evp[i] >= inp.length) {
				throw new IndexOutOfBoundsException("All numbers in expansion, permutation, "
						+ "or selection vector must be in range 0..." + Integer.toString(inp.length-1));
			}
			else {
				expPerm[i] = inp[evp[i]];
			}
		}
		return expPerm;
	}
	/***
	 * This is the 'round' function. It is its own inverse. f(x,k) = (L(x) xor F(R(x), k)) || R(x)
	 *@author Jamie Walder
	 * @param x input message
	 * @param k key 
	 * @return result of f operation
	 */
	public boolean[] f(boolean[] x, boolean[] k) {
		return concat(xor(lh(x),feistel(k,rh(x))),rh(x));
	}
	/***
	 * @author Jamie Walder
	 * F(k,x) is a Feistel function F(k,x) = P4 (s0 (L (k xor EP(x))) || s1 (R (k xor EP(x)))
	 * @param k key to encrypt with 
	 * @param x message to encrypt
	 * @return the result of feistal
	 */
	public boolean[] feistel(boolean[] k,boolean[] x) {
		boolean[] xor=xor(k,expPerm(x,EP));
		return expPerm(concat(s0(lh(xor)),s1(rh(xor))),P4);
	}
	/***
	 * @author Matthew Mallon
	 * Get a 10 bit key from the keyboard, such as 1010101010. Store it as an array of booleans in a field.
	 * @param scanner
	 */
	public void getKey10(Scanner scanner) {
		String stringKey = "";
		System.out.println ("Enter 10 bit key");
		stringKey = scanner.nextLine();
		if(stringKey.length() != 10) {
			throw new IllegalArgumentException("Key must be 10 bits");
		}
		for(int i = 0; i < stringKey.length(); i++) {
			if(stringKey.charAt(i) == '0') {
				key[i] = false;
			}
			else if(stringKey.charAt(i) == '1') {
				key[i] = true;
			}
			else {
				throw new IllegalArgumentException("Bits in key must be 0 or 1");
			}
		}	
	}
	/***
	 * @author Matthew Mallon
	 * Left half of x, L(x)
	 * @param inp input
	 * @return a bit array which is the left half of the parameter, inp.
	 */
	public boolean[] lh(boolean[] inp) {
		int size = inp.length/2;
		boolean[] lh = new boolean[size];
		for(int i = 0; i < size; i++) {
			lh[i] = inp[i];
		}
		return lh;
	}
	/***
	 * @author Matthew Mallon
	 * Right half of x, R(x)
	 * @param inp input
	 * @return a bit array which is the right half of the parameter, inp.
	 */
	public boolean[] rh(boolean[] inp) {
		int size = inp.length/2;
		boolean[] rh = new boolean[size];	
		for(int i = 0; i < size; i++) {
			rh[i] = inp[i + size];
		}
		return rh;
	}
	/***
	 * Exclusive OR. x and y must have the same length. x xor y is the same as x != y
	 * @param x 
	 * @param y
	 * @return the resulting xor of x and y
	 */
	public boolean[] xor(boolean[] x,boolean[] y) {
		if(x.length != y.length) {
			throw new IllegalArgumentException("x and y must be same length");
		}
		int size = x.length;
		boolean[] xor = new boolean[size];
		for(int i = 0; i < size; i++) {
			if(x[i] != y[i]) {
				xor[i] = true;
			}
			else {
				xor[i] = false;
			}
		}
		return xor;
	}
	/***
	 * @author Jamie Walder 
	 * s0 function 
	 * @param input 4 bit input to the s function
	 * @return two bit output of sblock
	 */
	public boolean[] s0(boolean[] input) {
		boolean[] buffer={false,false,false,false};
		input=concat(buffer,input);
		return this.s0[bitArrayToByte(input)];
	}
	/***
	 * @author Jamie Walder 
	 * s1 function 
	 * @param input 4 bit input to the s function
	 * @return two bit output of sblock
	 */
	public boolean[] s1(boolean[] input) {
		boolean[] buffer={false,false,false,false};
		input=concat(buffer,input);
		return this.s1[bitArrayToByte(input)];
		
	}
			
	
}