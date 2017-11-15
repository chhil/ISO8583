package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EbcdicLengthPackagerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void testUnpack001() throws Exception {
        EbcdicLengthPackager pkgr = new EbcdicLengthPackager(3);

        pkgr.unpack(0, new byte[] { (byte) 0xF0, (byte) 0xf1, (byte) 0xf2, 0x00 });
        System.out.println(pkgr.describe());
        assertEquals(12, pkgr.getDataLength());

    }

    @Test
    public void testUnpack002() throws Exception {
        EbcdicLengthPackager pkgr = new EbcdicLengthPackager(5);

        expectedException.expect(Exception.class);

        pkgr.unpack(0, new byte[] { (byte) 0xF0, (byte) 0xf1, (byte) 0xf2, 0x00 });

        System.out.println(pkgr.describe());

        assertEquals(0, pkgr.getDataLength());

    }

    @Test
    public void testUnpack003() throws Exception {
        EbcdicLengthPackager pkgr = new EbcdicLengthPackager(2);


        pkgr.unpack(0, new byte[] { (byte) 0xF0, (byte) 0xf1, (byte) 0xf2, 0x00 });

        assertEquals(1, pkgr.getDataLength());

    }

    @Test
    public void testUnpack004() throws Exception {
        EbcdicLengthPackager pkgr = new EbcdicLengthPackager(2);

        pkgr.unpack(1, new byte[] { 0x30, (byte) 0xF1, (byte) 0xF2, 0x00 });

        assertEquals(12, pkgr.getDataLength());

    }

    @Test
    public void testPack005() throws Exception {
        EbcdicLengthPackager pkgr = new EbcdicLengthPackager(2);

        expectedException.expect(Exception.class);
        expectedException.expectMessage("Cannot pack 3 wide length 100 into LL packager.");
        pkgr.pack("100");
        assertEquals(12, pkgr.getDataLength());

    }
}
