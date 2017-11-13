package org.iso8583.packager;

public class NOPDataPackager extends DataPackager {


    public NOPDataPackager(int length) {
        super(length);
    }

    public NOPDataPackager() {
        super();
    }

    public NOPDataPackager(int length, Padding location) {
        super(length);

    }

    @Override
    public String describe() {

        return "[" + get() + "]";
    }

    @Override
    public int unpack(int offset, byte[] bytes) throws Exception {
        set(null);
        return offset;
    }

    @Override
    public byte[] pack(String value) {
        return new byte[0];

    }

    @Override
    public byte[] pack() {
        return new byte[0];
    }

}
