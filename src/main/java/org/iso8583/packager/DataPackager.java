package org.iso8583.packager;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.iso8583.util.Compliance;
import org.iso8583.util.ComplianceNone;

public abstract class DataPackager<T> implements IMessage {

    private Compliance<T>       compliance = (Compliance<T>) new ComplianceNone();
    public static final Charset ASCII  = StandardCharsets.ISO_8859_1;
    public static final Charset EBCDIC = Charset.forName("IBM1047");
    private T                   interprettedData;
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

    public DataPackager(int length, Compliance c) {
        this.length = length;
        this.compliance = c;
    }
    public DataPackager(int length) {
        this.length = length;
    }

    public DataPackager(Compliance<T> compliance) {
        this.compliance = compliance;
    }

    public DataPackager() {
    }

    public abstract String describe();

    public abstract int unpack(int offset, byte[] bytes) throws Exception;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public T get() {
        return interprettedData;
    }

    public void set(T interprettedData) {
        this.interprettedData = interprettedData;
    }

    public int unpack(int offset, byte[] bytes, List<Field> fieldFormatter) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    public abstract byte[] pack(T value);

    public abstract byte[] pack();

    public Compliance getCompliance() {
        return compliance;
    }

    public void setCompliance(Compliance compliance) {
        this.compliance = compliance;
    }

}
