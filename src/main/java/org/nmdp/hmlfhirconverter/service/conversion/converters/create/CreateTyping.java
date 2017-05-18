package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.swagger.model.Typing;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;
import org.nmdp.hmlfhirconverter.util.PrimitiveParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateTyping {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static Typing createTyping(JsonObject jsonObject) {
        Typing typing = new Typing();

        if (jsonObject == null) {
            return typing;
        }

        typing.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        typing.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        typing.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        typing.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        typing.setDate(jsonObject.has(HmlFieldConstants.TYPING_DATE) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.TYPING_DATE).getAsString()).toDate() : null);
        typing.setGeneFamily(jsonObject.has(HmlFieldConstants.TYPING_GENEFAMILY) ? jsonObject.get(HmlFieldConstants.TYPING_GENEFAMILY).getAsString() : null);
        typing.setTypingMethod(CreateTypingMethod.createTypingMethod(jsonObject.has(HmlFieldConstants.TYPING_TYPINGMETHOD) ?  PrimitiveParser.isJsonObject(jsonObject, HmlFieldConstants.TYPING_TYPINGMETHOD) : null));
        typing.setProperties(CreateProperties.createProperties(jsonObject.has(HmlFieldConstants.TYPING_PROPERTY) ? jsonObject.get(HmlFieldConstants.TYPING_PROPERTY).getAsJsonObject() : null));
        typing.setAlleleAssignment(CreateAlleleAssignment.createAlleleAssignment(jsonObject.has(HmlFieldConstants.TYPING_ALLELEASSIGNMENT) ? PrimitiveParser.isJsonObject(jsonObject, HmlFieldConstants.TYPING_ALLELEASSIGNMENT) : null));
        typing.setConsensusSequence(CreateConsensusSequence.createConsensusSequence(jsonObject.has(HmlFieldConstants.TYPING_CONSENSUSSEQUENCE) ? PrimitiveParser.isJsonObject(jsonObject, HmlFieldConstants.TYPING_CONSENSUSSEQUENCE) : null));

        return typing;
    }

    public static List<Typing> createTypingsAr(JsonArray jsonArray) {
        List<Typing> typings = new ArrayList<>();

        if (jsonArray == null) {
            return typings;
        }


        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jo = (JsonObject)jsonArray.get(i);
            typings.add(createTyping(jo));
        }
        // loop through each element of the array and pass to below as a JsonObject
        for (JsonElement element : jsonArray) {

            typings.add( createTyping((JsonObject)element));
        }
        return typings;
    }

    public static List<Typing> createTypingsOb(JsonObject jsonObject) {
        List<Typing> typings = new ArrayList<>();

        if (jsonObject == null) {
            return typings;
        }

        typings.add(createTyping(jsonObject));
        return typings;

    }
}
