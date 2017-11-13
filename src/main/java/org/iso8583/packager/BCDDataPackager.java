package org.iso8583.packager;

import org.iso8583.util.Utils;

public class BCDDataPackager extends DataPackager<String> {

    private Padding ignoreNibble = Padding.IgnoreRightmostPadNibble;

    public BCDDataPackager(int length) {
        super(length);
    }

    public BCDDataPackager(int length, Padding location) {
        super(length);
        ignoreNibble = location;

    }

    @Override
    public String describe() {

        return "[" + rawData + "]";
    }

    @Override
    public int unpack(int offset, byte[] bytes) throws Exception {
        int actualLengthofBytes = (length + 1) / 2;
        if ((offset + actualLengthofBytes) <= bytes.length) {
            StringBuilder l = new StringBuilder();
            for (int i = offset; i < offset + actualLengthofBytes; i++) {

                l.append(String.valueOf(bytes[i]));

            }

            try {
                if (length % 2 == 0) {
                    setRawData(l.toString());
                }
                else if (ignoreNibble == Padding.IgnoreRightmostPadNibble) {
                    setRawData(Utils.left(l.toString(), l.length() - 1));
                    set(Utils.left(l.toString(), l.length() - 1));
                }
                else if (ignoreNibble == Padding.IgnoreLeftmostPadNibble) {
                    setRawData(Utils.right(l.toString(), l.length() - 1));
                    set(Utils.right(l.toString(), l.length() - 1));
                }

            }
            catch (NumberFormatException ex) {
                throw new Exception(String.format("Encountered non numeric data %d.", l.toString()));
            }
            return offset + actualLengthofBytes;

        }
        else
            throw new Exception(String.format("Need to read %d bytes from offset %d, but total bytes available is %d.",
                    length, offset, bytes.length));

    }

    @Override
    public byte[] pack(String value) {
        return null;
        // TODO
    }

    @Override
    public byte[] pack() {
        // TODO Auto-generated method stub
        return null;
    }

}
