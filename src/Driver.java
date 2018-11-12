import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;
/**
 * Test the implementation of SDES
 * 
 * @author (sdb) 
 * @version (Oct 2018)
 */
public class Driver
{
   public static void main(String [] args)
{   SDES sdes = new SDES();
	
    Scanner scanner = new Scanner (System.in);
    
//    **tests expPerm**
    boolean[] inp = {false, false, true, true};
    int[] evp = {0, 2, 3, 1};
    System.out.println(Arrays.toString(sdes.expPerm(inp, evp)));
    
//    **test getKey10**
    sdes.getKey10(scanner);
    System.out.println(Arrays.toString(sdes.key));
    
//    **test byteArrayToString**
    String example = "This is an example";
    byte[] bytes = example.getBytes();
    System.out.println(bytes);
    System.out.println(sdes.byteArrayToString(bytes));

//    **test lh**
    boolean[] inp1 = {true, true, false, false};
    System.out.println(Arrays.toString(sdes.lh(inp1)));
   
//    **test rh**
    boolean[] inp11 = {true, true, false, false};
    System.out.println(Arrays.toString(sdes.rh(inp11)));
    
//    **test xor**
    boolean[] x = {true, true, false, false};
    boolean[] y = {true, false, false, true};
    System.out.println(Arrays.toString(sdes.xor(x, y)));
    
    //**test concat**
    boolean[] x1 = {true, true, false, false};
    boolean[] y1 = {true, false, false, true};
    System.out.println(Arrays.toString(sdes.concat(x1, y1)));
    
    //**test bitArrayToByte**
    boolean[] x11 = {true, true, false, false, true, false, false, true};
    System.out.println(sdes.bitArrayToByte(x11));
    
    //**test byteToBitArray**
    boolean[] x111 = {true, true, false, false, true, false, false, true};
    byte b = sdes.bitArrayToByte(x111);
    System.out.println(Arrays.toString(sdes.byteToBitArray(b, 8)));
    
    String plain = "x";
    System.out.println ("Enter plain text, or hit 'Enter' to terminate");
        plain = scanner.nextLine();
    byte [] cipher;
    while (plain.length() > 0)
    {   
        cipher = sdes.encrypt  (plain);
        System.out.print ("Cipher is ");
        sdes.show (cipher);
        System.out.println (sdes.byteArrayToString (sdes.decrypt (cipher)));
        System.out.println ("Enter plain text, or hit 'Enter' to terminate");        plain = scanner.nextLine();
    }
    byte[] cipherT= {
			-115, -17, -47, -113, -43, -47, 15, 84,
			-43, -113, -17, 84, -43, 79, 58, 15, 64, -113, -43, 65, -47, 127,
			84, 64, -43, -61, 79, -43, 93, -61, -14, 15, -43, -113, 84, -47, 127,
			-43, 127, 84, 127, 10, 84, 15, 64, 43
	};
    System.out.println("Input: "+Arrays.toString(cipherT));
	//0111 1111 01 
	boolean[] key= {false,true,true ,true, true,true,true,true, false,true};
	sdes.getKey10(new Scanner(System.in));
	byte[] plainT=sdes.decrypt(cipherT);
	System.out.println(sdes.byteArrayToString(plainT));
}
}