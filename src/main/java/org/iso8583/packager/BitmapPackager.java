package org.iso8583.packager;

import java.util.BitSet;
import java.util.List;

import org.iso8583.util.Utils;

public class BitmapPackager extends DataPackager<String> {
    BitSet bitmap;

    protected BitSet getBitmap() {
        return bitmap;
    }

    protected void setBitmap(BitSet bitmap) {
        this.bitmap = bitmap;
    }

    boolean             msbBitmapExtension = true;
    private List<Field> fields;

    public BitmapPackager(int length) {
        super(length);

        // TODO Auto-generated constructor stub
    }

    public BitmapPackager(int length, boolean msbBitmapExtension) {
        super(length);
        this.msbBitmapExtension = msbBitmapExtension;

        // TODO Auto-generated constructor stub
    }

    @Override
    public String describe() {

        StringBuilder str = new StringBuilder();
        str.append("[Bitmap Hex] : [").append(Utils.bytesToHex(Utils.toByteArray(bitmap))).append("]")
                .append(String.format("%n"));
        str.append("[Bitmap Bit] : [");

        for (int i = 0; i < bitmap.length(); i++) {
            if (bitmap.get(i)) {
                str.append(i).append(" ");
            }

        }
        str.append("]").append(String.format("%n"));

        for (Field f : fields) {
            if (f.getValue() != null)
                str.append(String.format("[%10s] : %s", f.getName(), f.getDataPackager().describe()))
                        .append(String.format("%n"));
        }

        return str.toString();
    }

    @Override
    public int unpack(int offset, byte[] bytesIn, List<Field> fieldFormatter) throws Exception {
        this.fields = fieldFormatter;
        offset = unpack(offset, bytesIn);

        for (int i = 0; i <= bitmap.size(); i++) {
            if (bitmap.get(i)) {
                Field f = fieldFormatter.get(i);

                if (f.getLengthPackager() != null) {
                    offset = f.getLengthPackager().unpack(offset, bytesIn);
                    if (f.getDataPackager() != null) {
                        f.getDataPackager().setLength(f.getLengthPackager().getDataLength());
                        offset = f.getDataPackager().unpack(offset, bytesIn);
                        f.setValue(f.getDataPackager().get());

                    }
                }
                else {
                    if (f.getDataPackager() != null) {
                        offset = f.getDataPackager().unpack(offset, bytesIn);
                        f.setValue(f.getDataPackager().get());
                    }
                }
                if (f.getDataPackager() instanceof BitmapPackager) {
                    // BitmapPackager pkgr = (BitmapPackager)
                    // f.getDataPackager();
                    unpack(offset, bytesIn, f.fields);

                }

            }

        }

        return offset;

    }

    @Override
    public int unpack(int offset, byte[] bytes) throws Exception {

        if (msbBitmapExtension) {
            length = 8;
            bitmap = new BitSet(64);
            if ((bytes[offset] >> 7 & 1) == 1) {
                bitmap = new BitSet(128);
                length = 16;
                if ((offset + 64) <= bytes.length) {
                    if ((bytes[offset + 64] >> 7 & 1) == 1) {
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

            set(l.toString().toUpperCase());
        }

        return offset + length;
    }

    @Override
    public byte[] pack(String value) {

        return null;
    }

    @Override
    public byte[] pack() {

        return null;
    }

    /**
     * All the field that were set in the object are now packed i.e. converted
     * to bytes using the type of packager (length and data ) for each field.
     * 
     * @param fields
     * @return
     */
    public byte[] pack(List<Field> fields) {
        this.fields = fields;
        bitmap = new BitSet();

        byte[] packed = new byte[0];
        int dataElement = 0;
        for (Field f : fields) {

            if (f.getValue() != null) {

                byte[] packedBytes = f.getDataPackager().pack(f.getValue());
                if (f.getLengthPackager() != null) {
                    bitmap.set(dataElement);
                    packed = Utils.concat(packed, f.getLengthPackager().pack(String.valueOf((packedBytes.length))),
                            packedBytes);
                }
                else if (f.getDataPackager() != null && !(f.getDataPackager() instanceof BitmapPackager)) {
                    bitmap.set(dataElement);
                    packed = Utils.concat(packed, f.getDataPackager().pack(f.getValue()));

                }
            }
            if (f.getDataPackager() instanceof BitmapPackager) {
                BitmapPackager pkgr = (BitmapPackager) f.getDataPackager();
                packed = Utils.concat(packed, pkgr.pack(f.fields));

            }

            dataElement++;

        }

        packed = Utils.concat(Utils.toByteArray(bitmap), packed);
        return packed;

    }

}
