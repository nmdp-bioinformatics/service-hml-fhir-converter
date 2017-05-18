package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.Genotype;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateGenotypes {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static List<Genotype> createGenotypes(JsonObject jsonObject) {
        List<Genotype> genotypes = new ArrayList<>();
        Genotype genotype = new Genotype();

        if (jsonObject == null) {
            return  genotypes;
        }

        genotype.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        genotype.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        genotype.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        genotype.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        genotype.setDiploidCombinations(CreateDiploidCombinations.createDiploidCombinations(jsonObject.has(HmlFieldConstants.GENOTYPELIST_DIPLOIDCOMBINATION) ? jsonObject.get(HmlFieldConstants.GENOTYPELIST_DIPLOIDCOMBINATION).getAsJsonObject() : null));

        genotypes.add(genotype);
        return genotypes;
    }

}
