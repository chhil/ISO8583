package org.iso8583.packager;

import org.iso8583.util.Compliance;

public class AsciiDataPackager extends DataPackager<String> {

    public AsciiDataPackager(int length) {
        super(length);
    }

    public AsciiDataPackager(int length, Compliance<String> compliance) {
        super(length, compliance);
    }
    public AsciiDataPackager() {
    }

    public AsciiDataPackager(Compliance<String> compliance) {
        super(compliance);
    }

    @Override
    public String describe() {

        return "[" + getCompliance().makeCompliant(rawData) + "]";
    }

    @Override
    public int unpack(int offset, byte[] bytes) throws Exception {

        if ((offset + length) <= bytes.length) {
            byte[] data = new byte[length];
            int k = 0;
            for (int i = offset; i < offset + length; i++) {

                data[k++] = bytes[i];
            }
            setRawData(new String(data, DataPackager.ASCII));
            set(new String(data, DataPackager.ASCII));
        }
        else
            throw new Exception(String.format("Need to read %d bytes from offset %d, but total bytes available is %d.",
                    length, offset, length - bytes.length));
        return offset + length;
    }

    @Override
    public byte[] pack(String value) {
        set(value);
        setRawData(value.getBytes(DataPackager.ASCII));
        return value.getBytes(DataPackager.ASCII);

    }

    @Override
    public byte[] pack() {
        // TODO Auto-generated method stub
        return get().getBytes(DataPackager.ASCII);
    }

}
