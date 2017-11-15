package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AsciiBitmapPackagerTest {


    @Test
    public void test() throws Exception {
        AsciiBitmapPackager pkgr = new AsciiBitmapPackager(16);

        System.out.println(Character.digit(0x43, 16));

        byte[] asciiBitmap = new byte[] { 0x34, 0x32, 0x33, 0x34, 0x30, 0x31, 0x30, 0x31, 0x30, 0x38, 0x30, 0x30, 0x30,
                0x30, 0x30, 0x43 };
        


        pkgr.unpack(0, asciiBitmap);
        
        //@formatter:off
        
        String expected = String.format("%n") + 
            "[Bitmap Hex] : [423401010800000C]\r\n" + 
            "[Bitmap Bit] : [2 7 11 12 14 24 32 37 61 62 ]\r\n";        
        // @formatter:on
        assertEquals(expected, pkgr.describe());

    }

}
