package org.iso8583.packager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Field {
    
    private LengthPackager lengthPackager;
    private DataPackager   dataPackager;
    private String         name;
    private String         value;
    private Map<String, String> map = new HashMap();

    List<Field>            fields;

    public Field(String name, LengthPackager lengthPackager, DataPackager dataPackager) {
        this.setName(name);
        map.put(name, null);
        this.setDataPackager(dataPackager);
        this.setLengthPackager(lengthPackager);
    }

    public Field(String name, DataPackager dataPackager) {

        this.setDataPackager(dataPackager);
        this.setName(name);
    }

    public Field(String name, DataPackager dataPackager, List<Field> fields) {

        this.setDataPackager(dataPackager);
        this.setName(name);
        this.fields = fields;
    }


    public LengthPackager getLengthPackager() {
        return lengthPackager;
    }

    public void setLengthPackager(LengthPackager lengthPackager) {
        this.lengthPackager = lengthPackager;
    }

    public DataPackager getDataPackager() {
        return dataPackager;
    }

    public void setDataPackager(DataPackager dataPackager) {
        this.dataPackager = dataPackager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        map.put(name, null);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        map.put(name, value);
    }

    public String describe() {

        return String.format("[%10s] : [%s]", name, value);

    }
}
