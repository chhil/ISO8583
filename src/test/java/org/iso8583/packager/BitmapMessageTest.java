package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.iso8583.util.Utils;
import org.junit.Test;

public class BitmapMessageTest {

    final List<Field> subfields = new ArrayList<Field>() {
                                    {
                                        add(new Field("0", new NOPDataPackager(0)));
                                        add(new Field("1", new NOPDataPackager(0)));
                                        add(new Field("2", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("3", new EbcdicDataPackager(6)));
                                        add(new Field("4", new EbcdicDataPackager(12)));
                                        add(new Field("5", new EbcdicDataPackager(12)));
                                        add(new Field("6", new NOPDataPackager(0)));
                                        add(new Field("7", new EbcdicDataPackager(10)));
                                        add(new Field("8", new NOPDataPackager(0)));
                                        add(new Field("9", new NOPDataPackager(0)));
                                        add(new Field("10", new NOPDataPackager(0)));
                                        add(new Field("11", new EbcdicDataPackager(6)));
                                        add(new Field("12", new EbcdicDataPackager(12)));
                                        add(new Field("13", new EbcdicDataPackager(4)));
                                        add(new Field("14", new EbcdicDataPackager(4)));
                                        add(new Field("15", new NOPDataPackager(0)));
                                        add(new Field("16", new NOPDataPackager(0)));
                                        add(new Field("17", new NOPDataPackager(0)));
                                        add(new Field("18", new NOPDataPackager(0)));
                                        add(new Field("19", new EbcdicDataPackager(3)));
                                        add(new Field("20", new EbcdicDataPackager(3)));
                                        add(new Field("21", new EbcdicDataPackager(3)));
                                        add(new Field("22", new EbcdicDataPackager(12)));
                                        add(new Field("23", new EbcdicDataPackager(3)));
                                        add(new Field("24", new EbcdicDataPackager(3)));
                                        add(new Field("25", new EbcdicDataPackager(4)));
                                        add(new Field("26", new EbcdicDataPackager(4)));
                                        add(new Field("27", new EbcdicDataPackager(1)));
                                        add(new Field("28", new EbcdicDataPackager(9)));
                                        add(new Field("29", new EbcdicDataPackager(9)));
                                        add(new Field("30", new EbcdicDataPackager(24)));
                                        add(new Field("31", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("32", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("33", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("34", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("35", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("36", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("37", new EbcdicDataPackager(12)));
                                        add(new Field("38", new EbcdicDataPackager(6)));
                                        add(new Field("39", new EbcdicDataPackager(3)));
                                        add(new Field("40", new EbcdicDataPackager(3)));
                                        add(new Field("41", new EbcdicDataPackager(8)));
                                        add(new Field("42", new EbcdicDataPackager(15)));
                                        add(new Field("43", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("44", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("45", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("46", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("47", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                                        add(new Field("48", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                                        add(new Field("49", new EbcdicDataPackager(3)));
                                        add(new Field("50", new EbcdicDataPackager(3)));
                                        add(new Field("51", new EbcdicDataPackager(3)));
                                        add(new Field("52", new EbcdicDataPackager(8)));
                                        add(new Field("53", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("54", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                                        add(new Field("55", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                                        add(new Field("56", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("57", new EbcdicDataPackager(3)));
                                        add(new Field("58", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                                        add(new Field("59", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                                        add(new Field("60", new EbcdicLengthPackager(3), new BinaryDataPackager()));
                                        add(new Field("61", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                                        add(new Field("62", new EbcdicLengthPackager(3), new BinaryDataPackager()));
                                        add(new Field("63", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                                        add(new Field("64", new EbcdicDataPackager(8)));
                                    }
                                };
    final List<Field> fields    = new ArrayList<Field>() {
                                    {
                                        add(new Field("0", new EbcdicDataPackager(4)));
                                        add(new Field("1", new BitmapPackager(8), subfields));

                                    }
                                };

    /**
     * Unpack a byte stream, then pack it, should result in the same original
     * bytes.
     * 
     * @throws Exception
     */
    @Test
    public void unpackPacktest() throws Exception {


        BitmapMessage bm = new BitmapMessage(fields);

        String bytesInHex = "F1F1F2F0783407424640C014F1F5F3F7F4F7F5F4F0F5F1F5F4F9F0F0F2F3F9F4F8F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F1F8F1F7F1F1F0F8F1F0F5F5F5F3F0F3F1F2F0F0F0E2F4F1F6F0F0F0F0F1F0F0F1F1F0F0F5F6F9F7F1F5F5F1F5F4F9F0F3F1F0F3F4F7F7F1F0F2F3F3F7F1F2F3F4F5F6F7F8F9F0F1F2F3E0E2C7E0F0F1F1F6F0F0F0F0F0F0F0F0F0F1F2F3F4F5F6F7F8F9F04040404040F8F4F0F8F4F0F4F1F3D4E2F8F4F0F0F5FFFF0000F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F3F1F2F0F2F0F1F0F0C6D9D6E2E361C3C8C1D9D3C5E2A1A1A1A1A1A1A1A1A1A1A1A1A140E3C5D9D4E240C1D5C440C3D6D5C4C9E3C9D6D5E240E5C5D9E2C9D6D540C1C3C3C5D7E3C5C440C2E840E3C8C540C3C1D9C4D4C5D4C2C5D940F1F2F3F4F5F1F7F1F7F1F1F0F8F1F0F5F5F5F3F1F0F0F0F0F1E28583A4998540C59385948595A340C9C440404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F1F2F3F4F5F6F7F8F9F1F0F1F0F1F0F1F0F1F0F1F0F1F0F1F1C1E7C6C4F380000000F0F3";

        // System.out.println(ISOUtil.hexdump(StringUtils.hexStringToByteArray(bytesInHex)));
        // System.out.println(StringUtils.hexDump(StringUtils.hexStringToByteArray(bytesInHex),
        // 0, bytesInHex.length() / 2,
        // DataPackager.EBCDIC));
        bm.unpack(Utils.hexStringToByteArray(bytesInHex));
        // System.out.println(bm.describe());

        byte[] v = bm.pack();

        assertEquals(bytesInHex, Utils.bytesToHex(v));

        // System.out.println(bm.describe());
        // System.out.println(StringUtils.hexDump(v, 0, v.length,
        // DataPackager.EBCDIC));

        String describeExpected = "[         0] : [1120]\r\n" + 
                "[Bitmap    ] : [783407424640C014]\r\n" + 
                "[Bitmap    ] : [2 3 4 5 11 12 14 22 23 24 26 31 34 38 39 42 49 50 60 62 ]\r\n" + 
                "[         2] : [374754051549002]\r\n" + 
                "[         3] : [394800]\r\n" + 
                "[         4] : [000000000000]\r\n" + 
                "[         5] : [000000000000]\r\n" + 
                "[        11] : [000018]\r\n" + 
                "[        12] : [171108105553]\r\n" + 
                "[        14] : [0312]\r\n" + 
                "[        22] : [000S41600001]\r\n" + 
                "[        23] : [001]\r\n" + 
                "[        24] : [100]\r\n" + 
                "[        26] : [5697]\r\n" + 
                "[        31] : [515490310347710]\r\n" + 
                "[        34] : [371234567890123\\SG\\0116]\r\n" + 
                "[        38] : [000000]\r\n" + 
                "[        39] : [000]\r\n" + 
                "[        42] : [1234567890     ]\r\n" + 
                "[        49] : [840]\r\n" + 
                "[        50] : [840]\r\n" + 
                "[        60] : [D4E2F8F4F0F0F5FFFF0000F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F3F1F2F0F2F0F1F0F0C6D9D6E2E361C3C8C1D9D3C5E2A1A1A1A1A1A1A1A1A1A1A1A1A140E3C5D9D4E240C1D5C440C3D6D5C4C9E3C9D6D5E240E5C5D9E2C9D6D540C1C3C3C5D7E3C5C440C2E840E3C8C540C3C1D9C4D4C5D4C2C5D940F1F2F3F4F5F1F7F1F7F1F1F0F8F1F0F5F5F5F3F1F0F0F0F0F1E28583A4998540C59385948595A340C9C440404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F1F2F3F4F5F6F7F8F9F1F0F1F0F1F0F1F0F1F0F1F0F1]\r\n" + 
                "[        62] : [C1E7C6C4F380000000F0F3]\r\n" + "\r\n";
        assertEquals(describeExpected, bm.describe());
        assertEquals("374754051549002", bm.get("2"));
        assertEquals("394800", bm.get("3"));
        assertEquals("1120", bm.getMTI());
        assertEquals("1120", bm.get("0"));

    }

}
