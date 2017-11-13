package org.iso8583.packager;

public class AsciiDataPackager extends DataPackager {

    public AsciiDataPackager(int length) {
        super(length);
    }

    public AsciiDataPackager() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String describe() {

        return "[" + rawData + "]";
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
