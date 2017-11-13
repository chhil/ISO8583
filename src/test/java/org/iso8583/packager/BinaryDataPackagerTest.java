package org.iso8583.packager;

import java.nio.charset.Charset;

import org.junit.Test;

public class BinaryDataPackagerTest {

    public static final Charset EBCDIC = Charset.forName("IBM1047");
    @Test
    public void unpack() throws Exception {
        BinaryDataPackager pkgr = new BinaryDataPackager(3);
        pkgr.unpack(0, new byte[] { (byte) 0xF0, (byte) 0xf1, (byte) 0xf2, 0x00 });
        System.out.println(pkgr.getRawData());
        System.out.println(pkgr.get());

        pkgr = new BinaryDataPackager(9);

        pkgr.unpack(0, "01234ABCD".getBytes());
        System.out.println(pkgr.getRawData());
        System.out.println(pkgr.get());
        System.out.println(pkgr.describe());

    }

}
