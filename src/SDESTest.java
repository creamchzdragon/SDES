import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SDESTest {
	SDES sdes=new SDES();
	@Test
	void testSDES() {
		byte[] cipher= {
				-115, -17, -47, -113, -43, -47, 15, 84,
				-43, -113, -17, 84, -43, 79, 58, 15, 64, -113, -43, 65, -47, 127,
				84, 64, -43, -61, 79, -43, 93, -61, -14, 15, -43, -113, 84, -47, 127,
				-43, 127, 84, 127, 10, 84, 15, 64, 43
		};
		//0111 1111 01 
		boolean[] key= {false,true,true ,true, true,true,true,true, false,true};
		SDES sdes=new SDES();
		sdes.key=key;
		byte[] plain=sdes.decrypt(cipher);
		assertTrue(sdes.byteArrayToString(plain).equals("What are the first names of your team members?"));
		
	}

	@Test
	void testBitArrayToByte() {
		//01010101
		//1+4+16+64=85
		boolean[] barr= {false,true,false,true,false,true,false,true};
		assertTrue(sdes.bitArrayToByte(barr)==85);
	}

	@Test
	void testByteArrayToString() {
		byte[] barr= {97,98,99};
		assertTrue(sdes.byteArrayToString(barr).equals("abc"));
	}

	@Test
	void testByteToBitArray() {
		boolean[] barr= {false,true,false,true,false,true,false,true};
		assertTrue(Arrays.equals(sdes.byteToBitArray((byte)85,8),barr));
	}

	@Test
	void testConcat() {
		boolean[] r= {true,true,true,true};
		boolean[] l= {false,false,false,false};
		boolean[] result= {true,true,true,true,false,false,false,false};
		assertTrue(Arrays.equals(sdes.concat(r, l),result));
	}

	

	@Test
	void testExpPerm() {
		int[] InitPerm= {1,5,2,0,3,7,4,6};
		int[] InvInitPerm= {3,0,2,4,6,1,7,5};
		boolean[] start= {true,false,true,false,true,false,true,false};
		SDES sdes=new SDES();
		
		System.out.println("Exp Perm Test");
		for(boolean b:sdes.expPerm(sdes.expPerm(start, InitPerm),InvInitPerm)){
			System.out.print(b+",");
		}
	}

	@Test
	void testF() {
		SDES sdes=new SDES();
		boolean[] x= {true,false,true,false,true,false,true,false};
		boolean[] k= {true,false,true,false,true,false,true,false};
		boolean[] idealResult= {true,true,false,true,true,false,true,false};
		boolean[] result=sdes.f(x, k);
		for(int i=0;i<idealResult.length;i++) {
			assertTrue(result[i]==idealResult[i]);
		}
		
	}

	@Test
	void testFeistel() {
		SDES sdes=new SDES();
		boolean[] x= {true,false,true,false};
		boolean[] k= {true,false,true,false,true,false,true,false};
		boolean[] idealResult= {false,true,true,true};
		boolean[] result=sdes.feistel(k, x);
		for(int i=0;i<idealResult.length;i++) {
			assertTrue(result[i]==idealResult[i]);
		}
		
	}



	@Test
	void testLh() {
		boolean[] test= {true,false};
		assertTrue(sdes.lh(test)[0]);
	}

	@Test
	void testRh() {
		boolean[] test= {false,true};
		assertTrue(sdes.rh(test)[0]);
	}

	

	

	@Test
	void testXor() {
		 boolean[] exp1= {true,true,false,true};
		 boolean[] exp2= {true,true,false,true};
		 boolean[] result= {false,false,false,false};
		 assertTrue(Arrays.equals(sdes.xor(exp1, exp2),result));
	}

}
