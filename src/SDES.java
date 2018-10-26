import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
/***
 * 
 * @author Jamie Walder,,
 *
 */
public class SDES {
	private final static int[] EP= {3,0,1,2,1,2,3,0};
	private final static int[] P4= {1,3,2,0};
	//boolean arrays when turn into bytes are equivalent to one of these sixteen arrays ie [0,0,0,0] is 0000 in bytes and 0 in base ten and thus position 0 in our table
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
	//boolean arrays when turn into bytes are equivalent to one of these sixteen arrays ie [0,0,0,0] is 0000 in bytes and 0 in base ten and thus position 0 in our table
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
	 * Convert the given bit array to a single byte
	 * @param inp A bit array, max length is 8 bits
	 * @return
	 */
	public byte bitArrayToByte(boolean[] inp) {
		throw new NotImplementedException();
	}
	/***
	 * Convert the given byte array to a String
	 * @param inp An array of bytes, hopefully storing the codes of printable characters.
	 * @return The characters as a String.
	 */
	public String byteArrayToString(byte[] inp) {
		throw new NotImplementedException();
	}
	/***
	 * Convert the given byte to a bit array, of the given size.
	 * @param b a byte to be converted
	 * @param size The size of the resulting bit array. The operator >>> can be used for an unsigned right shift.
	 * @return
	 */
	public boolean[] byteToBitArray(byte b,int size) {
		throw new NotImplementedException();
	}
	/***
	 *  Concatenate the two bit arrays, x || y
	 * @param x
	 * @param y
	 * @return the concatenation of x and y
	 */
	public boolean[] concat(boolean[] x, boolean[] y) {
		throw new NotImplementedException();
	}
	/***
	 * Decrypt the given byte array.
	 * @param cipher An array of bytes representing the cipher text.
	 * @return An array of bytes representing the original plain text.
	 */
	public byte[] decrypt(byte[] cipher) {
		throw new NotImplementedException();
	}
	/***
	 * Decrypt a single byte using SDES
	 * @param b the byte to be encyrpted
	 * @return The decrypted byte
	 */
	public byte decryptByte(byte b) {
		throw new NotImplementedException();
	}
	/***
	 *  Encrypt the given string using SDES Each character produces a byte of cipher.
	 * @param msg The message to be encrypted
	 * @return An array of bytes representing the cipher text.
	 */
	public byte[] encrypt(String msg) {
		throw new NotImplementedException();
	}
	/***
	 * Encrypt a single byte using SDES
	 * @param b The byte to be encrypted
	 * @return The encrypted byte
	 */
	public byte encryptByte(byte b) {
		throw new NotImplementedException();
	}
	/***
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
		throw new NotImplementedException();
	}
	/***
	 * This is the 'round' function. It is its own inverse. f(x,k) = (L(x) xor F(R(x), k)) || R(x)
	 *@author Jamie Walder
	 * @param x input message
	 * @param k key 
	 * @return result of f operation
	 */
	public boolean[] f(boolean[] x, boolean[] k) {
		return concat(xor(lh(x),feistel(rh(x),k)),rh(x));
	}
	/***
	 * @author Jamie Walder
	 * F(k,x) is a Feistel function F(k,x) = P4 (s0 (L (k xor EP(x))) || s1 (R (k xor EP(x)))
	 * @param k key to encrypt with 
	 * @param x message to encrypt
	 * @return the result of feistal
	 */
	public boolean[] feistel(boolean[] k,boolean[] x) {
		return expPerm(concat(s0(lh(xor(k,expPerm(x,EP)))),s1(rh(xor(k,expPerm(x,EP))))),P4);
	}
	/***
	 * Get a 10 bit key from the keyboard, such as 1010101010. Store it as an array of booleans in a field.
	 * @param scanner
	 */
	public void getKey10(Scanner scanner) {
		throw new NotImplementedException();
	}
	/***
	 * Left half of x, L(x)
	 * @param inp input
	 * @return a bit array which is the left half of the parameter, inp.
	 */
	public boolean[] lh(boolean[] inp) {
		throw new NotImplementedException();
	}
	/***
	 * Right half of x, R(x)
	 * @param inp input
	 * @return a bit array which is the right half of the parameter, inp.
	 */
	public boolean[] rh(boolean[] inp) {
		throw new NotImplementedException();	
	}
	/***
	 * Send the bitArray to stdout as 1's and 0's
	 * @param inp input
	 */
	public void show(boolean[] inp) {
		throw new NotImplementedException();
	}
	/***
	 * Send the byteArray to stdout
	 * @param byteArray input
	 */
	public void show(byte[] byteArray) {
		throw new NotImplementedException();
	}
	/***
	 * Exclusive OR. x and y must have the same length. x xor y is the same as x != y
	 * @param x 
	 * @param y
	 * @return the resulting xor of x and y
	 */
	public boolean[] xor(boolean[] x,boolean[] y) {
		throw new NotImplementedException();
	}
	/***
	 * @author Jamie Walder 
	 * s0 function 
	 * @param input 4 bit input to the s function
	 * @return two bit output of sblock
	 */
	public boolean[] s0(boolean[] input) {
		return this.s0[bitArrayToByte(input)];
	}
	/***
	 * @author Jamie Walder 
	 * s1 function 
	 * @param input 4 bit input to the s function
	 * @return two bit output of sblock
	 */
	public boolean[] s1(boolean[] input) {
		return this.s1[bitArrayToByte(input)];
		
	}
			
	
}
