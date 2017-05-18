package org.nmdp.hmlfhirconverter.service.conversion.converters.create;

import io.swagger.model.Sequence;

/**
 * Created by ekan on 5/2/2017.
 */
public class CreateSequence {

    public static Sequence createSequence(String seq) {
        Sequence sequence = new Sequence();

        if (seq == null) {
            return sequence;
        }

        sequence.setSequence(seq);

        return sequence;
    }

}
