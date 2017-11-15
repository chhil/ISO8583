package org.iso8583.packager;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iso8583.util.Utils;

public class BitmapPackager extends DataPackager<Map<String, String>> {
    protected BitSet    bitmap;

    protected Map    m = new HashMap<>();
    protected BitSet getBitmap() {
        return bitmap;
    }

    protected void setBitmap(BitSet bitmap) {
        this.bitmap = bitmap;
    }

    private boolean     msbBitmapExtension = true;
    private List<Field> fields;

    public BitmapPackager(int length) {
        super(length);

        // TODO Auto-generated constructor stub
    }

    public BitmapPackager(int length, boolean msbBitmapExtension) {
        super(length);
        this.setMsbBitmapExtension(msbBitmapExtension);

        // TODO Auto-generated constructor stub
    }

    @Override
    public String describe() {

        StringBuilder str = new StringBuilder();
        str.append(String.format("%n")).append("[Bitmap Hex] : [").append(Utils.bytesToHex(Utils.toByteArray(bitmap)))
                .append("]")
                .append(String.format("%n"));
        str.append("[Bitmap Bit] : [");

        for (int i = 0; i < bitmap.length(); i++) {
            if (bitmap.get(i)) {
                str.append(i).append(" ");
            }
        }
        str.append("]").append(String.format("%n"));

        if (fields != null) {
            for (Field f : fields) {
                if (f.getValue() != null)
                    str.append(String.format("[%10s] : %s", f.getName(), f.getDataPackager().describe()))
                            .append(String.format("%n"));
            }
        }

        return str.toString();
    }

    @Override
    public int unpack(int offset, byte[] bytesIn, List<Field> fieldFormatter, boolean msbExtension) throws Exception {
        this.fields = fieldFormatter;
        offset = unpack(offset, bytesIn, msbExtension);

        for (int i = 0; i <= bitmap.size(); i++) {
            if (bitmap.get(i)) {
                Field f = fieldFormatter.get(i);

                if (f.getLengthPackager() != null) {
                    offset = f.getLengthPackager().unpack(offset, bytesIn);
                    if (f.getDataPackager() != null) {
                        if (!(f.getDataPackager() instanceof BitmapPackager)) {
                            f.getDataPackager().setLength(f.getLengthPackager().getDataLength());
                            offset = f.getDataPackager().unpack(offset, bytesIn);
                            f.setValue(f.getDataPackager().get());
                            m.put(String.valueOf(i), f.getDataPackager().get());
                        }
                        else {
                            BitmapPackager pkgr = (BitmapPackager) f.getDataPackager();
                            // pkgr.setLength(f.getLengthPackager().getDataLength());
                            // offset = unpack(offset, bytesIn,
                            // pkgr.isMsbBitmapExtension());
                            // f.getDataPackager().setLength(f.getLengthPackager().getDataLength());
                            offset = f.getDataPackager().unpack(offset, bytesIn, f.fields, pkgr.isMsbBitmapExtension());
                            f.setValue(f.getDataPackager().get());
                            // m.put(String.valueOf(i), (String)
                            // f.getDataPackager().get());

                            f.getDataPackager().get();
                        }

                    }
                }
                else {
                    if (f.getDataPackager() != null) {
                        offset = f.getDataPackager().unpack(offset, bytesIn);
                        f.setValue(f.getDataPackager().get());
                        m.put(String.valueOf(i), f.getDataPackager().get());
                    }
                }
                if (f.getLengthPackager() == null && f.getDataPackager() instanceof BitmapPackager) {
                    // BitmapPackager pkgr = (BitmapPackager)
                    // f.getDataPackager();
                    offset = unpack(offset, bytesIn, f.fields, msbExtension);

                }

            }

        }

        set(m);
        return offset;

    }

