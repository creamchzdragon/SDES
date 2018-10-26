import java.util.Scanner;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
/***
 * 
 * @author Jamie Walder,,
 *
 */
public class SDES {
	public SDES() {}
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
	public boolean concat(boolean[] x, boolean[] y) {
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

	 * @param x input message
	 * @param k key 
	 * @return result of f operation
	 */
	public boolean[] f(boolean[] x, boolean[] k) {
		throw new NotImplementedException();
	}
	/***
	 * F(k,x) is a Feistel function F(k,x) = P4 (s0 (L (k xor EP(x))) || s1 (R (k xor EP(x)))
	 * @param k key to encrypt with 
	 * @param x message to encrypt
	 * @return the result of feistal
	 */
	public boolean[] feistel(boolean[] k,boolean[] x) {
		throw new NotImplementedException();
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
	
			
	
}
