package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import com.google.gson.JsonObject;
import io.swagger.model.ReferenceSequence;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;
import org.nmdp.hmlfhirconverter.util.PrimitiveParser;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateReferenceSequence {

    public static ReferenceSequence createReferenceSequence(JsonObject jsonObject) {
        ReferenceSequence referenceSequence = new ReferenceSequence();

        if (jsonObject == null) {
            return referenceSequence;
        }
        referenceSequence.setId(jsonObject.has(HmlFieldConstants.REFSEQ_ID) ? jsonObject.get(HmlFieldConstants.REFSEQ_ID).getAsString() : null);
        referenceSequence.setName(jsonObject.has(HmlFieldConstants.REFSEQ_NAME) ? jsonObject.get(HmlFieldConstants.REFSEQ_NAME).getAsString() : null);
        referenceSequence.setStart(PrimitiveParser.parseStringToInteger(jsonObject.has(HmlFieldConstants.REFSEQ_START) ? jsonObject.get(HmlFieldConstants.REFSEQ_START).getAsString() : null));
        referenceSequence.setEnd(PrimitiveParser.parseStringToInteger(jsonObject.has(HmlFieldConstants.REFSEQ_END) ? jsonObject.get(HmlFieldConstants.REFSEQ_END).getAsString() : null));
        referenceSequence.setAccession(jsonObject.has(HmlFieldConstants.REFSEQ_ACCESSION) ? jsonObject.get(HmlFieldConstants.REFSEQ_ACCESSION).getAsString() : null);
        referenceSequence.setUri(jsonObject.has(HmlFieldConstants.REFSEQ_URI) ? jsonObject.get(HmlFieldConstants.REFSEQ_URI).getAsString() : null);
        return referenceSequence;

    }

}
