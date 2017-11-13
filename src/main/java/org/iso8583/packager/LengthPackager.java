package org.iso8583.packager;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class LengthPackager implements IMessage {

    public static final Charset ASCII  = StandardCharsets.ISO_8859_1;
    public static final Charset EBCDIC = Charset.forName("IBM1047");
    protected int lengthOfLength;
    protected int decodedLength;

    public LengthPackager(int lengthOfLength) {
        this.lengthOfLength = lengthOfLength;

    }

    public abstract int unpack(int offset, byte[] bytesIn) throws Exception;

    public abstract byte[] pack(String length);

    public abstract byte[] pack(byte[] lengthBytes) throws Exception;

    public abstract String describe();

    public int getDataLength() {
        return decodedLength;
    }

    public void setDataLength(int dataLength) {
        this.decodedLength = dataLength;
    }

}
