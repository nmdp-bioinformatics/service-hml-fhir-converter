package org.nmdp.hmlfhirconverter.service.conversion;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 3/14/17.
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


import io.swagger.model.Hml;

import org.modelmapper.ModelMapper;

import org.nmdp.hmlfhirconverter.domain.fhir.FhirMessage;
import org.nmdp.hmlfhirconverter.domain.fhir.Patient;
import org.nmdp.hmlfhirconverter.domain.fhir.Specimen;
import org.nmdp.hmlfhirconverter.mapping.fhir.PatientMap;
import org.nmdp.hmlfhirconverter.mapping.fhir.SpecimenMap;
import org.nmdp.hmlfhirconverter.util.HmlToFhirConverter;

public class HmlToFhirConversionServiceImpl implements HmlToFhirConversionService {

    public void convertHmlToFhir(String hmlXml) {
        Hml hml = HmlToFhirConverter.convertStringToXml(hmlXml);
        FhirMessage fhir = mapHmlToFhir(hml);
    }

    private FhirMessage mapHmlToFhir(Hml hml) {
        ModelMapper mapper = new ModelMapper();
        FhirMessage message = new FhirMessage();
        Patient patient = new Patient();
        Specimen specimen = new Specimen();

        mapper.addMappings(new PatientMap());
        mapper.addConverter(new SpecimenMap());

        mapper.map(hml, patient);
        mapper.map(hml, specimen);

        return message;
    }
}