    @Override
    public int unpack(int offset, byte[] bytes) throws Exception {

        if (isMsbBitmapExtension()) {
            length = 8;
            bitmap = new BitSet(64);
            if ((bytes[offset] >> 7 & 1) == 1) {
                bitmap = new BitSet(128);
                length = 16;
                if ((offset + length) <= bytes.length) {
                    if ((bytes[offset + 8] >> 7 & 1) == 1) {
                        length = 24;
                        bitmap = new BitSet(192);
                    }
                }
            }
        }
        bitmap = new BitSet(length * 8);

        int count = 1;
        StringBuilder l = new StringBuilder();
        for (int i = 0; i < length; i++) {
            l.append(String.format("%02x", bytes[i + offset] & 0xff));
            for (int j = 7; j >= 0; j--) {

                // System.out.print(String.format("[ %3d ]: %d ", count + 1,
                // ((bytes[offset + i] >> j) & 1)));
                if (((bytes[offset + i] >> j) & 1) == 1) {

                    bitmap.set(count);
                }
                count++;

            }
            // System.out.print("\r\n");
            m.put("0", l.toString().toUpperCase());

            // set(l.toString().toUpperCase());
        }

        return offset + length;
    }

    public int unpack(int offset, byte[] bytes, boolean isMsbBitmapExtension) throws Exception {

        if (isMsbBitmapExtension) {
            length = 8;
            bitmap = new BitSet(64);
            if ((bytes[offset] >> 7 & 1) == 1) {
                bitmap = new BitSet(128);
                length = 16;
                if ((offset + length) <= bytes.length) {
                    if ((bytes[offset + 8] >> 7 & 1) == 1) {
                        length = 24;
                        bitmap = new BitSet(192);
                    }
                }
            }
        }
        bitmap = new BitSet(length * 8);

        int count = 1;
        StringBuilder l = new StringBuilder();
        for (int i = 0; i < length; i++) {
            l.append(String.format("%02x", bytes[i + offset] & 0xff));
            for (int j = 7; j >= 0; j--) {

                // System.out.print(String.format("[ %3d ]: %d ", count + 1,
                // ((bytes[offset + i] >> j) & 1)));
                if (((bytes[offset + i] >> j) & 1) == 1) {

                    bitmap.set(count);
                }
                count++;

            }
            // System.out.print("\r\n");

            m.put("0", l.toString().toUpperCase());
        }

        return offset + length;
    }


    @Override
    public byte[] pack() {

        return null;
    }


    public byte[] pack(List<Field> fields, int bitmapSize) {
        this.fields = fields;
        bitmap = new BitSet(bitmapSize * 8);

        byte[] packed = new byte[0];
        int dataElement = 0;
        for (Field f : fields) {

            if (f.getValue() != null) {

                byte[] packedBytes = f.getDataPackager().pack(f.getValue());
                if (f.getLengthPackager() != null) {
                    if (f.getDataPackager() instanceof BitmapPackager) {
                        BitmapPackager pkgr = (BitmapPackager) f.getDataPackager();
                        bitmap.set(dataElement);
                        byte[] bitmapPacked = pkgr.pack(f.fields, pkgr.length);
                        packed = Utils.concat(packed, bitmapPacked);
                        packed = f.getLengthPackager().pack(String.valueOf(bitmapPacked.length));

                    }
                    else {
                        bitmap.set(dataElement);
                        packed = Utils.concat(packed, f.getLengthPackager().pack(String.valueOf((packedBytes.length))),
                                packedBytes);
                    }
                }
                else if (f.getDataPackager() != null && !(f.getDataPackager() instanceof BitmapPackager)) {
                    bitmap.set(dataElement);
                    packed = Utils.concat(packed, f.getDataPackager().pack(f.getValue()));

                }
            }
            if (f.getDataPackager() instanceof BitmapPackager) {
                BitmapPackager pkgr = (BitmapPackager) f.getDataPackager();
                packed = Utils.concat(packed, pkgr.pack(f.fields, pkgr.length));

            }

            dataElement++;

        }

        System.out.println(bitmap.length());
        byte[] p = Utils.toByteArray(bitmap);
        byte[] q = new byte[bitmapSize];
        for (int i = 0; i < bitmapSize; i++)
            q[i] = p[i];

        packed = Utils.concat(q, packed);
        return packed;

    }

    public boolean isMsbBitmapExtension() {
        return msbBitmapExtension;
    }

    public void setMsbBitmapExtension(boolean msbBitmapExtension) {
        this.msbBitmapExtension = msbBitmapExtension;
    }



    @Override
    public byte[] pack(Map<String, String> value) {
        // TODO Auto-generated method stub
        return null;
    }

}
