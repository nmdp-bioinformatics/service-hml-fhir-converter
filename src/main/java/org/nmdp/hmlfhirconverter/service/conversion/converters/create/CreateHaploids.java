package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.swagger.model.Haploid;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateHaploids {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static Haploid createHaploid(JsonObject jsonObject) {
        Haploid haploid = new Haploid();

        if (jsonObject == null) {
            return haploid;
        }

        haploid.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        haploid.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        haploid.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        haploid.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        haploid.setLocus(jsonObject.has(HmlFieldConstants.HAPLOID_LOCUS) ? jsonObject.get(HmlFieldConstants.HAPLOID_LOCUS).getAsString() : null);
        haploid.setMethod(jsonObject.has(HmlFieldConstants.HAPLOID_METHOD) ? jsonObject.get(HmlFieldConstants.HAPLOID_METHOD).getAsString() : null);
        haploid.setType(jsonObject.has(HmlFieldConstants.HAPLOID_TYPE) ? jsonObject.get(HmlFieldConstants.HAPLOID_TYPE).getAsString() : null);

        return haploid;
    }

    public static List<Haploid> createHaploidsAr(JsonArray jsonArray) {
        List<Haploid> haploids = new ArrayList<>();

        if (jsonArray == null) {
            return haploids;
        }


        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jo = (JsonObject)jsonArray.get(i);
            haploids.add(createHaploid(jo));
        }
        // loop through each element of the array and pass to below as a JsonObject
        for (JsonElement element : jsonArray) {

            haploids.add((Haploid) createHaploidsOb((JsonObject)element));
        }
        return haploids;
    }

    public static List<Haploid> createHaploidsOb(JsonObject jsonObject) {
        List<Haploid> haploids = new ArrayList<>();

        if (jsonObject == null) {
            return haploids;
        }

        haploids.add(createHaploid(jsonObject));
        return haploids;

    }

}
