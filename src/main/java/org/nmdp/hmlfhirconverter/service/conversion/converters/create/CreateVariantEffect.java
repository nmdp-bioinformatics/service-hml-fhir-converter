package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.VariantEffect;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateVariantEffect {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static VariantEffect createVariantEffect(JsonObject jsonObject) {
        VariantEffect variantEffect = new VariantEffect();

        if (jsonObject == null) {
            return variantEffect;
        }

        variantEffect.setId(jsonObject.has(HmlFieldConstants.VARIANTEFF_ID) ? jsonObject.get(HmlFieldConstants.VARIANTEFF_ID).getAsString() : null);
        variantEffect.setActive(jsonObject.has(HmlFieldConstants.VARIANTEFF_ACTIVE) ? jsonObject.get(HmlFieldConstants.VARIANTEFF_ACTIVE).getAsBoolean() : null);
        variantEffect.setDateCreated(jsonObject.has(HmlFieldConstants.VARIANTEFF_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.VARIANTEFF_DATECREATED).getAsString()).toDate() : null);
        variantEffect.setDateUpdated(jsonObject.has(HmlFieldConstants.VARIANTEFF_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.VARIANTEFF_DATEUPDATED).getAsString()).toDate() : null);
        variantEffect.setAnyAttribute(jsonObject.has(HmlFieldConstants.VARIANTEFF_ANYATTRIBUTE) ? jsonObject.get(HmlFieldConstants.VARIANTEFF_ANYATTRIBUTE).getAsJsonObject() : null);
        variantEffect.setHgvs(jsonObject.has(HmlFieldConstants.VARIANTEFF_HGVS) ? jsonObject.get(HmlFieldConstants.VARIANTEFF_HGVS).getAsString() : null);
        variantEffect.setTerm(jsonObject.has(HmlFieldConstants.VARIANTEFF_TERM) ? jsonObject.get(HmlFieldConstants.VARIANTEFF_TERM).getAsString() : null);
        variantEffect.setUri(jsonObject.has(HmlFieldConstants.VARIANTEFF_URI) ? jsonObject.get(HmlFieldConstants.VARIANTEFF_URI).getAsString() : null);

        return variantEffect;
    }

}
