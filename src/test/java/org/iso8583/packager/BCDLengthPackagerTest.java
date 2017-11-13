package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BCDLengthPackagerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void testUnpack001() throws Exception {
        BCDLengthPackager pkgr = new BCDLengthPackager(2);

        pkgr.unpack(0, new byte[] { 12, 24, 00 });
        System.out.println(pkgr.describe());
        assertEquals(12, pkgr.getDataLength());

        pkgr = new BCDLengthPackager(4);

        pkgr.unpack(0, new byte[] { 12, 24, 00 });
        System.out.println(pkgr.describe());
        assertEquals(1224, pkgr.getDataLength());

        pkgr = new BCDLengthPackager(3);

        pkgr.unpack(0, new byte[] { 12, 24, 00 });
        System.out.println(pkgr.describe());
        assertEquals(122, pkgr.getDataLength());

        pkgr = new BCDLengthPackager(3, Padding.IgnoreLeftmostPadNibble);

        pkgr.unpack(0, new byte[] { 12, 24, 00 });
        System.out.println(pkgr.describe());
        assertEquals(224, pkgr.getDataLength());

        pkgr = new BCDLengthPackager(3, Padding.IgnoreLeftmostPadNibble, 16);
        pkgr.unpack(0, new byte[] { 0x12, 0x24, 00 }); // this is equivalent to
                                                       // {18 36 00}}
        System.out.println(pkgr.describe());
        assertEquals(836, pkgr.getDataLength());

    }
}
