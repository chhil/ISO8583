package org.iso8583.util;

public class ComplianceWipe implements Compliance<String> {

    @Override
    public String makeCompliant(String s) {

        return Utils.leftPad("", s.length(), '*');
    }

}
