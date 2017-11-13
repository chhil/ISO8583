package org.iso8583.util;

public interface Compliance<T> {

    public T makeCompliant(T s);

}
