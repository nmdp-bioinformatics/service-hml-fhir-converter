package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.TypingTestName;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateTypingTestNames {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static List<TypingTestName> createTypingTestNames(JsonObject jsonObject) {
        List<TypingTestName> typingTestNames = new ArrayList<>();
        TypingTestName typingTestName = new TypingTestName();

        if (jsonObject == null) {
            return typingTestNames;
        }

        typingTestName.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        typingTestName.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        typingTestName.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        typingTestName.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        typingTestName.setDescription(jsonObject.has(HmlFieldConstants.TYPINGTESTNAMES_DESCRIPTION) ? jsonObject.get(HmlFieldConstants.TYPINGTESTNAMES_DESCRIPTION).getAsString() : null);
        typingTestName.setName(jsonObject.has(HmlFieldConstants.TYPINGTESTNAMES_NAME) ? jsonObject.get(HmlFieldConstants.TYPINGTESTNAMES_NAME).getAsString() : null);

        typingTestNames.add(typingTestName);
        return typingTestNames;
    }
}
