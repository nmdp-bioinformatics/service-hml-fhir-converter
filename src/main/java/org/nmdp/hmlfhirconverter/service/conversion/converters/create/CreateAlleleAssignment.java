package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.AlleleAssignment;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;
import org.nmdp.hmlfhirconverter.util.PrimitiveParser;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateAlleleAssignment {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static AlleleAssignment createAlleleAssignment(JsonObject jsonObject) {
        AlleleAssignment alleleAssignment = new AlleleAssignment();

        if (jsonObject == null) {
            return alleleAssignment;
        }

        alleleAssignment.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        alleleAssignment.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        alleleAssignment.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        alleleAssignment.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        alleleAssignment.setDate(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_DATE) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_DATE).getAsString()).toDate() : null);
        alleleAssignment.setAlleleDb(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_ALLELEDB) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_ALLELEDB).getAsString() : null);
        alleleAssignment.setAlleleVersion(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_ALLELEVERSION) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_ALLELEVERSION).getAsString() : null);
        alleleAssignment.setProperties(CreateProperties.createProperties(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_PROPERTY) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_PROPERTY).getAsJsonObject() : null));
        alleleAssignment.setGenotypes(CreateGenotypes.createGenotypes(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_GENOTYPE) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_GENOTYPE).getAsJsonObject() : null));
        alleleAssignment.setGlString(CreateGlString.createGlString(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_GLSTRING) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_GLSTRING).getAsString() : null));
        alleleAssignment.setHaploid(PrimitiveParser.isArray(jsonObject,HmlFieldConstants.ALLELEASSIGNMENT_HAPLOID) ? CreateHaploids.createHaploidsAr(jsonObject.has(HmlFieldConstants.HML_SAMPLE) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_HAPLOID).getAsJsonArray() : null):
                CreateHaploids.createHaploidsOb(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_HAPLOID) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_HAPLOID).getAsJsonObject() : null));



        return alleleAssignment;
    }

}
