package org.iso8583.packager;

import java.util.BitSet;

import org.iso8583.util.Utils;

public class AsciiBitmapPackager extends BitmapPackager {

    /**
     * The number of bytes that make the bitmap.
     * An ascii bitmap looks like the following, its the hex data that comes on
     * the wire.
     * 
     * <pre>
     * 
     * hex (base16)    0x34, 0x32, 0x33, 0x34, 0x30, 0x31, 0x30, 0x31, 0x30, 0x38, 0x30, 0x30, 0x30, 0x30, 0x30, 0x43
     * char               4     2     3     4     0     1     0     1     0     8     0     0     0     0     0     C
     * bin (base2)     0100  0010  0011  0100  0000  0001  0000  0001  0000  1000  0000  0000  0000  0000  0000  1100
     *                 |        |           |           |           |           |           |           |           |
     * pos             1        8          16          24          32          40          48          56          64  
     * 
     * Bits set (have a value of 1) are counted from left to right starting with pos 1.
     * Bits set : 2,7,11,12,14,24,32,37,61,62
     * 
     * The bit in pos 1 is not set hence there is no secondary bitmap. If it was set there would be another 
     * set of 16 bytes following this one that would need to be interpreted 
     * we did above except the pos would continue from 65 to 128
     * </pre>
     * 
     * @param length
     */
    public AsciiBitmapPackager(int length) {
        super(length);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int unpack(int offset, byte[] bytes, boolean isMsbBitmapExtension) throws Exception {

        StringBuilder builder = new StringBuilder();
        // check if left most bit is 1
        if (isMsbBitmapExtension) {
            if ((Character.digit((char) bytes[offset], 16) & 0x08) == 1) {
                length = 32; // 16 bytes for 64 bits , 32bytes for 128
            }
        }
        bitmap = new BitSet(length * 8);
        StringBuilder l = new StringBuilder();
        for (int i = 0; i < length; i++) {
            l.append(String.format("%02x", bytes[i + offset] & 0xff));
            builder.append(Utils.leftPad(Integer.toBinaryString(Character.digit(bytes[offset + i], 16)), 4, '0'));

        }

        byte[] arr = builder.toString().getBytes();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0x31) {
                bitmap.set(i + 1);
            }

        }
        m.put("0", l.toString().toUpperCase());
        return offset + length;
    }

    @Override
    public int unpack(int offset, byte[] bytes) throws Exception {

        StringBuilder builder = new StringBuilder();
        // check if left most bit is 1
        if (isMsbBitmapExtension()) {
            if ((Character.digit((char) bytes[offset], 16) & 0x08) == 1) {
                length = 32; // 16 bytes for 64 bits , 32bytes for 128
            }
        }
        bitmap = new BitSet(length * 8);
        StringBuilder l = new StringBuilder();
        for (int i = 0; i < length; i++) {
            l.append(String.format("%02x", bytes[i + offset] & 0xff));
            builder.append(Utils.leftPad(Integer.toBinaryString(Character.digit(bytes[offset + i], 16)), 4, '0'));

        }

        byte[] arr = builder.toString().getBytes();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0x31) {
                bitmap.set(i + 1);
            }

        }
        m.put("0", l.toString().toUpperCase());
        return offset + length;
    }



    protected byte[] packBitmap(int bitmapSize) {

        // todo MSB set to 1 if more than 64
        byte[] p = Utils.toByteArray(bitmap);
        return Utils.bytesToHex(p).getBytes();
    }

}
