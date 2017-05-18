package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.swagger.model.ConsensusSequenceBlock;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;
import org.nmdp.hmlfhirconverter.util.PrimitiveParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateConsensusSequenceBlock {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static ConsensusSequenceBlock createConsensusSequenceBlock(JsonObject jsonObject) {
        ConsensusSequenceBlock consensusSequenceBlock = new ConsensusSequenceBlock();
        if (jsonObject == null) {
            return  consensusSequenceBlock;
        }

        consensusSequenceBlock.setId(jsonObject.has(HmlFieldConstants.CSB_ID) ? jsonObject.get(HmlFieldConstants.CSB_ID).getAsString() : null);
        consensusSequenceBlock.setActive(jsonObject.has(HmlFieldConstants.CSB_ACTIVE) ? jsonObject.get(HmlFieldConstants.CSB_ACTIVE).getAsBoolean() : null);
        consensusSequenceBlock.setContinuity(jsonObject.has(HmlFieldConstants.CSB_CONTINUITY) ? jsonObject.get(HmlFieldConstants.CSB_CONTINUITY).getAsBoolean() : null);
        consensusSequenceBlock.setDateCreated(jsonObject.has(HmlFieldConstants.CSB_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.CSB_DATECREATED).getAsString()).toDate() : null);
        consensusSequenceBlock.setDateUpdated(jsonObject.has(HmlFieldConstants.CSB_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.CSB_DATEUPDATED).getAsString()).toDate() : null);
        consensusSequenceBlock.setDescription(jsonObject.has(HmlFieldConstants.CSB_DESCRIPTION) ? jsonObject.get(HmlFieldConstants.CSB_DESCRIPTION).getAsString() : null);
        consensusSequenceBlock.setEnd(jsonObject.has(HmlFieldConstants.CSB_END) ? jsonObject.get(HmlFieldConstants.CSB_END).getAsInt() : null);
        consensusSequenceBlock.setExpectedCopyNumber(jsonObject.has(HmlFieldConstants.CSB_EXPECTEDCOPYNUM) ? jsonObject.get(HmlFieldConstants.CSB_EXPECTEDCOPYNUM).getAsInt() : null);
        consensusSequenceBlock.setPhaseSet(jsonObject.has(HmlFieldConstants.CSB_PHASESET) ? jsonObject.get(HmlFieldConstants.CSB_PHASESET).getAsString() : null);
        consensusSequenceBlock.setPhasingGroup(jsonObject.has(HmlFieldConstants.CSB_PHASINGGROUP) ? jsonObject.get(HmlFieldConstants.CSB_PHASINGGROUP).getAsString() : null);
        consensusSequenceBlock.setReferenceSequenceId(jsonObject.has(HmlFieldConstants.CSB_REFSEQID) ? jsonObject.get(HmlFieldConstants.CSB_REFSEQID).getAsString() : null);
        consensusSequenceBlock.setSequence(CreateSequence.createSequence(jsonObject.has(HmlFieldConstants.CSB_SEQUENCE) ? jsonObject.get(HmlFieldConstants.CSB_SEQUENCE).getAsString() : null));
        consensusSequenceBlock.setStart(jsonObject.has(HmlFieldConstants.CSB_START) ? jsonObject.get(HmlFieldConstants.CSB_START).getAsInt() : null);
        consensusSequenceBlock.setStrand(jsonObject.has(HmlFieldConstants.CSB_STRAND) ? jsonObject.get(HmlFieldConstants.CSB_STRAND).getAsString() : null);
        consensusSequenceBlock.setVariant(PrimitiveParser.isArray(jsonObject,HmlFieldConstants.CSB_VARIANT) ? CreateVariant.createVariantsAr(jsonObject.has(HmlFieldConstants.CSB_VARIANT) ? jsonObject.get(HmlFieldConstants.CSB_VARIANT).getAsJsonArray() : null):
                CreateVariant.createVariantsOb(jsonObject.has(HmlFieldConstants.CSB_VARIANT) ? jsonObject.get(HmlFieldConstants.CSB_VARIANT).getAsJsonObject() : null));
        consensusSequenceBlock.setSequenceQuality(CreateSequenceQuality.createSequenceQuality(jsonObject.has(HmlFieldConstants.CSB_SEQQUALITY) ? jsonObject.get(HmlFieldConstants.CSB_SEQQUALITY).getAsJsonObject() : null));


        return consensusSequenceBlock;
    }

    public static List<ConsensusSequenceBlock> createConsensusSequenceBlocksAr(JsonArray jsonArray) {
        List<ConsensusSequenceBlock> consensusSequenceBlocks = new ArrayList<>();

        if (jsonArray == null) {
            return consensusSequenceBlocks;
        }


        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jo = (JsonObject)jsonArray.get(i);
            consensusSequenceBlocks.add(createConsensusSequenceBlock(jo));
        }
        // loop through each element of the array and pass to below as a JsonObject
        for (JsonElement element : jsonArray) {

            consensusSequenceBlocks.add( createConsensusSequenceBlock((JsonObject)element));
        }
        return consensusSequenceBlocks;
    }

    public static List<ConsensusSequenceBlock> createConsensusSequenceBlocksOb(JsonObject jsonObject) {
        List<ConsensusSequenceBlock> consensusSequenceBlocks = new ArrayList<>();

        if (jsonObject == null) {
            return consensusSequenceBlocks;
        }

        consensusSequenceBlocks.add(createConsensusSequenceBlock(jsonObject));
        return consensusSequenceBlocks;

    }

}
