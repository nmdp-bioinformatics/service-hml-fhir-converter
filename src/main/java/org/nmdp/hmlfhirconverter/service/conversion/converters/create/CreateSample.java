package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.swagger.model.Sample;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;
import org.nmdp.hmlfhirconverter.util.PrimitiveParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateSample {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static Sample createSample(JsonObject jsonObject)  {
        Sample sample = new Sample();

        if (jsonObject == null)  {
            return sample;
        }

        sample.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sample.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        sample.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        sample.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        sample.setCenterCode(jsonObject.has(HmlFieldConstants.SAMPLE_CENTERCODE) ? jsonObject.get(HmlFieldConstants.SAMPLE_CENTERCODE).getAsString() : null);
        sample.setSampleId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sample.setTyping(PrimitiveParser.isArray(jsonObject,HmlFieldConstants.SAMPLE_TYPING) ? CreateTyping.createTypingsAr(jsonObject.has(HmlFieldConstants.SAMPLE_TYPING) ? jsonObject.get(HmlFieldConstants.SAMPLE_TYPING).getAsJsonArray() : null):
                CreateTyping.createTypingsOb(jsonObject.has(HmlFieldConstants.SAMPLE_TYPING) ? jsonObject.get(HmlFieldConstants.SAMPLE_TYPING).getAsJsonObject() : null));
        sample.setProperties(CreateProperties.createProperties(jsonObject.has(HmlFieldConstants.SAMPLE_PROPERTY) ? jsonObject.get(HmlFieldConstants.SAMPLE_PROPERTY).getAsJsonObject() : null));
        sample.setCollectionMethods(CreateCollectionMethods.createCollectionMethods(jsonObject.has(HmlFieldConstants.SAMPLE_COLLECTIONMETHOD) ?  PrimitiveParser.isJsonObject(jsonObject, HmlFieldConstants.SAMPLE_COLLECTIONMETHOD) : null));

        return sample;
    }


    public static List<Sample> createSamplesAr(JsonArray jsonArray) {
        List<Sample> samples = new ArrayList<>();

        if (jsonArray == null) {
            return samples;
        }


        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jo = (JsonObject)jsonArray.get(i);
            samples.add(createSample(jo));
        }
        // loop through each element of the array and pass to below as a JsonObject
        for (JsonElement element : jsonArray) {

            samples.add( createSample((JsonObject)element));
        }
        return samples;
    }

    public static List<Sample> createSamplesOb(JsonObject jsonObject) {
        List<Sample> samples = new ArrayList<>();

        if (jsonObject == null) {
            return samples;
        }

        samples.add(createSample(jsonObject));
        return samples;

    }

}
