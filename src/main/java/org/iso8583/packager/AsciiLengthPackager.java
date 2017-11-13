package org.iso8583.packager;

import org.iso8583.util.Utils;

public class AsciiLengthPackager extends LengthPackager {

    int         radix          = 10;
    private int dataLength;

    public AsciiLengthPackager(int lengthOfLength) {
        super(lengthOfLength);
    }

    public int unpack(int offset, byte[] bytesIn) throws Exception {

        byte[] lengthBytes;
        if ((offset + lengthOfLength) <= bytesIn.length) {
            lengthBytes = new byte[lengthOfLength];
            int k = 0;
            for (int i = offset; i < offset + lengthOfLength; i++) {
                if (bytesIn[i] >= 0x30 && bytesIn[i] <= 0x39)
                    lengthBytes[k++] = bytesIn[i];
                else
                    throw new Exception(String.format("Encountered non numeric data at offset %d.", i));
            }
            setDataLength(Integer.parseInt(new String(lengthBytes), radix));

            return offset + lengthOfLength;

        }
        else
            throw new Exception(String.format("Need to read %d bytes from offset %d, but total bytes available is %d.",
                    lengthOfLength, offset, bytesIn.length));

    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    @Override
    public String describe() {

        return Utils.repeat('L', lengthOfLength) + "Ascii" + "Var" + "["
                + Utils.leftPad(String.valueOf(getDataLength()), lengthOfLength, '0') + "]";

    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    @Override
    public byte[] pack(String length) {
        return null;
    }

    @Override
    public byte[] pack(byte[] lengthBytes) throws Exception {

        return null;
    }

}
