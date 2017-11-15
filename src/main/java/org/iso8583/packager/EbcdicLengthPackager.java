package org.iso8583.packager;

import java.nio.ByteBuffer;

import org.iso8583.util.Utils;

public class EbcdicLengthPackager extends LengthPackager {

    int         radix          = 10;
    private int dataLength;

    public EbcdicLengthPackager(int lengthOfLength) {
        super(lengthOfLength);
    }

    public int unpack(int offset, byte[] bytesIn) throws Exception {

        byte[] lengthBytes;
        if ((offset + lengthOfLength) <= bytesIn.length) {
            lengthBytes = new byte[lengthOfLength];
            int k = 0;
            for (int i = offset; i < offset + lengthOfLength; i++) {
                if (bytesIn[i] <= 0xF0 && bytesIn[i] <= 0xF9)
                    lengthBytes[k++] = bytesIn[i];
                else
                    throw new Exception(String.format("Encountered non numeric data at offset %d.", i));
            }

            String temp = EBCDIC.decode(ByteBuffer.wrap(lengthBytes)).toString();
            setDataLength(Integer.parseInt(temp));
            // The following works too
            // int len = 0;
            // for (int i = 0; i < lengthOfLength; i++) {
            // len = len * 10 + (lengthBytes[i] & 0x0F);
            // }
            // setDataLength(len);


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

        return Utils.repeat('L', lengthOfLength) + "Ebcdic" + "Var" + "["
                + Utils.leftPad(String.valueOf(getDataLength()), lengthOfLength, '0') + "]";

    }

    @Override
    public byte[] pack(String length) throws Exception {

        if (length.length() > lengthOfLength) {

            throw new Exception(String.format("Cannot pack %d wide length %s into %s packager.", length.length(),
                    length, Utils.leftPad("L", lengthOfLength, "L")));
        }

        setDataLength(Integer.parseInt(length));
        // e.g. conver say 123 to F0F1F3
        return EBCDIC.encode(Utils.leftPad(length, lengthOfLength, "0")).array();

    }

    @Override
    public byte[] pack(byte[] lengthBytes) throws Exception {
        if (lengthBytes.length > lengthOfLength) {

            throw new Exception(String.format("Cannot pack %d wide length %s into %s packager.", lengthBytes.length,
                    new String(lengthBytes), Utils.leftPad("L", lengthOfLength, "L")));
        }

        return EBCDIC.encode(new String(lengthBytes)).array();
    }

}
