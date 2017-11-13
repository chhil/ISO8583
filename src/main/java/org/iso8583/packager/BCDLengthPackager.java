package org.iso8583.packager;

import org.iso8583.util.Utils;

public class BCDLengthPackager extends LengthPackager {

    int             radix        = 10;
    private int     dataLength;
    private Padding ignoreNibble = Padding.IgnoreRightmostPadNibble;
    private char    padChar      = 0x0;;

    public BCDLengthPackager(int lengthOfLength) {
        super(lengthOfLength);
    }

    public BCDLengthPackager(int lengthOfLength, Padding location) {
        super(lengthOfLength);
        ignoreNibble = location;

    }

    public BCDLengthPackager(int lengthOfLength, Padding location, int radix) {
        super(lengthOfLength);
        ignoreNibble = location;
        this.radix = radix;

    }

    public BCDLengthPackager(int lengthOfLength, Padding location, int radix, char padChar) {
        super(lengthOfLength);
        ignoreNibble = location;
        this.padChar = padChar;
        this.radix = radix;

    }

    /**
     * This will unpack a byte array and extract the length. The length bytes
     * are in BCD.
     * e.g. byte array has 12 34 56 and offset is 0 and the length is 4 digits
     * wide [LLLL].
     * The length extracted will be 1234.
     * e.g. byte array has 12 34 56 and offset is 1 and the length is 4 digits
     * wide [LLLL].
     * The length extracted will be 3456
     * e.g. byte array has 12 34 56 and offset is 0 and the length is 3 digits
     * wide [LLL] and the pad char is leftmost .
     * The length extracted will be 234.
     * e.g. byte array has 12 34 56 and offset is 0 and the length is 3 digits
     * wide [LLL] and the pad char is rightmost .
     * The length extracted will be 123.
     */
    public int unpack(int offset, byte[] bytesIn) throws Exception {
        int actualLengthofBytes = (lengthOfLength + 1) / 2;
        byte[] lengthBytes;
        if ((offset + actualLengthofBytes) <= bytesIn.length) {
            lengthBytes = new byte[actualLengthofBytes];
            int k = 0;
            for (int i = offset; i < offset + actualLengthofBytes; i++) {

                lengthBytes[k++] = bytesIn[i];

            }

            StringBuilder l = new StringBuilder();

            for (int i = 0; i < actualLengthofBytes; i++) {
                l.append(String.valueOf((radix * (lengthBytes[i] / radix)) + lengthBytes[i] % radix));

            }
            try {
                if (lengthOfLength % 2 == 0) {
                    setDataLength(Integer.parseInt(l.toString()));
                }
                else if (ignoreNibble == Padding.IgnoreRightmostPadNibble) {
                    setDataLength(Integer.parseInt(Utils.left(l.toString(), l.length() - 1)));
                }
                else if (ignoreNibble == Padding.IgnoreLeftmostPadNibble) {
                    setDataLength(Integer.parseInt(Utils.right(l.toString(), l.length() - 1)));
                }

            }
            catch (NumberFormatException ex) {
                throw new Exception(String.format("Encountered non numeric data %d.", l.toString()));
            }
            return offset + actualLengthofBytes;

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

        return Utils.repeat('L', lengthOfLength) + "BCD" + "Var" + "["
                + Utils.leftPad(String.valueOf(getDataLength()), lengthOfLength, '0') + "]";

    }

    @Override
    public byte[] pack(String length) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public byte[] pack(byte[] lengthBytes) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
