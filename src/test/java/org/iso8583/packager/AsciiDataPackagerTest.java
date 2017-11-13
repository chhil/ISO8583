package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AsciiDataPackagerTest {

    @Test
    public void unpack() throws Exception {
        AsciiDataPackager pkgr = new AsciiDataPackager(5);
        byte[] data = { 'C', 'H', 'H', 'i', 'L', 'm', 'u', 'r', 't', 'u', 'z', 'a' };

        int offset = pkgr.unpack(0, data);

        assertEquals("CHHiL", pkgr.getRawData());
        assertEquals(5, offset);
        assertEquals("[CHHiL]", pkgr.describe());

        offset = pkgr.unpack(5, data);
        assertEquals("murtu", pkgr.getRawData());
        assertEquals(10, offset);
        assertEquals("[murtu]", pkgr.describe());
    }

}
