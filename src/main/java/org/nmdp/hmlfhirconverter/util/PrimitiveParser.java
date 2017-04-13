package org.nmdp.hmlfhirconverter.util;

import org.apache.log4j.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Created by Enoch Kan on 4/6/2017.
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

    public static Boolean CheckValidUrl (String urlString)  {
        String regex = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(urlString);

        if (m.find()) {
            return true;
        }

        return false;
    }
}
