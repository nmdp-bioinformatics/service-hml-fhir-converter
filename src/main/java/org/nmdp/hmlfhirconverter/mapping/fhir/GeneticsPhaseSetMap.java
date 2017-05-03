package org.nmdp.hmlfhirconverter.mapping.fhir;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 5/3/17.
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

import org.nmdp.hmlfhirconverter.domain.fhir.GeneticsPhaseSet;

import java.util.List;
import java.util.ArrayList;

public class GeneticsPhaseSetMap implements Converter<Hml, List<GeneticsPhaseSet>> {

    @Override
    public List<GeneticsPhaseSet> convert(MappingContext<Hml, List<GeneticsPhaseSet>> context) {
        if (context.getSource() == null) {
            return null;
        }

        Hml hml = context.getSource();
        Sample sample = hml.getSamples().get(0);
        Typing typing = sample.getTyping();
        ConsensusSequence consensusSequence = typing.getConsensusSequence();
        List<ConsensusSequenceBlock> consensusSequenceBlocks = consensusSequence.getConsensusSequenceBlocks();
        List<GeneticsPhaseSet> geneticsPhaseSets = new ArrayList<>();

        consensusSequenceBlocks.stream()
                .forEach(c -> geneticsPhaseSets.add(convertConsensusSequenceBlockToGeneticsPhaseSet(c)));

        return geneticsPhaseSets;
    }

    private GeneticsPhaseSet convertConsensusSequenceBlockToGeneticsPhaseSet(ConsensusSequenceBlock consensusSequenceBlock) {
        GeneticsPhaseSet geneticsPhaseSet = new GeneticsPhaseSet();

        geneticsPhaseSet.setPhasingGroup(consensusSequenceBlock.getPhasingGroup());
        geneticsPhaseSet.setPhaseSet(consensusSequenceBlock.getPhaseSet());

        return geneticsPhaseSet;
    }
}
