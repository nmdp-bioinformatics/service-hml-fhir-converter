package org.nmdp.hmlfhirconverter.mapping.fhir;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 4/25/17.
 * <p>
 * service-hmlFhirConverter
 * Copyright (c) 2012-2017 National Marrow Donor Program (NMDP)
 * <p>
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 3 of the License, or (at
 * your option) any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library;  if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.
 * <p>
 * > http://www.fsf.org/licensing/licenses/lgpl.html
 * > http://www.opensource.org/licenses/lgpl-license.php
 */

import io.swagger.model.*;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import org.nmdp.hmlfhirconverter.domain.fhir.BackboneElement;
import org.nmdp.hmlfhirconverter.domain.fhir.Identifier;
import org.nmdp.hmlfhirconverter.domain.fhir.ReferenceSequenceId;
import org.nmdp.hmlfhirconverter.domain.fhir.Sequence;

import java.util.ArrayList;
import java.util.List;

public class SequenceMap implements Converter<Hml, List<Sequence>> {

    @Override
    public List<Sequence> convert(MappingContext<Hml, List<Sequence>> context) {
        if (context.getSource() == null) {
            return null;
        }

        Hml hml = context.getSource();
        Sample sample = hml.getSamples().get(0);
        Typing typing = sample.getTyping();
        ConsensusSequence consensusSequence = typing.getConsensusSequence();
        ReferenceDatabase referenceDatabase = consensusSequence.getReferenceDatabase();

        return createSequences(referenceDatabase);
    }

    private List<Sequence> createSequences(ReferenceDatabase element) {
        List<Sequence> sequences = new ArrayList<>();

        if (element == null) {
            return sequences;
        }

        Sequence nameSequence = createSequence(element.getName());
        Sequence descriptionSequence = createSequence(element.getDescription());
        Sequence versionSequence = createSequence(element.getVersion());
        Sequence uriSequence = createSequence(element.getUri());

        if (nameSequence != null) { sequences.add(nameSequence); }
        if (descriptionSequence != null) { sequences.add(descriptionSequence); }
        if (versionSequence != null) { sequences.add(versionSequence); }
        if (uriSequence != null) { sequences.add(uriSequence); }

        for (Sequence sequence : createSequences(element.getReferenceSequence())) {
            sequences.add(sequence);
        }

        return sequences;
    }

    private List<Sequence> createSequences(ReferenceSequence element) {
        List<Sequence> sequences = new ArrayList();

        if (element == null) {
            return sequences;
        }

        Sequence idSequence = createSequence(element.getReferenceSequenceId());
        Sequence nameSequence = createSequence(element.getName());
        Sequence descriptionSequence = createSequence(element.getDescription());
        Sequence uriSequence = createSequence(element.getUri());
        String strand = element.getStrand();

        if (strand != null && !strand.isEmpty()) {
            Sequence sequence = new Sequence();
            BackboneElement backboneElement = new BackboneElement();

            backboneElement.setStrand(Integer.parseInt(strand));
            sequence.setReferenceSeq(backboneElement);
            sequences.add(sequence);
        }

        if (idSequence != null) { sequences.add(idSequence); }
        if (nameSequence != null) { sequences.add(nameSequence); }
        if (descriptionSequence != null) { sequences.add(descriptionSequence); }
        if (uriSequence != null) { sequences.add(uriSequence); }

        return sequences;
    }

    private Sequence createSequence(String refSeqId) {
        if (refSeqId.isEmpty() || refSeqId == null) {
            return null;
        }

        Sequence sequence = new Sequence();
        BackboneElement backboneElement = new BackboneElement();
        ReferenceSequenceId referenceSequenceId = new ReferenceSequenceId();
        Identifier identifier = new Identifier();

        identifier.setValue(refSeqId);
        referenceSequenceId.setIdentifier(identifier);
        backboneElement.setReferenceSequenceId(referenceSequenceId);
        sequence.setReferenceSeq(backboneElement);

        return sequence;
    }
}
