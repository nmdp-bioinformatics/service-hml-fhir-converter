package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.SbtSanger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateSbtSanger {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static SbtSanger createSbtSanger(JsonObject jsonObject) {
        SbtSanger sbtSanger = new SbtSanger();

        if (jsonObject == null) {
            return  sbtSanger;
        }

        sbtSanger.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sbtSanger.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        sbtSanger.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        sbtSanger.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        sbtSanger.setLocus(jsonObject.has(HmlFieldConstants.SBTSNGR_LOCUS) ? jsonObject.get(HmlFieldConstants.SBTSNGR_LOCUS).getAsString() : null);
        sbtSanger.setTestId(jsonObject.has(HmlFieldConstants.SBTSNGR_TESTID) ? jsonObject.get(HmlFieldConstants.SBTSNGR_TESTID).getAsString() : null);
        sbtSanger.setTestIdSource(jsonObject.has(HmlFieldConstants.SBTSNGR_TESTIDSOURCE) ? jsonObject.get(HmlFieldConstants.SBTSNGR_TESTIDSOURCE).getAsString() : null);
        sbtSanger.setProperties(CreateProperties.createProperties(jsonObject.has(HmlFieldConstants.SBTSNGR_PROPERTY) ? jsonObject.get(HmlFieldConstants.SBTSNGR_PROPERTY).getAsJsonObject() : null));
        sbtSanger.setAmplification(CreateAmplification.createAmplification(jsonObject.has(HmlFieldConstants.SBTSNGR_AMPLIFICATION) ? jsonObject.get(HmlFieldConstants.SBTSNGR_AMPLIFICATION).getAsJsonObject() : null));
        sbtSanger.setSubAmplification(CreateSubAmplification.createSubAmplification(jsonObject.has(HmlFieldConstants.SBTSNGR_SUBAMPLIFICATION) ? jsonObject.get(HmlFieldConstants.SBTSNGR_SUBAMPLIFICATION).getAsJsonObject() : null));
        sbtSanger.setGssp(CreateGssp.createGssp(jsonObject.has(HmlFieldConstants.SBTSNGR_GSSP) ? jsonObject.get(HmlFieldConstants.SBTSNGR_GSSP).getAsJsonObject() : null));

        return sbtSanger;
    }

}
