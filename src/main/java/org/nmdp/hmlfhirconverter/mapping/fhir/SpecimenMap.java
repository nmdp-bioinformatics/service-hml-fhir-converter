package org.nmdp.hmlfhirconverter.mapping.fhir;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 3/27/17.
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

import io.swagger.model.CollectionMethod;
import io.swagger.model.Hml;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import org.nmdp.hmlfhirconverter.domain.fhir.Specimen;
import org.nmdp.hmlfhirconverter.domain.fhir.Type;

import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

public class SpecimenMap implements Converter<Hml, Specimen> {

    @Override
    public Specimen convert(MappingContext<Hml, Specimen> context) {
        if (context.getSource() == null) {
            return null;
        }

        Specimen specimen = new Specimen();
        specimen.setType(extractTypeFromSource(context.getSource()));

        return specimen;
    }

    private Type extractTypeFromSource(Hml hml) {
        Type type = new Type();
        List<CollectionMethod> collectionMethods = hml.getSamples().stream()
            .filter(Objects::nonNull)
            .findFirst()
            .get()
                .getCollectionMethods().stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        if (collectionMethods.size() == 0) {
            return type;
        }

        CollectionMethod collectionMethod = collectionMethods.get(0);
        type.setSpecimenType(collectionMethod.getName());

        return type;
    }
}
