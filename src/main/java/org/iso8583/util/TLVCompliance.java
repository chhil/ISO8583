package org.iso8583.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TLVCompliance implements Compliance<Map<String, String>> {

    private Map<String, Compliance<String>> complianceMap;

    public TLVCompliance(Map<String, Compliance<String>> complianceMap) {
        this.complianceMap = complianceMap;
    }
    @Override
    public Map<String, String> makeCompliant(Map<String, String> nameValuePairs) {

        Map<String, String> temp = new TreeMap<String, String>();
        if (complianceMap == null) {
            return nameValuePairs;
        }
        for (Entry<String, String> entry : nameValuePairs.entrySet()) {

            Compliance<String> compliance = complianceMap.get(entry.getKey());

            if (compliance != null) {
                compliance.makeCompliant(entry.getValue());
                temp.put(entry.getKey(), compliance.makeCompliant(entry.getValue()));
            }
            else {
                temp.put(entry.getKey(), entry.getValue());
            }
        }
        return temp;
    }


}
