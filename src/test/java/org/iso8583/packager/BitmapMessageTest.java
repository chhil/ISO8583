package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iso8583.util.ComplianceProtect;
import org.iso8583.util.Utils;
import org.junit.Test;

public class BitmapMessageTest {

    /**
     * Unpack a byte stream, then pack it, should result in the same original
     * bytes.
     * Field 2 is set up with a protct compliance (masked),
     * 
     * @throws Exception
     */
    @Test
    public void unpackPacktest() throws Exception {
        final List<Field<String>> subfields = new ArrayList<Field<String>>() {
            {
                add(new Field<String>("0", new NOPDataPackager(0)));
                add(new Field<String>("1", new NOPDataPackager(0)));
                add(new Field<String>("2", new EbcdicLengthPackager(2),
                        new EbcdicDataPackager(new ComplianceProtect())));
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
                // 63 NOPs and then the 64 one set for field 128
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("128", new EbcdicDataPackager(8)));
            }
        };
        final List<Field<String>> fields = new ArrayList<Field<String>>() {
            {
                add(new Field<String>("0", new EbcdicDataPackager(4)));
                add(new Field<String>("1", new BitmapPackager(8), subfields));

            }
        };
        BitmapMessage msg = new BitmapMessage(fields);

        // First nibble F is 1111 , mSB is set hence secondary bitmap present
        // Last bit is set so 127 is set
        String bitmap = "F83407424640C0140000000000000001";
        String dataForBit127 = "F0F0F0F0F0F0F0F0";
        String bytesInHex = "F1F1F2F0" + bitmap
                + "F1F5F3F7F4F7F5F4F0F5F1F5F4F9F0F0F2F3F9F4F8F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F1F8F1F7F1F1F0F8F1F0F5F5F5F3F0F3F1F2F0F0F0E2F4F1F6F0F0F0F0F1F0F0F1F1F0F0F5F6F9F7F1F5F5F1F5F4F9F0F3F1F0F3F4F7F7F1F0F2F3F3F7F1F2F3F4F5F6F7F8F9F0F1F2F3E0E2C7E0F0F1F1F6F0F0F0F0F0F0F0F0F0F1F2F3F4F5F6F7F8F9F04040404040F8F4F0F8F4F0F4F1F3D4E2F8F4F0F0F5FFFF0000F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F3F1F2F0F2F0F1F0F0C6D9D6E2E361C3C8C1D9D3C5E2A1A1A1A1A1A1A1A1A1A1A1A1A140E3C5D9D4E240C1D5C440C3D6D5C4C9E3C9D6D5E240E5C5D9E2C9D6D540C1C3C3C5D7E3C5C440C2E840E3C8C540C3C1D9C4D4C5D4C2C5D940F1F2F3F4F5F1F7F1F7F1F1F0F8F1F0F5F5F5F3F1F0F0F0F0F1E28583A4998540C59385948595A340C9C440404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F1F2F3F4F5F6F7F8F9F1F0F1F0F1F0F1F0F1F0F1F0F1F0F1F1C1E7C6C4F380000000F0F3"
                + dataForBit127;

        // System.out.println(ISOUtil.hexdump(StringUtils.hexStringToByteArray(bytesInHex)));
        // System.out.println(StringUtils.hexDump(StringUtils.hexStringToByteArray(bytesInHex),
        // 0, bytesInHex.length() / 2,
        // DataPackager.EBCDIC));
        msg.unpack(Utils.hexStringToByteArray(bytesInHex));
        // System.out.println(bm.describe());

        byte[] v = msg.pack();

        // System.out.print(msg.describe());
        assertEquals(bytesInHex, Utils.bytesToHex(v));

        // System.out.println(bm.describe());
        // System.out.println(StringUtils.hexDump(v, 0, v.length,
        // DataPackager.EBCDIC));
      //@formatter:off
        String describeExpected = 
        "[         0] : [1120]" + String.format("%n") + String.format("%n")+ 
        "[Bitmap Hex] : [F83407424640C0140000000000000001]" + String.format("%n") + 
        "[Bitmap Bit] : [2 3 4 5 11 12 14 22 23 24 26 31 34 38 39 42 49 50 60 62 128 ]" + String.format("%n") + 
        "[         2] : [374754_____9002]" + String.format("%n") + 
        "[         3] : [394800]" + String.format("%n") + 
        "[         4] : [000000000000]" + String.format("%n") + 
        "[         5] : [000000000000]" + String.format("%n") + 
        "[        11] : [000018]" + String.format("%n") + 
        "[        12] : [171108105553]" + String.format("%n") + 
        "[        14] : [0312]" + String.format("%n") + 
        "[        22] : [000S41600001]" + String.format("%n") + 
        "[        23] : [001]" + String.format("%n") + 
        "[        24] : [100]" + String.format("%n") + 
        "[        26] : [5697]" + String.format("%n") + 
        "[        31] : [515490310347710]" + String.format("%n") + 
        "[        34] : [371234567890123\\SG\\0116]" + String.format("%n") + 
        "[        38] : [000000]" + String.format("%n") + 
        "[        39] : [000]" + String.format("%n") + 
        "[        42] : [1234567890     ]" + String.format("%n") + 
        "[        49] : [840]" + String.format("%n") + 
        "[        50] : [840]" + String.format("%n") + 
        "[        60] : [D4E2F8F4F0F0F5FFFF0000F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F3F1F2F0F2F0F1F0F0C6D9D6E2E361C3C8C1D9D3C5E2A1A1A1A1A1A1A1A1A1A1A1A1A140E3C5D9D4E240C1D5C440C3D6D5C4C9E3C9D6D5E240E5C5D9E2C9D6D540C1C3C3C5D7E3C5C440C2E840E3C8C540C3C1D9C4D4C5D4C2C5D940F1F2F3F4F5F1F7F1F7F1F1F0F8F1F0F5F5F5F3F1F0F0F0F0F1E28583A4998540C59385948595A340C9C440404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F1F2F3F4F5F6F7F8F9F1F0F1F0F1F0F1F0F1F0F1F0F1]" + String.format("%n") + 
        "[        62] : [C1E7C6C4F380000000F0F3]" + String.format("%n") + 
        "[       128] : [00000000]" + String.format("%n") +String.format("%n") ;
      //@formatter:on
        

