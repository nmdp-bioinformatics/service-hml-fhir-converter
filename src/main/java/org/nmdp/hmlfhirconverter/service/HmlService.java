package org.nmdp.hmlfhirconverter.service;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 1/20/17.
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

import org.nmdp.hmlfhirconvertermodels.domain.Hml;
import org.nmdp.hmlfhirconverter.service.base.IMongoCrudRepositoryService;
import org.nmdp.hmlfhirconvertermodels.domain.fhir.FhirMessage;

import java.util.List;
import java.util.Map;

public interface HmlService extends IMongoCrudRepositoryService<Hml, org.nmdp.hmlfhirconvertermodels.dto.Hml> {
    List<org.nmdp.hmlfhirconvertermodels.dto.Hml> convertByteArrayToHmls(byte[] bytes, String xmlPrefix) throws Exception;
    Map<String, org.nmdp.hmlfhirconvertermodels.dto.Hml> writeHmlToMongoConversionDb(List<org.nmdp.hmlfhirconvertermodels.dto.Hml> hmls);
    Map<String, FhirMessage> writeFhirToMongoConversionDb(List<FhirMessage> fhirMessages);
    List<org.nmdp.hmlfhirconvertermodels.dto.Hml> convertStringToHmls(String xml, String xmlPrefix) throws Exception;
    List<FhirMessage> convertByteArrayToFhirMessages(byte[] bytes) throws Exception;
}
