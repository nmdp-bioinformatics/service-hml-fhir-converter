package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.CollectionMethod;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateCollectionMethods {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static List<CollectionMethod> createCollectionMethods(JsonObject jsonObject) {
        List<CollectionMethod> collectionMethods = new ArrayList<>();
        CollectionMethod collectionMethod = new CollectionMethod();

        if (jsonObject == null) {
            return collectionMethods;
        }

        collectionMethod.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        collectionMethod.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        collectionMethod.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        collectionMethod.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        collectionMethod.setDescription(jsonObject.has(HmlFieldConstants.COLLECTIONMETHOD_DESCRIPTION) ? jsonObject.get(HmlFieldConstants.COLLECTIONMETHOD_DESCRIPTION).getAsString() : null);
        collectionMethod.setName(jsonObject.has(HmlFieldConstants.COLLECTIONMETHOD_NAME) ? jsonObject.get(HmlFieldConstants.COLLECTIONMETHOD_NAME).getAsString() : null);

        collectionMethods.add(collectionMethod);
        return collectionMethods;
    }

}
