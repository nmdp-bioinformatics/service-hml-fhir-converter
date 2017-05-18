package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.Allele;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateAlleles {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static List<Allele> createAlleles(JsonObject jsonObject) {
        List<Allele> alleles = new ArrayList<>();
        Allele allele = new Allele();

        if (jsonObject == null) {
            return alleles;
        }

        allele.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        allele.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        allele.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        allele.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        allele.setName(jsonObject.has(HmlFieldConstants.ALLELELIST_NAME) ? jsonObject.get(HmlFieldConstants.ALLELELIST_NAME).getAsString() : null);
        allele.setPresent(jsonObject.has(HmlFieldConstants.ALLELELIST_PRESENT) ? jsonObject.get(HmlFieldConstants.ALLELELIST_PRESENT).getAsString() : null);

        alleles.add(allele);
        return alleles;
    }

}
