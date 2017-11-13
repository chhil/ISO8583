package org.iso8583.packager;

import org.iso8583.util.Compliance;
import org.iso8583.util.Utils;

public class BinaryDataPackager extends DataPackager<String> {

    private Padding ignoreNibble = Padding.IgnoreRightmostPadNibble;

    public BinaryDataPackager(int length) {
        super(length);
    }

    public BinaryDataPackager(int length, Padding location) {
        super(length);
        ignoreNibble = location;

    }

    public BinaryDataPackager() {

    }

    public BinaryDataPackager(Compliance<String> compliance) {
        super(compliance);
    }

    @Override
    public String describe() {

        return "[" + getCompliance().makeCompliant(get()) + "]";
    }
    @Override
    public int unpack(int offset, byte[] bytes) throws Exception {

        if ((offset + length) <= bytes.length) {
            StringBuilder l = new StringBuilder();
            byte[] data = new byte[length];
            int k = 0;
            for (int i = offset; i < offset + length; i++) {

                data[k++] = bytes[i];
                l.append(String.format("%02x", bytes[i] & 0xff));

            }

            set(l.toString().toUpperCase());
            setRawData(l.toString().toUpperCase());

            return offset + length;

        }
        else
            throw new Exception(String.format("Need to read %d bytes from offset %d, but total bytes available is %d.",
                    length, offset, bytes.length));

    }

    @Override
    public byte[] pack(String value) {
        set(value);
        setRawData(Utils.hexStringToByteArray(value));
        return Utils.hexStringToByteArray(value);

    }

    @Override
    public byte[] pack() {
        return Utils.hexStringToByteArray(get());
    }

}
