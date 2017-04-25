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
import org.nmdp.hmlfhirconverter.domain.fhir.AlleleName;

import java.util.ArrayList;
import java.util.List;

public class AlleleNameMap implements Converter<Hml, List<AlleleName>> {

    @Override
    public List<AlleleName> convert(MappingContext<Hml, List<AlleleName>> context) {
        if (context.getSource() == null) {
            return null;
        }

        List<AlleleName> alleleNames = new ArrayList<>();
        Hml hml = context.getSource();
        Sample sample = hml.getSamples().get(0);
        Typing typing = sample.getTyping();
        AlleleAssignment alleleAssignment = typing.getAlleleAssignment();
        List<Genotype> genotypeList = alleleAssignment.getGenotypes();

        for (Genotype genotype : genotypeList) {
            for (DiploidCombination diploidCombination : genotype.getDiploidCombinations()) {
                LocusBlock locusBlock = diploidCombination.getLocusBlock();
                for (Allele allele : locusBlock.getAlleles()) {
                    AlleleName alleleName = new AlleleName();

                    alleleName.setName(allele.getName());
                    alleleNames.add(alleleName);
                }
            }
        }

        return alleleNames;
    }
}
