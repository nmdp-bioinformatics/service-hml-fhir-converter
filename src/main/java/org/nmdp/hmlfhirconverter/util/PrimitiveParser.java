package org.nmdp.hmlfhirconverter.util;

import org.apache.log4j.Logger;

/**
 * Created by ekan on 4/6/2017.
 */
public class PrimitiveParser {

    private static final Logger LOG = Logger.getLogger(PrimitiveParser.class);

    public static Boolean parseStringIntegerToBoolean(String value) {
        try {
            Integer v = Integer.parseInt(value);
            return v > 0;
        } catch (Exception ex) {
            LOG.error("Error parsing string to integer", ex);
            throw ex;
        }
    }

    public static Integer parseStringToInteger (String value2) {
        try {
            Integer v1 = Integer.parseInt(value2);
            return v1;
        } catch (Exception ex) {
            LOG.error("Error parsing string to integer", ex);
            throw ex;
        }
    }
}
