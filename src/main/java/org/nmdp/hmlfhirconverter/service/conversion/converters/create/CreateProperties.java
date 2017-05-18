package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.Property;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateProperties {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static List<Property> createProperties(JsonObject jsonObject) {
        List<Property> properties = new ArrayList<>();
        Property property = new Property();

        if (jsonObject == null) {
            return properties;
        }

        property.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        property.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        property.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        property.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        property.setDescription(jsonObject.has(HmlFieldConstants.PROPERTIES_DESCRIPTION) ? jsonObject.get(HmlFieldConstants.PROPERTIES_DESCRIPTION).getAsString() : null);
        property.setName(jsonObject.has(HmlFieldConstants.PROPERTIES_NAME) ? jsonObject.get(HmlFieldConstants.PROPERTIES_NAME).getAsString() : null);
        property.setValue(jsonObject.has(HmlFieldConstants.PROPERTIES_VALUE) ? jsonObject.get(HmlFieldConstants.PROPERTIES_VALUE).getAsString() : null);
        property.setExtendedItems(CreateExtendedItems.createExtendedItems(jsonObject.has(HmlFieldConstants.PROPERTIES_EXTENDEDITEMS) ? jsonObject.get(HmlFieldConstants.PROPERTIES_EXTENDEDITEMS).getAsJsonObject() : null));

        properties.add(property);
        return properties;
    }
}
