package org.iso8583.packager;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.junit.Test;

public class EbcdicDataPackagerTest {

    public static final Charset EBCDIC = Charset.forName("IBM1047");
    @Test
    public void unpack() throws Exception {
        EbcdicDataPackager pkgr = new EbcdicDataPackager(3);
        pkgr.unpack(0, new byte[] { (byte) 0xF0, (byte) 0xf1, (byte) 0xf2, 0x00 });
        System.out.println(pkgr.getRawData());
        System.out.println(pkgr.get());

        pkgr = new EbcdicDataPackager(5);
        ByteBuffer b = EBCDIC.encode("Murtuza");
        pkgr.unpack(0, b.array());
        System.out.println(pkgr.getRawData());
        System.out.println(pkgr.get());
        System.out.println(pkgr.describe());

    }

}
