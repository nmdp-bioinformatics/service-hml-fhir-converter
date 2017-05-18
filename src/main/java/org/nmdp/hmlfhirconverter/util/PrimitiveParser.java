package org.nmdp.hmlfhirconverter.util;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import java.lang.reflect.Field;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Created by Enoch Kan on 4/6/2017.
 * function parseStringIntegerToBoolean takes in a string and parses it to a Boolean values
 * function parseStringToInteger takes in a string and parses it to an integer
 * function CheckValidUrl takes in a string and checks if it's a valid url using regex
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

    public static Boolean isArray(JsonObject jsonObject, String propertyName){
        Object jobj = jsonObject.get(propertyName);

        try {
            if (jobj instanceof JsonArray) {
                return true;
            }
            return false;

        } catch (Exception ex) {
            LOG.error("Error obtaining field from JsonObject.", ex);
            return false;
        }

    }

    public static JsonObject isJsonObject(JsonObject jsonObject, String propertyName)  {
        try {
            Object jobj1 = jsonObject.get(propertyName);

            if (jobj1 instanceof JsonObject) {
                return ((JsonObject) jobj1).getAsJsonObject();
            }
            return null;

        }   catch (Exception ex) {
            LOG.error("Error parsing JsonObject.", ex);
            return null;
        }
    }


}

