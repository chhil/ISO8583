package org.iso8583.packager;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.iso8583.util.Utils;

public class GenericTLVDataPackager extends DataPackager<Map<String, String>> {

    private DataPackager<String>  tagPackager;
    private LengthPackager        dataLengthPackager;
    private DataPackager<String>  valuePackager;
    protected Map<String, String> tagValueMap = new HashMap<String, String>();

    public GenericTLVDataPackager(int length) {
        super(length);
    }

    public GenericTLVDataPackager() {
    }

    public GenericTLVDataPackager(DataPackager<String> tagPackager, LengthPackager dataLengthPackager,
            DataPackager<String> valuePackager) {

        this.setTagPackager(tagPackager);
        this.setDataLengthPackager(dataLengthPackager);
        this.setValuePackager(valuePackager);

    }

    @Override
    public String describe() {
        Map<String, String> map = get();
        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> entry : map.entrySet()) {
            builder.append(
                    String.format("{[%s][%s][%s]}", entry.getKey(), entry.getValue().length(), entry.getValue()));

        }

        return "[" + builder.toString() + "]";
    }

    @Override
    public int unpack(int offset, byte[] bytes) throws Exception {

        int totalFieldLength = offset + length;
        if ((offset + length) <= bytes.length) {
            while (offset < totalFieldLength) {

                offset = getTagPackager().unpack(offset, bytes);
                offset = getDataLengthPackager().unpack(offset, bytes);
                getValuePackager().setLength(getDataLengthPackager().getDataLength());
                offset = getValuePackager().unpack(offset, bytes);
                System.out.println(String.format("Tag %s Length %d value %s", getTagPackager().get(),
                        getDataLengthPackager().getDataLength(), getValuePackager().get()));
                getTagValueMap().put(getTagPackager().get(), getValuePackager().get());
            }

        }
        else
            throw new Exception(String.format("Need to read %d bytes from offset %d, but total bytes available is %d.",
                    length, offset, length - bytes.length));
        set(getTagValueMap());
        return offset + length;
    }

    @Override
    public byte[] pack(Map<String, String> value) {
        set(value);
        // setRawData(value.getBytes(DataPackager.ASCII));

        return pack();

    }

    @Override
    public byte[] pack() {

        Map<String, String> map = get();
        byte[] packed = new byte[0];
        for (Entry<String, String> entry : map.entrySet()) {
            packed = Utils.concat(packed, getTagPackager().pack(entry.getKey()));
            packed = Utils.concat(packed, getDataLengthPackager().pack(String.valueOf(entry.getValue().length())));
            packed = Utils.concat(packed, getValuePackager().pack(entry.getValue()));

        }
        return packed;
    }

    public DataPackager<String> getTagPackager() {
        return tagPackager;
    }

    public void setTagPackager(DataPackager<String> tagPackager) {
        this.tagPackager = tagPackager;
    }

    public LengthPackager getDataLengthPackager() {
        return dataLengthPackager;
    }

    public void setDataLengthPackager(LengthPackager dataLegtPackager) {
        this.dataLengthPackager = dataLegtPackager;
    }

    public DataPackager<String> getValuePackager() {
        return valuePackager;
    }

    public void setValuePackager(DataPackager<String> valuePackager) {
        this.valuePackager = valuePackager;
    }

    public Map<String, String> getTagValueMap() {
        return tagValueMap;
    }

    public void setTagValueMap(Map<String, String> tagValueMap) {
        this.tagValueMap = tagValueMap;
    }

}
