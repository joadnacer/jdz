package com.joad.jdz.base64.scalar;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Test;
    
public class RFC4648ScalarDecodeTest {
    /*
     * RFC4648 test vectors
     */
    private static final byte[] emptyVector  = "".getBytes();
    private static final byte[] fVector      = "Zg==".getBytes();
    private static final byte[] foVector     = "Zm8=".getBytes();
    private static final byte[] fooVector    = "Zm9v".getBytes();
    private static final byte[] foobVector   = "Zm9vYg==".getBytes();
    private static final byte[] foobaVector  = "Zm9vYmE=".getBytes();
    private static final byte[] foobarVector = "Zm9vYmFy".getBytes();

    /*
     * Random data fuzz
     */
    private static final byte[] randomDataZeroMod  = new byte[1000];
    private static final byte[] randomDataOneMod   = new byte[1001];
    private static final byte[] randomDataTwoMod   = new byte[1002];
    private static final byte[] randomDataThreeMod = new byte[1003];

    static {
        new Random().nextBytes(randomDataZeroMod);
        new Random().nextBytes(randomDataOneMod);
        new Random().nextBytes(randomDataTwoMod);
        new Random().nextBytes(randomDataThreeMod);
    }

    private static final byte[] base64RandomDataZeroMod  = java.util.Base64.getEncoder().encode(randomDataZeroMod);
    private static final byte[] base64RandomDataOneMod   = java.util.Base64.getEncoder().encode(randomDataOneMod);
    private static final byte[] base64RandomDataTwoMod   = java.util.Base64.getEncoder().encode(randomDataTwoMod);
    private static final byte[] base64RandomDataThreeMod = java.util.Base64.getEncoder().encode(randomDataThreeMod);
    private static final byte[] base64InvalidData        = java.util.Base64.getEncoder().encode(randomDataZeroMod);

    @Test
    public void RFC4648DecodeEmptyVector() {
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(emptyVector));

        assertEquals("", jdzRes);
    }
    
    @Test
    public void RFC4648DecodefVector() {
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(fVector));

        assertEquals("f", jdzRes);
    }

    @Test
    public void RFC4648DecodefoVector() {
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(foVector));

        assertEquals("fo", jdzRes);
    }

    @Test
    public void RFC4648DecodefooVector() {
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(fooVector));

        assertEquals("foo", jdzRes);
    }

    @Test
    public void RFC4648DecodefoobVector() {
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(foobVector));

        assertEquals("foob", jdzRes);
    }

    @Test
    public void RFC4648DecodefoobaVector() {
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(foobaVector));

        assertEquals("fooba", jdzRes);
    }

    @Test
    public void RFC4648DecodefoobarVector() {
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(foobarVector));

        assertEquals("foobar", jdzRes);
    }

    @Test
    public void RFC4648DecodeZeroMod() {
        String javaRes = new String(java.util.Base64.getDecoder().decode(base64RandomDataZeroMod));
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(base64RandomDataZeroMod));

        assertEquals(javaRes, jdzRes);
    }

    @Test
    public void RFC4648DecodeOneMod() {
        String javaRes = new String(java.util.Base64.getDecoder().decode(base64RandomDataOneMod));
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(base64RandomDataOneMod));

        assertEquals(javaRes, jdzRes);
    }

    @Test
    public void RFC4648DecodeTwoMod() {
        String javaRes = new String(java.util.Base64.getDecoder().decode(base64RandomDataTwoMod));
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(base64RandomDataTwoMod));

        assertEquals(javaRes, jdzRes);
    }

    @Test
    public void RFC4648DecodeThreeMod() {
        String javaRes = new String(java.util.Base64.getDecoder().decode(base64RandomDataThreeMod));
        String jdzRes = new String(com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(base64RandomDataThreeMod));

        assertEquals(javaRes, jdzRes);
    }

    @Test
    public void RFC4648InvalidInputDecode() {
        base64InvalidData[100] = (byte) '$';
        base64InvalidData[101] = (byte) '$';
        base64InvalidData[102] = (byte) '$';
        base64InvalidData[103] = (byte) '$';

        String exception = "No exception";

        try {
            com.joad.jdz.base64.scalar.Base64Scalar.getDecoder().decode(base64InvalidData);
        }
        catch (Exception e) {
            exception = e.getMessage();
        }

        assertEquals("Illegal base64 character 24 at position 100", exception);
    }
}
