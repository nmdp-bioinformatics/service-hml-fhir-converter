package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.ReportingCenter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateReportingCenters {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static List<ReportingCenter> createReportingCenters(JsonObject jsonObject) {
        List<ReportingCenter> reportingCenters = new ArrayList<>();
        ReportingCenter reportingCenter = new ReportingCenter();

        if (jsonObject == null) {
            return reportingCenters;
        }

        reportingCenter.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        reportingCenter.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        reportingCenter.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        reportingCenter.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        reportingCenter.setContext(jsonObject.has(HmlFieldConstants.REPORTINGCENTER_CONTEXT) ? jsonObject.get(HmlFieldConstants.REPORTINGCENTER_CONTEXT).getAsString() : null);
        reportingCenter.setCenterId(jsonObject.has(HmlFieldConstants.REPORTINGCENTER_CENTERID) ? jsonObject.get(HmlFieldConstants.REPORTINGCENTER_CENTERID).getAsString() : null);

        reportingCenters.add(reportingCenter);
        return reportingCenters;
    }

}
