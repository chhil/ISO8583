package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iso8583.util.Utils;
import org.junit.Test;

public class BitmapMessageTest {

    /**
     * Unpack a byte stream, then pack it, should result in the same original
     * bytes.
     * 
     * @throws Exception
     */
    @Test
    public void unpackPacktest() throws Exception {
        final List<Field<String>> subfields = new ArrayList<Field<String>>() {
            {
                add(new Field<String>("0", new NOPDataPackager(0)));
                add(new Field<String>("1", new NOPDataPackager(0)));
                add(new Field<String>("2", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("3", new EbcdicDataPackager(6)));
                add(new Field<String>("4", new EbcdicDataPackager(12)));
                add(new Field<String>("5", new EbcdicDataPackager(12)));
                add(new Field<String>("6", new NOPDataPackager(0)));
                add(new Field<String>("7", new EbcdicDataPackager(10)));
                add(new Field<String>("8", new NOPDataPackager(0)));
                add(new Field<String>("9", new NOPDataPackager(0)));
                add(new Field<String>("10", new NOPDataPackager(0)));
                add(new Field<String>("11", new EbcdicDataPackager(6)));
                add(new Field<String>("12", new EbcdicDataPackager(12)));
                add(new Field<String>("13", new EbcdicDataPackager(4)));
                add(new Field<String>("14", new EbcdicDataPackager(4)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("19", new EbcdicDataPackager(3)));
                add(new Field<String>("20", new EbcdicDataPackager(3)));
                add(new Field<String>("21", new EbcdicDataPackager(3)));
                add(new Field<String>("22", new EbcdicDataPackager(12)));
                add(new Field<String>("23", new EbcdicDataPackager(3)));
                add(new Field<String>("24", new EbcdicDataPackager(3)));
                add(new Field<String>("25", new EbcdicDataPackager(4)));
                add(new Field<String>("26", new EbcdicDataPackager(4)));
                add(new Field<String>("27", new EbcdicDataPackager(1)));
                add(new Field<String>("28", new EbcdicDataPackager(9)));
                add(new Field<String>("29", new EbcdicDataPackager(9)));
                add(new Field<String>("30", new EbcdicDataPackager(24)));
                add(new Field<String>("31", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("32", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("33", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("34", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("35", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("36", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("37", new EbcdicDataPackager(12)));
                add(new Field<String>("38", new EbcdicDataPackager(6)));
                add(new Field<String>("39", new EbcdicDataPackager(3)));
                add(new Field<String>("40", new EbcdicDataPackager(3)));
                add(new Field<String>("41", new EbcdicDataPackager(8)));
                add(new Field<String>("42", new EbcdicDataPackager(15)));
                add(new Field<String>("43", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("44", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("45", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("46", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("47", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                add(new Field<String>("48", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                add(new Field<String>("49", new EbcdicDataPackager(3)));
                add(new Field<String>("50", new EbcdicDataPackager(3)));
                add(new Field<String>("51", new EbcdicDataPackager(3)));
                add(new Field<String>("52", new EbcdicDataPackager(8)));
                add(new Field<String>("53", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("54", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                add(new Field<String>("55", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                add(new Field<String>("56", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("57", new EbcdicDataPackager(3)));
                add(new Field<String>("58", new EbcdicLengthPackager(2), new EbcdicDataPackager()));
                add(new Field<String>("59", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                add(new Field<String>("60", new EbcdicLengthPackager(3), new BinaryDataPackager()));
                add(new Field<String>("61", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                add(new Field<String>("62", new EbcdicLengthPackager(3), new BinaryDataPackager()));
                add(new Field<String>("63", new EbcdicLengthPackager(3), new EbcdicDataPackager()));
                add(new Field<String>("64", new EbcdicDataPackager(8)));
            }
        };
        final List<Field<String>> fields = new ArrayList<Field<String>>() {
            {
                add(new Field<String>("0", new EbcdicDataPackager(4)));
                add(new Field<String>("1", new BitmapPackager(8), subfields));

            }
        };
        BitmapMessage msg = new BitmapMessage(fields);

        String bytesInHex = "F1F1F2F0783407424640C014F1F5F3F7F4F7F5F4F0F5F1F5F4F9F0F0F2F3F9F4F8F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F1F8F1F7F1F1F0F8F1F0F5F5F5F3F0F3F1F2F0F0F0E2F4F1F6F0F0F0F0F1F0F0F1F1F0F0F5F6F9F7F1F5F5F1F5F4F9F0F3F1F0F3F4F7F7F1F0F2F3F3F7F1F2F3F4F5F6F7F8F9F0F1F2F3E0E2C7E0F0F1F1F6F0F0F0F0F0F0F0F0F0F1F2F3F4F5F6F7F8F9F04040404040F8F4F0F8F4F0F4F1F3D4E2F8F4F0F0F5FFFF0000F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F3F1F2F0F2F0F1F0F0C6D9D6E2E361C3C8C1D9D3C5E2A1A1A1A1A1A1A1A1A1A1A1A1A140E3C5D9D4E240C1D5C440C3D6D5C4C9E3C9D6D5E240E5C5D9E2C9D6D540C1C3C3C5D7E3C5C440C2E840E3C8C540C3C1D9C4D4C5D4C2C5D940F1F2F3F4F5F1F7F1F7F1F1F0F8F1F0F5F5F5F3F1F0F0F0F0F1E28583A4998540C59385948595A340C9C440404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F1F2F3F4F5F6F7F8F9F1F0F1F0F1F0F1F0F1F0F1F0F1F0F1F1C1E7C6C4F380000000F0F3";

        // System.out.println(ISOUtil.hexdump(StringUtils.hexStringToByteArray(bytesInHex)));
        // System.out.println(StringUtils.hexDump(StringUtils.hexStringToByteArray(bytesInHex),
        // 0, bytesInHex.length() / 2,
        // DataPackager.EBCDIC));
        msg.unpack(Utils.hexStringToByteArray(bytesInHex));
        // System.out.println(bm.describe());

        byte[] v = msg.pack();

        System.out.print(msg.describe());
        assertEquals(bytesInHex, Utils.bytesToHex(v));

        // System.out.println(bm.describe());
        // System.out.println(StringUtils.hexDump(v, 0, v.length,
        // DataPackager.EBCDIC));

        String describeExpected = "[         0] : [1120]\r\n" + "[Bitmap Hex] : [783407424640C014]\r\n"
                + "[Bitmap Bit] : [2 3 4 5 11 12 14 22 23 24 26 31 34 38 39 42 49 50 60 62 ]\r\n"
                + "[         2] : [374754051549002]\r\n" + "[         3] : [394800]\r\n"
                + "[         4] : [000000000000]\r\n" + "[         5] : [000000000000]\r\n"
                + "[        11] : [000018]\r\n" + "[        12] : [171108105553]\r\n" + "[        14] : [0312]\r\n"
                + "[        22] : [000S41600001]\r\n" + "[        23] : [001]\r\n" + "[        24] : [100]\r\n"
                + "[        26] : [5697]\r\n" + "[        31] : [515490310347710]\r\n"
                + "[        34] : [371234567890123\\SG\\0116]\r\n" + "[        38] : [000000]\r\n"
                + "[        39] : [000]\r\n" + "[        42] : [1234567890     ]\r\n" + "[        49] : [840]\r\n"
                + "[        50] : [840]\r\n"
                + "[        60] : [D4E2F8F4F0F0F5FFFF0000F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F3F1F2F0F2F0F1F0F0C6D9D6E2E361C3C8C1D9D3C5E2A1A1A1A1A1A1A1A1A1A1A1A1A140E3C5D9D4E240C1D5C440C3D6D5C4C9E3C9D6D5E240E5C5D9E2C9D6D540C1C3C3C5D7E3C5C440C2E840E3C8C540C3C1D9C4D4C5D4C2C5D940F1F2F3F4F5F1F7F1F7F1F1F0F8F1F0F5F5F5F3F1F0F0F0F0F1E28583A4998540C59385948595A340C9C440404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F1F2F3F4F5F6F7F8F9F1F0F1F0F1F0F1F0F1F0F1F0F1]\r\n"
                + "[        62] : [C1E7C6C4F380000000F0F3]\r\n" + "\r\n";
        assertEquals(describeExpected, msg.describe());
        assertEquals("374754051549002", msg.get("2"));
        assertEquals("394800", msg.get("3"));
        assertEquals("1120", msg.getMTI());
        assertEquals("1120", msg.get("0"));

    }

    @Test
    public void unpackPacktest2() throws Exception {
        final List<Field> subfields = new ArrayList<Field>() {
            {
                add(new Field<String>("0", new NOPDataPackager(0)));
                add(new Field<String>("1", new NOPDataPackager(0)));
                add(new Field<Map<String, String>>("2", new AsciiLengthPackager(3), new GenericTLVDataPackager(
                        new AsciiDataPackager(3), new AsciiLengthPackager(3), new AsciiDataPackager())));

            }
        };

        final List<Field<String>> fields = new ArrayList<Field<String>>() {
            {
                add(new Field("0", new AsciiDataPackager(4)));
                add(new Field("1", new BitmapPackager(8), subfields));

            }
        };

        BitmapMessage bm = new BitmapMessage<>(fields);

        HashMap<String, String> h = new HashMap<String, String>() {
            {
                put("005", "55555");
                put("006", "666666");
                put("007", "7777777");
            }
        };

        bm.setMTI("0100");
        bm.set("2", h);
        byte[] x = bm.pack();
        Map s = (Map) bm.get("2");
        System.out.println(Utils.hexDump(x));
        System.out.println(bm.describe());
    }
}
