package com.joad.jdz.base64.vector;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Test;
    
public class RFC2045VectorDecodeTest {
    /*
     * RFC2045 test vectors
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
    private static final byte[] randomDataSixtyOne = new byte[61];

    static {
        new Random().nextBytes(randomDataZeroMod);
        new Random().nextBytes(randomDataOneMod);
        new Random().nextBytes(randomDataTwoMod);
        new Random().nextBytes(randomDataThreeMod);
        new Random().nextBytes(randomDataSixtyOne);
    }

    private static final byte[] base64RandomDataZeroMod  = java.util.Base64.getMimeEncoder().encode(randomDataZeroMod);
    private static final byte[] base64RandomDataOneMod   = java.util.Base64.getMimeEncoder().encode(randomDataOneMod);
    private static final byte[] base64RandomDataTwoMod   = java.util.Base64.getMimeEncoder().encode(randomDataTwoMod);
    private static final byte[] base64RandomDataThreeMod = java.util.Base64.getMimeEncoder().encode(randomDataThreeMod);
    private static final byte[] base64RandomDataSixtyOne = java.util.Base64.getMimeEncoder().encode(randomDataSixtyOne);
    private static final byte[] base64InvalidData        = java.util.Base64.getMimeEncoder().encode(randomDataZeroMod);

    @Test
    public void RFC2045DecodeEmptyVector() {
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(emptyVector));

        assertEquals("", jdzRes);
    }
    
    @Test
    public void RFC2045DecodefVector() {
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(fVector));

        assertEquals("f", jdzRes);
    }

    @Test
    public void RFC2045DecodefoVector() {
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(foVector));

        assertEquals("fo", jdzRes);
    }

    @Test
    public void RFC2045DecodefooVector() {
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(fooVector));

        assertEquals("foo", jdzRes);
    }

    @Test
    public void RFC2045DecodefoobVector() {
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(foobVector));

        assertEquals("foob", jdzRes);
    }

    @Test
    public void RFC2045DecodefoobaVector() {
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(foobaVector));

        assertEquals("fooba", jdzRes);
    }

    @Test
    public void RFC2045DecodefoobarVector() {
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(foobarVector));

        assertEquals("foobar", jdzRes);
    }

    @Test
    public void RFC2045DecodeZeroMod() {
        String javaRes = new String(java.util.Base64.getMimeDecoder().decode(base64RandomDataZeroMod));
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(base64RandomDataZeroMod));

        assertEquals(javaRes, jdzRes);
    }

    @Test
    public void RFC2045DecodeOneMod() {
        String javaRes = new String(java.util.Base64.getMimeDecoder().decode(base64RandomDataOneMod));
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(base64RandomDataOneMod));

        assertEquals(javaRes, jdzRes);
    }

    @Test
    public void RFC2045DecodeTwoMod() {
        String javaRes = new String(java.util.Base64.getMimeDecoder().decode(base64RandomDataTwoMod));
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(base64RandomDataTwoMod));

        assertEquals(javaRes, jdzRes);
    }

    @Test
    public void RFC2045DecodeThreeMod() {
        String javaRes = new String(java.util.Base64.getMimeDecoder().decode(base64RandomDataThreeMod));
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(base64RandomDataThreeMod));

        assertEquals(javaRes, jdzRes);
    }
    
    @Test
    public void RFC2045DecodeSixtyOne() {
        String javaRes = new String(java.util.Base64.getMimeDecoder().decode(base64RandomDataSixtyOne));
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(base64RandomDataSixtyOne));

        assertEquals(javaRes, jdzRes);
    }

    @Test
    public void RFC2045InvalidInputDecode() {
        base64InvalidData[100] = (byte) '$';
        base64InvalidData[101] = (byte) '$';
        base64InvalidData[102] = (byte) '$';
        base64InvalidData[103] = (byte) '$';

        String javaRes = new String(java.util.Base64.getMimeDecoder().decode(base64InvalidData));
        String jdzRes = new String(com.joad.jdz.base64.vector.Base64Vector.getMimeDecoder().decode(base64InvalidData));

        assertEquals(javaRes, jdzRes);
    }
}