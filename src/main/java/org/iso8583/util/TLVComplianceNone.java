package org.iso8583.util;

import java.util.Map;

public class TLVComplianceNone implements Compliance<Map<String, String>> {

    private Map<String, Compliance<String>> complianceMap;

    @Override
    public Map<String, String> makeCompliant(Map<String, String> nameValuePairs) {

            return nameValuePairs;
            }


}
