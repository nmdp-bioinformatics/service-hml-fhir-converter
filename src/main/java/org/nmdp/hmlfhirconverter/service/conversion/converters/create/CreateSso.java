package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.Sso;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateSso {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static Sso createSso(JsonObject jsonObject) {
        Sso sso = new Sso();

        if (jsonObject == null) {
            return sso;
        }

        sso.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sso.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        sso.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        sso.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        sso.setLocus(jsonObject.has(HmlFieldConstants.SSO_LOCUS) ? jsonObject.get(HmlFieldConstants.SSO_LOCUS).getAsString() : null);
        sso.setTestId(jsonObject.has(HmlFieldConstants.SSO_TESTID) ? jsonObject.get(HmlFieldConstants.SSO_TESTID).getAsString() : null);
        sso.setTestIdSource(jsonObject.has(HmlFieldConstants.SSO_TESTIDSOURCE) ? jsonObject.get(HmlFieldConstants.SSO_TESTIDSOURCE).getAsString() : null);
        sso.setProperties(CreateProperties.createProperties(jsonObject.has(HmlFieldConstants.SSO_PROPERTY) ? jsonObject.get(HmlFieldConstants.SSO_PROPERTY).getAsJsonObject() : null));

        return sso;
    }

}
