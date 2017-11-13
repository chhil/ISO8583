package org.iso8583.packager;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

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

        System.out.println(Utils.hexDump(p.pack()));

        HashMap<String, String> h = new HashMap<String, String>() {
            {
                put("005", "55555");
                put("006", "666666");
                put("007", "7777777");
            }
        };

        assertEquals(Utils.bytesToHex("005005555550060066666660070077777777".getBytes()), Utils.bytesToHex(p.pack(h)));
        System.out.println(p.describe());


    }

}