        assertEquals(describeExpected, msg.describe());
        assertEquals("374754051549002", msg.get("2"));
        assertEquals("394800", msg.get("3"));
        assertEquals("1120", msg.getMTI());
        assertEquals("1120", msg.get("0"));

    }

    /**
     * Test where fiedl 2 is set up as a TLV field.
     * 
     * @throws Exception
     */
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
        // System.out.println(Utils.hexDump(x));
        // System.out.println(bm.describe());
        //@formatter:on
        String expected = "[         0] : [0100]\r\n" + "\r\n" + "[Bitmap Hex] : [4000000000000000]\r\n"
                + "[Bitmap Bit] : [2 ]\r\n"
                + "[         2] : [{[005][5][55555]}{[006][6][666666]}{[007][7][7777777]}]\r\n" + "\r\n";
        //@formatter:off
        assertEquals(expected, bm.describe());
    }

    /**
     * Test where field 2 is set up as a bitmap.
     * 
     * @throws Exception
     */
    @Test
    public void unpackPacktest003() throws Exception {

        final List<Field<String>> field60DataElements = new ArrayList<Field<String>>() {
            {
                add(new Field<String>("0", new NOPDataPackager(0)));
                add(new Field<String>("1", new EbcdicDataPackager(1)));
                add(new Field<String>("2", new EbcdicDataPackager(1)));
                add(new Field<String>("3", new EbcdicDataPackager(1)));
                add(new Field<String>("4", new EbcdicDataPackager(1)));

            }
        };

        final List<Field<String>> subfields = new ArrayList<Field<String>>() {
            {
                add(new Field<String>("0", new NOPDataPackager(0)));
                add(new Field<String>("1", new NOPDataPackager(0)));
                add(new Field<String>("2", new EbcdicLengthPackager(4), new BitmapPackager(4, false),
                        field60DataElements));
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
                // 63 NOPs and then the 64 one set for field 128
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("18", new NOPDataPackager(0)));
                add(new Field<String>("15", new NOPDataPackager(0)));
                add(new Field<String>("16", new NOPDataPackager(0)));
                add(new Field<String>("17", new NOPDataPackager(0)));
                add(new Field<String>("128", new EbcdicDataPackager(8)));
            }
        };
        final List<Field<String>> fields = new ArrayList<Field<String>>() {
            {
                add(new Field<String>("0", new EbcdicDataPackager(4)));
                add(new Field<String>("1", new BitmapPackager(8), subfields));

            }
        };



        BitmapMessage msg = new BitmapMessage(fields);


        String bitmapField2 = "F0000000";
        String field2 = "F0F0F0F9" + bitmapField2 + "F0F1F2F3";
        String bitmap = "783407424640C014";

        String bytesInHex = "F1F1F2F0" + bitmap + field2
                + "F3F9F4F8F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F1F8F1F7F1F1F0F8F1F0F5F5F5F3F0F3F1F2F0F0F0E2F4F1F6F0F0F0F0F1F0F0F1F1F0F0F5F6F9F7F1F5F5F1F5F4F9F0F3F1F0F3F4F7F7F1F0F2F3F3F7F1F2F3F4F5F6F7F8F9F0F1F2F3E0E2C7E0F0F1F1F6F0F0F0F0F0F0F0F0F0F1F2F3F4F5F6F7F8F9F04040404040F8F4F0F8F4F0F4F1F3D4E2F8F4F0F0F5FFFF0000F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F240404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F3F1F2F0F2F0F1F0F0C6D9D6E2E361C3C8C1D9D3C5E2A1A1A1A1A1A1A1A1A1A1A1A1A140E3C5D9D4E240C1D5C440C3D6D5C4C9E3C9D6D5E240E5C5D9E2C9D6D540C1C3C3C5D7E3C5C440C2E840E3C8C540C3C1D9C4D4C5D4C2C5D940F1F2F3F4F5F1F7F1F7F1F1F0F8F1F0F5F5F5F3F1F0F0F0F0F1E28583A4998540C59385948595A340C9C440404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F1F2F3F4F5F6F7F8F9F1F0F1F0F1F0F1F0F1F0F1F0F1F0F1F1C1E7C6C4F380000000F0F3";

        msg.unpack(Utils.hexStringToByteArray(bytesInHex));
        //System.out.println(msg.describe());
        //@formatter:off
        String expected = "[         0] : [1120]\r\n" + 
                "\r\n" + 
                "[Bitmap Hex] : [783407424640C014]\r\n" + 
                "[Bitmap Bit] : [2 3 4 5 11 12 14 22 23 24 26 31 34 38 39 42 49 50 60 62 ]\r\n" + 
                "[         2] : \r\n" + 
                "[Bitmap Hex] : [F000000000000000]\r\n" + 
                "[Bitmap Bit] : [1 2 3 4 ]\r\n" + 
                "[         1] : [0]\r\n" + 
                "[         2] : [1]\r\n" + 
                "[         3] : [2]\r\n" + 
                "[         4] : [3]\r\n" + 
                "\r\n" + 
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
                "[        62] : [C1E7C6C4F380000000F0F3]\r\n\r\n";
        //@formatter:on
        assertEquals(expected, msg.describe());

    }
}
