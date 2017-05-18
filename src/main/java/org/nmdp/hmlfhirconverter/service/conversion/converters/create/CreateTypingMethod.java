package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.TypingMethod;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateTypingMethod {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static TypingMethod createTypingMethod(JsonObject jsonObject) {
        TypingMethod typingMethod = new TypingMethod();

        if (jsonObject == null) {
            return typingMethod;
        }

        typingMethod.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        typingMethod.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        typingMethod.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        typingMethod.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        typingMethod.setSbtSanger(CreateSbtSanger.createSbtSanger(jsonObject.has(HmlFieldConstants.TYPINGMETHOD_SBTSANGER) ? jsonObject.get(HmlFieldConstants.TYPINGMETHOD_SBTSANGER).getAsJsonObject() : null));
        typingMethod.setSbtNgs(CreateSbtNgs.createSbtNgs(jsonObject.has(HmlFieldConstants.TYPINGMETHOD_SBTNGS) ? jsonObject.get(HmlFieldConstants.TYPINGMETHOD_SBTNGS).getAsJsonObject() : null));
        typingMethod.setSso(CreateSso.createSso(jsonObject.has(HmlFieldConstants.TYPINGMETHOD_SSO) ? jsonObject.get(HmlFieldConstants.TYPINGMETHOD_SSO).getAsJsonObject() : null));
        typingMethod.setSsp(CreateSsp.createSsp(jsonObject.has(HmlFieldConstants.TYPINGMETHOD_SSP) ? jsonObject.get(HmlFieldConstants.TYPINGMETHOD_SSP).getAsJsonObject() : null));

        return typingMethod;
    }

}
