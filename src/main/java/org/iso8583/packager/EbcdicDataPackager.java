package org.iso8583.packager;

import java.nio.ByteBuffer;

import org.iso8583.util.Compliance;

public class EbcdicDataPackager extends DataPackager<String> {

    private Padding ignoreNibble = Padding.IgnoreRightmostPadNibble;

    public EbcdicDataPackager(int length) {
        super(length);
    }

    public EbcdicDataPackager() {
        super();
    }

    public EbcdicDataPackager(int length, Padding location) {
        super(length);
        ignoreNibble = location;

    }

    public EbcdicDataPackager(Compliance<String> compliance) {
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

            set(EBCDIC.decode(ByteBuffer.wrap(data)).toString());
            setRawData(l.toString().toUpperCase());

            return offset + length;

        }
        else
            throw new Exception(String.format("Need to read %d bytes from offset %d, but total bytes available is %d.",
                    length, offset, bytes.length));

    }

    @Override
    public byte[] pack(String value) {
        if (value == null)
            return null;
        set(value);
        setRawData(EBCDIC.encode(value).array());
        return EBCDIC.encode(value).array();
    }

    @Override
    public byte[] pack() {

        return EBCDIC.encode(get()).array();

    }

}
