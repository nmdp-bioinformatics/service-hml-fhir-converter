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

import org.json.JSONObject;
import org.json.XML;

import io.swagger.model.Hml;

import org.nmdp.hmlfhirconverter.service.conversion.converters.HmlXmlDeserializer;

import org.springframework.core.convert.ConversionException;

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

    private static JSONObject convertXmlStringToJson(String xml) throws ConversionException {
        try {
            return XML.toJSONObject(xml);
        } catch (Exception ex) {
            LOG.error("Error parsing HML to Json.", ex);
            throw (ConversionException)ex;
        }
    }
}
