package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.Project;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateProject {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static Project createProject(JsonObject jsonObject) {
        io.swagger.model.Project project = new Project();

        if (jsonObject == null) {
            return project;
        }

        project.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        project.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        project.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        project.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        project.setDescription(jsonObject.has(HmlFieldConstants.PROJECT_DESCRIPTION) ? jsonObject.get(HmlFieldConstants.PROJECT_DESCRIPTION).getAsString() : null);
        project.setName(jsonObject.has(HmlFieldConstants.PROJECT_NAME) ? jsonObject.get(HmlFieldConstants.PROJECT_NAME).getAsString() : null);

        return project;
    }
}
