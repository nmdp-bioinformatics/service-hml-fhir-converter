package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.SbtNgs;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateSbtNgs {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static SbtNgs createSbtNgs(JsonObject jsonObject) {
        SbtNgs sbtNgs = new SbtNgs();

        if (jsonObject == null) {
            return sbtNgs;
        }

        sbtNgs.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sbtNgs.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        sbtNgs.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        sbtNgs.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        sbtNgs.setLocus(jsonObject.has(HmlFieldConstants.SBTNGS_LOCUS) ? jsonObject.get(HmlFieldConstants.SBTNGS_LOCUS).getAsString() : null);
        sbtNgs.setProperties(CreateProperties.createProperties(jsonObject.has(HmlFieldConstants.SBTNGS_PROPERTY) ? jsonObject.get(HmlFieldConstants.SBTNGS_PROPERTY).getAsJsonObject() : null));
        sbtNgs.setRawReads(CreateRawReads.createRawReads(jsonObject.has(HmlFieldConstants.SBTNGS_RAWREADS) ? jsonObject.get(HmlFieldConstants.SBTNGS_RAWREADS).getAsJsonObject() : null));
        sbtNgs.setTestId(jsonObject.has(HmlFieldConstants.SBTNGS_TESTID) ? jsonObject.get(HmlFieldConstants.SBTNGS_TESTID).getAsString() : null);
        sbtNgs.setTestIdSource(jsonObject.has(HmlFieldConstants.SBTNGS_TESTIDSOURCE) ? jsonObject.get(HmlFieldConstants.SBTNGS_TESTIDSOURCE).getAsString() : null);

        return sbtNgs;
    }

}
