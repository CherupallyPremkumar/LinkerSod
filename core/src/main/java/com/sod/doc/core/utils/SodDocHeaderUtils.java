package com.sod.doc.core.utils;

import org.chenile.core.context.HeaderUtils;

import java.util.Map;

public class SodDocHeaderUtils extends HeaderUtils {

    public static final String LOCATION_ID_KEY = "x-request-location";

    public static void setLocationIdKey(Map<String, String> headers, String locaation) {
        headers.put(LOCATION_ID_KEY, locaation);
    }
    public static String getLocationId(Map<String, String> headers) {
        return headers.get(LOCATION_ID_KEY);
    }
}
