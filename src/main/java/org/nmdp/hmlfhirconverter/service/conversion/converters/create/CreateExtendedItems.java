package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.ExtendedItem;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateExtendedItems {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static List<ExtendedItem> createExtendedItems(JsonObject jsonObject) {
        List<ExtendedItem> extendedItems = new ArrayList<>();
        ExtendedItem extendedItem = new ExtendedItem();

        if (jsonObject == null) {
            return extendedItems;
        }

        extendedItem.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        extendedItem.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        extendedItem.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        extendedItem.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        extendedItem.setItem(jsonObject.has(HmlFieldConstants.EXTENDEDITEM_ITEM) ? jsonObject.get(HmlFieldConstants.EXTENDEDITEM_ITEM).getAsJsonObject() : null);

        extendedItems.add(extendedItem);
        return extendedItems;
    }

}
