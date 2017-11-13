package org.iso8583.util;

public class ComplianceNone implements Compliance<String> {

    @Override
    public String makeCompliant(String s) {

        return s;
    }

}
