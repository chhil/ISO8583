package org.iso8583.util;

public class ComplianceProtect implements Compliance<String> {

    @Override
    public String makeCompliant(String s) {
        // TODO Auto-generated method stub
        return Utils.protect(s);
    }

}
