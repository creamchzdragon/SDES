import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SDESTest {

	@Test
	void testSDES() {
		fail("Not yet implemented");
	}

	@Test
	void testBitArrayToByte() {
		fail("Not yet implemented");
	}

	@Test
	void testByteArrayToString() {
		fail("Not yet implemented");
	}

	@Test
	void testByteToBitArray() {
		fail("Not yet implemented");
	}

	@Test
	void testConcat() {
		fail("Not yet implemented");
	}

	@Test
	void testDecrypt() {
		fail("Not yet implemented");
	}

	@Test
	void testDecryptByte() {
		fail("Not yet implemented");
	}

	@Test
	void testEncrypt() {
		fail("Not yet implemented");
	}

	@Test
	void testEncryptByte() {
		fail("Not yet implemented");
	}

	@Test
	void testExpPerm() {
		fail("Not yet implemented");
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
	void testGetKey10() {
		fail("Not yet implemented");
	}

	@Test
	void testLh() {
		fail("Not yet implemented");
	}

	@Test
	void testRh() {
		fail("Not yet implemented");
	}

	@Test
	void testShowBooleanArray() {
		fail("Not yet implemented");
	}

	@Test
	void testShowByteArray() {
		fail("Not yet implemented");
	}

	@Test
	void testXor() {
		fail("Not yet implemented");
	}

}
