package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.DiploidCombination;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateDiploidCombinations {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static List<DiploidCombination> createDiploidCombinations(JsonObject jsonObject) {
        List<DiploidCombination> diploidCombinations = new ArrayList<>();
        DiploidCombination diploidCombination = new DiploidCombination();

        if (jsonObject == null) {
            return diploidCombinations;
        }

        diploidCombination.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        diploidCombination.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        diploidCombination.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        diploidCombination.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        diploidCombination.setLocusBlock(CreateLocusBlock.createLocusBlock(jsonObject.has(HmlFieldConstants.DIPLOIDCOMBINATION_LOCUSBLOCK) ? jsonObject.get(HmlFieldConstants.DIPLOIDCOMBINATION_LOCUSBLOCK).getAsJsonObject() : null));

        diploidCombinations.add(diploidCombination);
        return diploidCombinations;
    }

}
