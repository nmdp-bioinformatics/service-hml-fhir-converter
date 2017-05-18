package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.HmlId;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateHmlId {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static HmlId createHmlId(JsonObject jsonObject) {
        HmlId hmlId = new HmlId();

        if (jsonObject == null) {
            return hmlId;
        }

        hmlId.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        hmlId.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        hmlId.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        hmlId.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        hmlId.setExtension(jsonObject.has(HmlFieldConstants.HMLID_EXTENSION) ? jsonObject.get(HmlFieldConstants.HMLID_EXTENSION).getAsString() : null);
        hmlId.setRootName(jsonObject.has(HmlFieldConstants.HMLID_ROOT) ? jsonObject.get(HmlFieldConstants.HMLID_ROOT).getAsString() : null);

        return hmlId;
    }

}
