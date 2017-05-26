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

import org.nmdp.hmlfhirconvertermodels.dto.AlleleAssignment;
import org.nmdp.hmlfhirconvertermodels.dto.GlString;
import org.nmdp.hmlfhirconvertermodels.dto.Hml;
import org.nmdp.hmlfhirconvertermodels.dto.Sample;
import org.nmdp.hmlfhirconvertermodels.dto.Typing;
import org.modelmapper.Converter;

import org.modelmapper.spi.MappingContext;
import org.nmdp.hmlfhirconvertermodels.domain.fhir.Glstring;

import java.util.List;
import java.util.ArrayList;

public class GlStringMap implements Converter<Hml, List<Glstring>> {

    @Override
    public List<Glstring> convert(MappingContext<Hml, List<Glstring>> context) {
        if (context.getSource() == null) {
            return null;
        }

        Hml hml = context.getSource();
        Sample sample = hml.getSamples().get(0);
        List<Typing> typings = sample.getTyping();
        List<Glstring> glStrings = new ArrayList<>();

        for (Typing typing : typings) {
            List<AlleleAssignment> alleleAssignments = typing.getAlleleAssignment();
            for (AlleleAssignment alleleAssignment : alleleAssignments) {
                Glstring glstring = new Glstring();
                GlString glString = alleleAssignment.getGlString();

                glstring.setUri(glString.getUri());
                glstring.setValue(glString.getValue());
                glStrings.add(glstring);
            }
        }

        return glStrings;
    }
}
