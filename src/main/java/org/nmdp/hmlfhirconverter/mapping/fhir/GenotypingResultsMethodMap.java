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

import org.nmdp.hmlfhirconvertermodels.dto.Hml;
import org.nmdp.hmlfhirconvertermodels.dto.Sample;
import org.nmdp.hmlfhirconvertermodels.dto.Typing;
import org.nmdp.hmlfhirconvertermodels.dto.TypingMethod;
import org.nmdp.hmlfhirconvertermodels.dto.SbtNgs;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import org.nmdp.hmlfhirconvertermodels.domain.fhir.GenotypingResultsMethod;

import java.util.List;
import java.util.ArrayList;

public class GenotypingResultsMethodMap implements Converter<Hml, List<GenotypingResultsMethod>> {

    @Override
    public List<GenotypingResultsMethod> convert(MappingContext<Hml, List<GenotypingResultsMethod>> context) {
        if (context.getSource() == null) {
            return null;
        }

        List<GenotypingResultsMethod> genotypingResultsMethods = new ArrayList<>();
        Hml hml = context.getSource();
        Sample sample = hml.getSamples().get(0);
        List<Typing> typings = sample.getTyping();

        for (Typing typing : typings) {
            GenotypingResultsMethod genotypingResultsMethod = new GenotypingResultsMethod();
            TypingMethod typingMethod = typing.getTypingMethod();
            SbtNgs sbtNgs = typingMethod.getSbtNgs();

            genotypingResultsMethod.setTestId(sbtNgs.getTestId());
            genotypingResultsMethod.setTestIdSource(sbtNgs.getTestIdSource());
            genotypingResultsMethods.add(genotypingResultsMethod);
        }

        return genotypingResultsMethods;
    }
}
