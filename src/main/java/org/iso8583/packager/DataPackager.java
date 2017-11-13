package org.iso8583.packager;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class DataPackager implements IMessage {

    public static final Charset ASCII  = StandardCharsets.ISO_8859_1;
    public static final Charset EBCDIC = Charset.forName("IBM1047");
    private String              interprettedData;
    private byte[]              rawDataBytes;
    protected String getRawData() {
        return rawData;
    }

    protected void setRawData(String rawData) {
        this.rawData = rawData;
    }

    protected void setRawData(byte[] rawData) {
        this.rawDataBytes = rawData;
    }

    public byte[] getRawDataBytes() {
        return rawDataBytes;
    }

    protected String rawData;
    protected int    length;

    public DataPackager() {
    }
    public DataPackager(int length) {
        this.length = length;
    }

    public abstract String describe();

    public abstract int unpack(int offset, byte[] bytes) throws Exception;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String get() {
        return interprettedData;
    }

    public void set(String interprettedData) {
        this.interprettedData = interprettedData;
    }

    public int unpack(int offset, byte[] bytes, List<Field> fieldFormatter) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    public abstract byte[] pack(String value);

    public abstract byte[] pack();

}
