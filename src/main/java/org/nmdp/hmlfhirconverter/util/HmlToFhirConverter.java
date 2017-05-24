package org.nmdp.hmlfhirconverter.util;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 3/14/17.
 * <p>
 * service-hmlFhirConverter
 * Copyright (c) 2012-2017 National Marrow Donor Program (NMDP)
 * <p>
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 3 of the License, or (at
 * your option) any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library;  if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.
 * <p>
 * > http://www.fsf.org/licensing/licenses/lgpl.html
 * > http://www.opensource.org/licenses/lgpl-license.php
 */

import com.google.gson.*;

import org.apache.log4j.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import io.swagger.model.Hml;

import org.nmdp.hmlfhirconverter.service.conversion.converters.HmlXmlDeserializer;

import org.springframework.core.convert.ConversionException;

import org.springframework.core.convert.ConversionFailedException;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.StringReader;
import java.net.URL;
import java.util.Iterator;

public class HmlToFhirConverter {

    private static Logger LOG = Logger.getLogger(HmlToFhirConverter.class);

    public static Hml convertStringToXml(String hmlXml) throws ConversionException {
        try {
            JSONObject json = convertXmlStringToJson(hmlXml);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(io.swagger.model.Hml.class, new HmlXmlDeserializer());
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(json.toString());
            JsonObject jsonObject = (JsonObject) obj;
            Gson gson = gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();

            return gson.fromJson(jsonObject, Hml.class);
        } catch (Exception ex) {
            LOG.error("Error converting HML to FHIR.", ex);
            throw (ConversionException)ex;
        }
    }

    public static Hml convertXmlToObject(String xml) throws ConversionException {
        try {
            if (isValidXml(xml)) {
                JSONObject jsonObj = convertXmlStringToJson(xml);
                JSONObject mutatedJson = mutatePropertyNames(jsonObj, "ns2:");
                GsonBuilder gsonBuilder = new GsonBuilder();
                JsonParser parser = new JsonParser();
                gsonBuilder.registerTypeAdapter(io.swagger.model.Hml.class, new HmlXmlDeserializer());
                Gson gson = gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
                Object obj = parser.parse(mutatedJson.toString());
                JsonObject jsonObject = (JsonObject) obj;

                return gson.fromJson(jsonObject, Hml.class);
            }

            throw new Exception("Invalid XML.");
        } catch (Exception ex) {
            LOG.error("Error converting xml to Object.", ex);
            throw (ConversionException)ex;
        }
    }

    private static JSONObject convertXmlStringToJson(String xml) throws ConversionException {
        try {
            return XML.toJSONObject(xml);
        } catch (Exception ex) {
            LOG.error("Error parsing HML to Json.", ex);
            throw (ConversionException) ex;
        }
    }

    private static Boolean isValidXml(String xml) {
        Boolean valid = false;

        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();

            xmlReader.parse(new InputSource(new StringReader(xml)));
            valid = true;
        } catch (Exception ex) {
            LOG.error("General exception validating XML.", ex);
        } finally {
            return valid;
        }
    }

    private static JSONObject mutatePropertyNames(JSONObject json, String prefix) {
        JSONObject mutatedJson = new JSONObject();
        Iterator<String> jsonIterator = json.keys();

        while (jsonIterator.hasNext()) {
            String key = jsonIterator.next();
            Object property = json.get(key);

            if (property instanceof JSONObject) {
                JSONObject mutatedProperty = mutatePropertyNames((JSONObject) property, prefix);
                mutatedJson.put(key.replace(prefix, ""), mutatedProperty);
                continue;
            } else if (property instanceof JSONArray) {
                JSONArray arrayProperty = (JSONArray)property;
                JSONArray mutatedJsonArray = new JSONArray();

                for (int i = 0; i < arrayProperty.length(); i++) {
                    JSONObject obj = arrayProperty.getJSONObject(i);
                    JSONObject mutatedObj = mutatePropertyNames(obj, prefix);

                    mutatedJsonArray.put(mutatedObj);
                }

                mutatedJson.put(key.replace(prefix, ""), mutatedJsonArray);
                continue;
            }

            mutatedJson.put(key.replace(prefix, ""), property);
        }

        return mutatedJson;
    }
}
