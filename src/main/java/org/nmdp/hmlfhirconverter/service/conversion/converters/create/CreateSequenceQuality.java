package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.SequenceQuality;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateSequenceQuality {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static SequenceQuality createSequenceQuality(JsonObject jsonObject) {
        SequenceQuality sequenceQuality = new SequenceQuality();

        if (jsonObject == null) {
            return  sequenceQuality;
        }

        sequenceQuality.setId(jsonObject.has(HmlFieldConstants.SEQQUAL_ID) ? jsonObject.get(HmlFieldConstants.SEQQUAL_ID).getAsString() : null);
        sequenceQuality.setActive(jsonObject.has(HmlFieldConstants.SEQQUAL_ACTIVE) ? jsonObject.get(HmlFieldConstants.SEQQUAL_ACTIVE).getAsBoolean() : null);
        sequenceQuality.setDateCreated(jsonObject.has(HmlFieldConstants.SEQQUAL_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.SEQQUAL_DATECREATED).getAsString()).toDate() : null);
        sequenceQuality.setDateUpdated(jsonObject.has(HmlFieldConstants.SEQQUAL_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.SEQQUAL_DATEUPDATED).getAsString()).toDate() : null);
        sequenceQuality.setQualityScore(jsonObject.has(HmlFieldConstants.SEQQUAL_QUALITYSCORE) ? jsonObject.get(HmlFieldConstants.SEQQUAL_QUALITYSCORE).getAsString() : null);
        sequenceQuality.setSequenceEnd(jsonObject.has(HmlFieldConstants.SEQQUAL_SEQUENCEEND) ? jsonObject.get(HmlFieldConstants.SEQQUAL_SEQUENCEEND).getAsInt() : null);
        sequenceQuality.setSequenceStart(jsonObject.has(HmlFieldConstants.SEQQUAL_SEQUENCESTART) ? jsonObject.get(HmlFieldConstants.SEQQUAL_SEQUENCESTART).getAsInt() : null);

        return sequenceQuality;
    }

}
