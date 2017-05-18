package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.ConsensusSequence;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;
import org.nmdp.hmlfhirconverter.util.PrimitiveParser;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateConsensusSequence {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static ConsensusSequence createConsensusSequence(JsonObject jsonObject) {
        ConsensusSequence consensusSequence = new ConsensusSequence();

        if (jsonObject == null) {
            return consensusSequence;
        }

        consensusSequence.setId(jsonObject.has(HmlFieldConstants.CONSENSUSSEQ_ID) ? jsonObject.get(HmlFieldConstants.CONSENSUSSEQ_ID).getAsString() : null);
        consensusSequence.setActive(jsonObject.has(HmlFieldConstants.CONSENSUSSEQ_ACTIVE) ? jsonObject.get(HmlFieldConstants.CONSENSUSSEQ_ACTIVE).getAsBoolean() : null);
        consensusSequence.setDateCreated(jsonObject.has(HmlFieldConstants.CONSENSUSSEQ_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.CONSENSUSSEQ_DATECREATED).getAsString()).toDate() : null);
        consensusSequence.setDateUpdated(jsonObject.has(HmlFieldConstants.CONSENSUSSEQ_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.CONSENSUSSEQ_DATEUPDATED).getAsString()).toDate() : null);
        consensusSequence.setDate(jsonObject.has(HmlFieldConstants.CONSENSUSSEQ_DATE) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.CONSENSUSSEQ_DATE).getAsString()).toDate() : null);
        consensusSequence.setReferenceDatabase(CreateReferenceDatabase.createReferenceDatabase(jsonObject.has(HmlFieldConstants.CONSENSUSSEQ_REFERENCEDB) ? jsonObject.get(HmlFieldConstants.CONSENSUSSEQ_REFERENCEDB).getAsJsonObject() : null));
        consensusSequence.setConsensusSequenceBlocks(PrimitiveParser.isArray(jsonObject,HmlFieldConstants.CONSENSUSSEQ_CONSENSUSSEQUENCEBLOCK) ? CreateConsensusSequenceBlock.createConsensusSequenceBlocksAr(jsonObject.has(HmlFieldConstants.CONSENSUSSEQ_CONSENSUSSEQUENCEBLOCK) ? jsonObject.get(HmlFieldConstants.CONSENSUSSEQ_CONSENSUSSEQUENCEBLOCK).getAsJsonArray() : null):
                CreateConsensusSequenceBlock.createConsensusSequenceBlocksOb(jsonObject.has(HmlFieldConstants.CONSENSUSSEQ_CONSENSUSSEQUENCEBLOCK) ? jsonObject.get(HmlFieldConstants.CONSENSUSSEQ_CONSENSUSSEQUENCEBLOCK).getAsJsonObject() : null));

        return consensusSequence;
    }

}
