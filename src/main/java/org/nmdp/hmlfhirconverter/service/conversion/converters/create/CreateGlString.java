package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import io.swagger.model.GlString;
import org.nmdp.hmlfhirconverter.util.PrimitiveParser;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateGlString {

    public static GlString createGlString(String glStringStr) {
        GlString glstring = new GlString();

        if (glStringStr == null || glStringStr.isEmpty()) {
            return glstring;
        }

        if (PrimitiveParser.CheckValidUrl(glStringStr)) {
            glstring.setUri(glStringStr);
            return glstring;
        }

        glstring.setValue(glStringStr);
        return glstring;
    }

}
