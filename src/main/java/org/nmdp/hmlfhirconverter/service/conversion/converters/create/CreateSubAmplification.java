package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.SubAmplification;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateSubAmplification {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static SubAmplification createSubAmplification(JsonObject jsonObject) {
        SubAmplification subAmplification = new SubAmplification();

        if (jsonObject == null) {
            return subAmplification;
        }

        subAmplification.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        subAmplification.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        subAmplification.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        subAmplification.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        subAmplification.setRegisteredName(jsonObject.has(HmlFieldConstants.SUBAMP_REGISTEREDNAME) ? jsonObject.get(HmlFieldConstants.SUBAMP_REGISTEREDNAME).getAsString() : null);
        subAmplification.setSequence(CreateSequence.createSequence(jsonObject.has(HmlFieldConstants.SUBAMP_SEQUENCE) ? jsonObject.get(HmlFieldConstants.SUBAMP_SEQUENCE).getAsString() : null));

        return subAmplification;
    }

}
