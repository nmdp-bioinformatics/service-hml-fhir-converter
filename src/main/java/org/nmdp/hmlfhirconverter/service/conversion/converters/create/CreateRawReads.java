package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.RawRead;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;
import org.nmdp.hmlfhirconverter.util.PrimitiveParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateRawReads {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static List<RawRead> createRawReads(JsonObject jsonObject) {
        List<RawRead> rawReads = new ArrayList<>();
        RawRead rawRead = new RawRead();

        if (jsonObject == null) {
            return rawReads;
        }



        rawRead.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        rawRead.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        rawRead.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        rawRead.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        rawRead.setAdapterTrimmed(PrimitiveParser.parseStringIntegerToBoolean(jsonObject.has(HmlFieldConstants.RAWREADS_ADAPTERTRIMMED) ? jsonObject.get(HmlFieldConstants.RAWREADS_ADAPTERTRIMMED).getAsString() : null));
        rawRead.setAvailability(jsonObject.has(HmlFieldConstants.RAWREADS_AVAILABILITY) ? jsonObject.get(HmlFieldConstants.RAWREADS_AVAILABILITY).getAsString() : null);
        rawRead.setFormat(jsonObject.has(HmlFieldConstants.RAWREADS_FORMAT) ? jsonObject.get(HmlFieldConstants.RAWREADS_FORMAT).getAsString() : null);
        rawRead.setPaired(PrimitiveParser.parseStringIntegerToBoolean(jsonObject.has(HmlFieldConstants.RAWREADS_PAIRED) ? jsonObject.get(HmlFieldConstants.RAWREADS_PAIRED).getAsString() : null));
        rawRead.setPooled(PrimitiveParser.parseStringIntegerToBoolean(jsonObject.has(HmlFieldConstants.RAWREADS_POOLED) ? jsonObject.get(HmlFieldConstants.RAWREADS_POOLED).getAsString() : null));
        rawRead.setQualityTrimmed(PrimitiveParser.parseStringIntegerToBoolean(jsonObject.has(HmlFieldConstants.RAWREADS_QUALITYTRIMMED) ? jsonObject.get(HmlFieldConstants.RAWREADS_QUALITYTRIMMED).getAsString() : null));
        rawRead.setUri(jsonObject.has(HmlFieldConstants.RAWREADS_URI) ? jsonObject.get(HmlFieldConstants.RAWREADS_URI).getAsString() : null);

        rawReads.add(rawRead);
        return rawReads;
    }

}
