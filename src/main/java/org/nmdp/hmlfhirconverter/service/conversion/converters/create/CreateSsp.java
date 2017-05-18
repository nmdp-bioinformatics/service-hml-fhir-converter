package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.Ssp;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateSsp {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static Ssp createSsp(JsonObject jsonObject) {
        Ssp ssp = new Ssp();

        if (jsonObject == null) {
            return ssp;
        }

        ssp.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        ssp.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        ssp.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        ssp.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        ssp.setLocus(jsonObject.has(HmlFieldConstants.SSP_LOCUS) ? jsonObject.get(HmlFieldConstants.SSP_LOCUS).getAsString() : null);
        ssp.setScores(jsonObject.has(HmlFieldConstants.SSP_SCORES) ? jsonObject.get(HmlFieldConstants.SSP_SCORES).getAsString() : null);
        ssp.setTestId(jsonObject.has(HmlFieldConstants.SSP_TESTID) ? jsonObject.get(HmlFieldConstants.SSP_TESTID).getAsString() : null);
        ssp.setTestIdSource(jsonObject.has(HmlFieldConstants.SSP_TESTIDSOURCE) ? jsonObject.get(HmlFieldConstants.SSP_TESTIDSOURCE).getAsString() : null);
        ssp.setProperties(CreateProperties.createProperties(jsonObject.has(HmlFieldConstants.SSP_PROPERTY) ? jsonObject.get(HmlFieldConstants.SSP_PROPERTY).getAsJsonObject() : null));

        return ssp;
    }

}
