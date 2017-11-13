package org.iso8583.packager;

public interface IMessage {


    public int unpack(int offfset, byte[] bytes) throws Exception;

    public String describe();

}
