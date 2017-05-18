package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.IupacBases;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateIupacBases {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static List<IupacBases> createIupacBases(JsonObject jsonObject) {
        List<IupacBases> iupacBases = new ArrayList<>();
        IupacBases iupacBase = new IupacBases();

        if (jsonObject == null) {
            return iupacBases;
        }

        iupacBase.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        iupacBase.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        iupacBase.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        iupacBase.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        iupacBase.setProperty(jsonObject.has(HmlFieldConstants.IUPAC_PROPERTY) ? jsonObject.get(HmlFieldConstants.IUPAC_PROPERTY).getAsString() : null);
        iupacBase.setValue(jsonObject.has(HmlFieldConstants.IUPAC_VALUE) ? jsonObject.get(HmlFieldConstants.IUPAC_VALUE).getAsString() : null);

        iupacBases.add(iupacBase);
        return iupacBases;
    }

}
