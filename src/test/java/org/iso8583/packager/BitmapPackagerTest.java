package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BitmapPackagerTest {

    @Test
    public void test() throws Exception {
        BitmapPackager pkgr = new BitmapPackager(0);

        pkgr.unpack(0,
                new byte[] { (byte) 0b01111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111,
                        (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b01111111,
                        (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111,
                        (byte) 0b11111111, (byte) 0b11111111 });

        assertEquals(
                "[Bitmap Hex] : [7FFFFFFFFFFFFFFF]" + String.format("%n")
                        + "[Bitmap Bit] : [2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 ]"
                        + String.format("%n"),
                pkgr.describe());

        pkgr.unpack(0,
                new byte[] { (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111,
                        (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b01111111,
                        (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111,
                        (byte) 0b11111111, (byte) 0b11111111 });

        assertEquals(
                "[Bitmap Hex] : [FFFFFFFFFFFFFFFF7FFFFFFFFFFFFFFF]" + String.format("%n")
                        + "[Bitmap Bit] : [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100 101 102 103 104 105 106 107 108 109 110 111 112 113 114 115 116 117 118 119 120 121 122 123 124 125 126 127 128 ]"
                        + String.format("%n"),
                pkgr.describe());

        // Set bit 0 to make secondarY bitmap available
        pkgr.unpack(0,
                new byte[] { (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                        (byte) 0x00, (byte) 0x00 });

        assertEquals(
                "[Bitmap Hex] : [8000000000000000]" + String.format("%n") + "[Bitmap Bit] : [1 ]" + String.format("%n"),
                pkgr.describe());

        // Bit 0 is 0 , no secondary bitmap. bit 1 to 7 set
        pkgr.unpack(0, new byte[] { (byte) 0b01111111, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00 });

        assertEquals("[Bitmap Hex] : [7F00000000000000]" + String.format("%n") + "[Bitmap Bit] : [2 3 4 5 6 7 8 ]"
                + String.format("%n"), pkgr.describe());

        pkgr.unpack(0,
                new byte[] { (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111,
                        (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b01111111,
                        (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111,
                        (byte) 0b11111111, (byte) 0b11111111 });

        System.out.println(pkgr.describe());
    }
}
