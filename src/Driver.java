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
//    boolean[] inp = {false, false, true, true};
//    int[] evp = {0, 2, 3, 1};
//    System.out.println(Arrays.toString(sdes.expPerm(inp, evp)));
    
//    **test getKey10**
//    sdes.getKey10(scanner);
//    System.out.println(Arrays.toString(sdes.key));
    
//    **test byteArrayToString**
//    String example = "This is an example";
//    byte[] bytes = example.getBytes();
//    System.out.println(bytes);
//    System.out.println(sdes.byteArrayToString(bytes));

//    **test lh**
//    boolean[] inp = {true, true, false, false};
//    System.out.println(Arrays.toString(sdes.lh(inp)));
   
//    **test rh**
//    boolean[] inp = {true, true, false, false};
//    System.out.println(Arrays.toString(sdes.rh(inp)));
    
//    **test xor**
//    boolean[] x = {true, true, false, false};
//    boolean[] y = {true, false, false, true};
//    System.out.println(Arrays.toString(sdes.xor(x, y)));
    
//    **test concat**
//    boolean[] x = {true, true, false, false};
//    boolean[] y = {true, false, false, true};
//    System.out.println(Arrays.toString(sdes.concat(x, y)));
    
//    **test bitArrayToByte**
//    boolean[] x = {true, true, false, false, true, false, false, true};
//    System.out.println(sdes.bitArrayToByte(x));
    
//    **test byteToBitArray**
//    boolean[] x = {true, true, false, false, true, false, false, true};
//    byte b = sdes.bitArrayToByte(x);
//    System.out.println(Arrays.toString(sdes.byteToBitArray(b, 8)));
    
//    String plain = "x";
//    System.out.println ("Enter plain text, or hit 'Enter' to terminate");
//        plain = scanner.nextLine();
//    byte [] cipher;
//    while (plain.length() > 0)
//    {   
//        cipher = sdes.encrypt  (plain);
//        System.out.print ("Cipher is ");
//        sdes.show (cipher);
//        System.out.println (sdes.byteArrayToString (sdes.decrypt (cipher)));
//        System.out.println ("Enter plain text, or hit 'Enter' to terminate");
//        plain = scanner.nextLine();
//    }
}
}