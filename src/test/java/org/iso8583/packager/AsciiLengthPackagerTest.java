package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AsciiLengthPackagerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void testUnpack001() throws Exception {
        AsciiLengthPackager pkgr = new AsciiLengthPackager(3);

        pkgr.unpack(0, new byte[] { 0x30, 0x31, 0x31, 0x00 });
        System.out.println(pkgr.describe());
        assertEquals(11, pkgr.getDataLength());

    }

    @Test
    public void testUnpack002() throws Exception {
        AsciiLengthPackager pkgr = new AsciiLengthPackager(5);

        expectedException.expect(Exception.class);
        // expectedException
        // .expectMessage(is("Need to read 10 bytes from offset 0, but total
        // data available 4."));

        pkgr.unpack(0, new byte[] { 0x30, 0x31, 0x31, 0x00 });

        System.out.println(pkgr.describe());

        assertEquals(0, pkgr.getDataLength());

    }

    @Test
    public void testUnpack003() throws Exception {
        AsciiLengthPackager pkgr = new AsciiLengthPackager(2);

        expectedException.expect(Exception.class);
        // expectedException
        // .expectMessage(is("Need to read 10 bytes from offset 0, but total
        // data available 4."));

        pkgr.unpack(2, new byte[] { 0x30, 0x31, 0x31, 0x00 });

        assertEquals(0, pkgr.getDataLength());

    }

    @Test
    public void testUnpack004() throws Exception {
        AsciiLengthPackager pkgr = new AsciiLengthPackager(2);

        pkgr.unpack(1, new byte[] { 0x30, 0x31, 0x32, 0x00 });

        assertEquals(12, pkgr.getDataLength());

    }

    @Test
    public void testPack005() throws Exception {
        AsciiLengthPackager pkgr = new AsciiLengthPackager(2);


        expectedException.expect(Exception.class);
        expectedException.expectMessage("Cannot pack 3 wide length 100 into LL packager.");
        pkgr.pack("100");
        assertEquals(12, pkgr.getDataLength());

    }
}
