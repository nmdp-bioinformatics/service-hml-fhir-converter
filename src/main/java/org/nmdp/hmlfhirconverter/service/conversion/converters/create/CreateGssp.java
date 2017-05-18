package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.Gssp;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateGssp {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static Gssp createGssp(JsonObject jsonObject) {
        Gssp gssp = new Gssp();

        if (jsonObject == null) {
            return gssp;
        }

        gssp.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        gssp.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        gssp.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        gssp.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        gssp.setRegisteredName(jsonObject.has(HmlFieldConstants.GSSP_REGISTEREDNAME) ? jsonObject.get(HmlFieldConstants.GSSP_REGISTEREDNAME).getAsString() : null);
        gssp.setPrimerTarget(jsonObject.has(HmlFieldConstants.GSSP_PRIMERTARGET) ? jsonObject.get(HmlFieldConstants.GSSP_PRIMERTARGET).getAsString() : null);
        gssp.setPrimerSequence(jsonObject.has(HmlFieldConstants.GSSP_PRIMERSEQUENCE) ? jsonObject.get(HmlFieldConstants.GSSP_PRIMERSEQUENCE).getAsString() : null);
        gssp.setSequence(CreateSequence.createSequence(jsonObject.has(HmlFieldConstants.GSSP_SEQUENCE) ? jsonObject.get(HmlFieldConstants.GSSP_SEQUENCE).getAsString() : null));

        return gssp;
    }

}
