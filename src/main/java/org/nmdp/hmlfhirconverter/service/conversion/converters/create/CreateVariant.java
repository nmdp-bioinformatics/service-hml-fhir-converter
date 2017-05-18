package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.swagger.model.Variant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateVariant {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static Variant createVariant(JsonObject jsonObject) {
        Variant variant = new Variant();

        if (jsonObject == null) {
            return variant;
        }

        variant.setId(jsonObject.has(HmlFieldConstants.VARIANT_ID) ? jsonObject.get(HmlFieldConstants.VARIANT_ID).getAsString() : null);
        variant.setActive(jsonObject.has(HmlFieldConstants.VARIANT_ACTIVE) ? jsonObject.get(HmlFieldConstants.VARIANT_ACTIVE).getAsBoolean() : null);
        variant.setDateCreated(jsonObject.has(HmlFieldConstants.VARIANT_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.VARIANT_DATECREATED).getAsString()).toDate() : null);
        variant.setDateUpdated(jsonObject.has(HmlFieldConstants.VARIANT_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.VARIANT_DATEUPDATED).getAsString()).toDate() : null);
        variant.setAlternateBases(jsonObject.has(HmlFieldConstants.VARIANT_ALTERNATEBASES) ? jsonObject.get(HmlFieldConstants.VARIANT_ALTERNATEBASES).getAsString() : null);
        variant.setAnyAttribute(jsonObject.has(HmlFieldConstants.VARIANT_ANYATTRIBUTE) ? jsonObject.get(HmlFieldConstants.VARIANT_ANYATTRIBUTE).getAsJsonObject() : null);
        variant.setEnd(jsonObject.has(HmlFieldConstants.VARIANT_END) ? jsonObject.get(HmlFieldConstants.VARIANT_END).getAsInt() : null);
        variant.setFilter(jsonObject.has(HmlFieldConstants.VARIANT_FILTER) ? jsonObject.get(HmlFieldConstants.VARIANT_FILTER).getAsString() : null);
        variant.setName(jsonObject.has(HmlFieldConstants.VARIANT_NAME) ? jsonObject.get(HmlFieldConstants.VARIANT_NAME).getAsString() : null);
        variant.setQualityScore(jsonObject.has(HmlFieldConstants.VARIANT_QUALITYSCORE) ? jsonObject.get(HmlFieldConstants.VARIANT_QUALITYSCORE).getAsString() : null);
        variant.setReferenceBases(jsonObject.has(HmlFieldConstants.VARIANT_REFERENCEBASES) ? jsonObject.get(HmlFieldConstants.VARIANT_REFERENCEBASES).getAsString() : null);
        variant.setStart(jsonObject.has(HmlFieldConstants.VARIANT_START) ? jsonObject.get(HmlFieldConstants.VARIANT_START).getAsInt() : null);
        variant.setUri(jsonObject.has(HmlFieldConstants.VARIANT_URI) ? jsonObject.get(HmlFieldConstants.VARIANT_URI).getAsString() : null);
        variant.setVariantId(jsonObject.has(HmlFieldConstants.VARIANT_VARIANTID) ? jsonObject.get(HmlFieldConstants.VARIANT_VARIANTID).getAsString() : null);
        variant.setVariantEffect(CreateVariantEffect.createVariantEffect(jsonObject.has(HmlFieldConstants.VARIANT_VARIANTEFFECT) ? jsonObject.get(HmlFieldConstants.VARIANT_VARIANTEFFECT).getAsJsonObject() : null));

        return variant;
    }

    public static List<Variant> createVariantsAr(JsonArray jsonArray) {
        List<Variant> variants = new ArrayList<>();

        if (jsonArray == null) {
            return variants;
        }


        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jo = (JsonObject)jsonArray.get(i);
            variants.add(createVariant(jo));
        }
        // loop through each element of the array and pass to below as a JsonObject
        for (JsonElement element : jsonArray) {

            variants.add( createVariant((JsonObject)element));
        }
        return variants;
    }

    public static List<Variant> createVariantsOb(JsonObject jsonObject) {
        List<Variant> variants = new ArrayList<>();

        if (jsonObject == null) {
            return variants;
        }

        variants.add(createVariant(jsonObject));
        return variants;

    }

}
