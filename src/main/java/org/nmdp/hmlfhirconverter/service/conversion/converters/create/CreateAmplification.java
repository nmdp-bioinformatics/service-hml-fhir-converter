package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.Amplification;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateAmplification {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static Amplification createAmplification(JsonObject jsonObject) {
        Amplification amplification = new Amplification();

        if (jsonObject == null) {
            return amplification;
        }

        amplification.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        amplification.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        amplification.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        amplification.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        amplification.setRegisteredName(jsonObject.has(HmlFieldConstants.AMP_REGISTEREDNAME) ? jsonObject.get(HmlFieldConstants.AMP_SEQUENCE).getAsString() : null);
        amplification.setSequence(CreateSequence.createSequence(jsonObject.has(HmlFieldConstants.AMP_SEQUENCE) ? jsonObject.get(HmlFieldConstants.AMP_SEQUENCE).getAsString() : null));

        return amplification;
    }

}
