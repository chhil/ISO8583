package org.iso8583.packager;

import java.util.ArrayList;
import java.util.List;

import org.iso8583.util.Utils;

public class BitmapMessage<T> {

    List<Field<T>> fields = new ArrayList<Field<T>>();

    public BitmapMessage(List<Field<T>> fields) {
        this.fields = fields;
    }

    public void unpack(byte[] bytesIn) throws Exception {

        unpack(0, bytesIn, fields);
    }



    public byte[] pack() {

        byte[] packed = new byte[0];

        for (Field field : fields) {
            if (field.getDataPackager() != null) {
                if (field.getValue() != null) {
                    byte[] packedBytes = field.getDataPackager().pack(field.getValue());
                    if (field.getLengthPackager() != null) {
                        packed = Utils.concat(packedBytes,
                                field.getLengthPackager().pack(String.valueOf((packedBytes.length))));
                    }
                    else if (field.getDataPackager() != null && !(field.getDataPackager() instanceof BitmapPackager)) {

                        packed = Utils.concat(packed, field.getDataPackager().pack(field.getValue()));

                    }
                }
                if (field.getDataPackager() instanceof BitmapPackager) {
                    BitmapPackager pkgr = (BitmapPackager) field.getDataPackager();
                    packed = Utils.concat(packed, pkgr.pack(field.fields));

                }

            }

        }

        return packed;

    }

    public void set(String dataElement, T value) {

        int de = Integer.parseInt(dataElement);
        Field<T> f = fields.get(1).fields.get(de);
        f.setValue(value);

    }

    public void setMTI(T value) {

        fields.get(0).setValue(value);

    }

    public T getMTI() {

        return fields.get(0).getValue();

    }

    public T get(String dataElement) {

        int de = Integer.parseInt(dataElement);
        if (de == 0) {
            return fields.get(0).getValue();
        }
        Field<T> f = fields.get(1).fields.get(de);
        return f.getValue();

    }

    private int unpack(int offset, byte[] bytesIn, List<Field<T>> subfields) throws Exception {
        for (Field field : subfields) {

            if (field.getLengthPackager() != null) {
                offset = field.getLengthPackager().unpack(offset, bytesIn);
                if (field.getDataPackager() != null) {
                    field.getDataPackager().setLength(field.getLengthPackager().getDataLength());
                    offset = field.getDataPackager().unpack(offset, bytesIn);
                    field.setValue(field.getDataPackager().get());

                }
            }
            else {
                if (field.getDataPackager() != null && !(field.getDataPackager() instanceof BitmapPackager)) {
                    offset = field.getDataPackager().unpack(offset, bytesIn);
                    field.setValue(field.getDataPackager().get());
                }
            }
            if (field.getDataPackager() instanceof BitmapPackager) {
                BitmapPackager pkgr = (BitmapPackager) field.getDataPackager();
                pkgr.unpack(offset, bytesIn, field.fields);

            }
        }

        return offset;

    }

    public String describe() {

        StringBuilder builder = new StringBuilder();
        for (Field field : fields) {

            if (field.getLengthPackager() != null) {
                builder.append(field.getLengthPackager().describe());
            }

            if (field.getDataPackager() != null) {
                if (field.getDataPackager() instanceof BitmapPackager) {

                    builder.append(field.getDataPackager().describe()).append(String.format("%n"));
                }
                else {
                    builder.append(field.describe()).append(String.format("%n"));
                }
            }

        }
        return builder.toString();

    }

}
