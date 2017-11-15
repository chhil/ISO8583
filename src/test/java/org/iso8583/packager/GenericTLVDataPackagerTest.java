package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.iso8583.util.Compliance;
import org.iso8583.util.ComplianceProtect;
import org.iso8583.util.TLVCompliance;
import org.iso8583.util.Utils;
import org.junit.Test;

public class GenericTLVDataPackagerTest {

    @Test
    public void test() throws Exception {
        String data = "001001100200222003003333";
        GenericTLVDataPackager p = new GenericTLVDataPackager(new AsciiDataPackager(3), new AsciiLengthPackager(3),
                new AsciiDataPackager());
        p.setLength(data.length());
        p.unpack(0, data.getBytes());

        System.out.println(p.describe());

        HashMap<String, String> h = new HashMap<String, String>() {
            {
                put("005", "55555");
                put("006", "666666");
                put("007", "7777777");
            }
        };

        assertEquals(Utils.bytesToHex("005005555550060066666660070077777777".getBytes()), Utils.bytesToHex(p.pack(h)));

    }

    @Test
    public void tlvComplianceTest() throws Exception {

        Map<String, Compliance<String>> complianceMap = new HashMap<String, Compliance<String>>() {
            {
                put("006", new ComplianceProtect());
            }
        };
        TLVCompliance tlvCompliance = new TLVCompliance(complianceMap);

        String data = "001001100200222003003333";
        GenericTLVDataPackager p = new GenericTLVDataPackager(new AsciiDataPackager(3), new AsciiLengthPackager(3),
                new AsciiDataPackager(), tlvCompliance);
        p.setLength(data.length());
        p.unpack(0, data.getBytes());


        HashMap<String, String> h = new HashMap<String, String>() {
            {
                put("005", "55555");
                put("006", "666666666666666");
                put("007", "7777777");
            }
        };

        assertEquals(Utils.bytesToHex("005005555550060156666666666666660070077777777".getBytes()),
                Utils.bytesToHex(p.pack(h)));

        // @formatter = off
        String expected = 
                "[\r\n" + 
                "                 [tag  ][length][value]\r\n" + 
                "                 [005  ][5     ][55555]\r\n" + 
                "                 [006  ][15    ][666666_____6666]\r\n" + 
                "                 [007  ][7     ][7777777]\r\n" + 
                "               ]";
        // @formatter = on
        assertEquals(expected, p.describe());
    }

}
