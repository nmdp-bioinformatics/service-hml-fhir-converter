package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.ReferenceDatabase;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateReferenceDatabase {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static ReferenceDatabase createReferenceDatabase(JsonObject jsonObject) {
        ReferenceDatabase referenceDatabase = new ReferenceDatabase();

        if (jsonObject == null) {
            return  referenceDatabase;
        }

        referenceDatabase.setId(jsonObject.has(HmlFieldConstants.REFDB_ID) ? jsonObject.get(HmlFieldConstants.REFDB_ID).getAsString() : null);
        referenceDatabase.setActive(jsonObject.has(HmlFieldConstants.REFDB_ACTIVE) ? jsonObject.get(HmlFieldConstants.REFDB_ACTIVE).getAsBoolean() : null);
        referenceDatabase.setDateCreated(jsonObject.has(HmlFieldConstants.REFDB_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.REFDB_DATECREATED).getAsString()).toDate() : null);
        referenceDatabase.setDateUpdated(jsonObject.has(HmlFieldConstants.REFDB_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.REFDB_DATEUPDATED).getAsString()).toDate() : null);
        referenceDatabase.setAvailability(jsonObject.has(HmlFieldConstants.REFDB_AVAILABILITY) ? jsonObject.get(HmlFieldConstants.REFDB_AVAILABILITY).getAsString(): null);
        referenceDatabase.setCurated(jsonObject.has(HmlFieldConstants.REFDB_CURATED) ? jsonObject.get(HmlFieldConstants.REFDB_CURATED).getAsBoolean(): null);
        referenceDatabase.setReferenceSequence(CreateReferenceSequence.createReferenceSequence(jsonObject.has(HmlFieldConstants.REFDB_REFSEQ) ? jsonObject.get(HmlFieldConstants.REFDB_REFSEQ).getAsJsonObject() : null));
        return referenceDatabase;
    }

}
