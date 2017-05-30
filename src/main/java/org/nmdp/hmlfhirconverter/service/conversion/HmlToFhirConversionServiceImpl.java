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


import org.nmdp.hmlfhirconvertermodels.dto.Hml;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import org.nmdp.hmlfhirconverter.config.KafkaProducerConfiguration;
import org.nmdp.hmlfhirconvertermodels.domain.fhir.FhirMessage;
import org.nmdp.hmlfhirconvertermodels.domain.fhir.Patient;
import org.nmdp.hmlfhirconvertermodels.domain.fhir.Specimen;
import org.nmdp.hmlfhirconverter.kafka.KafkaMessageProducer;
import org.nmdp.hmlfhirconverter.kafka.config.KafkaMessageProducerConfiguration;
import org.nmdp.hmlfhirconverter.mapping.fhir.PatientMap;
import org.nmdp.hmlfhirconverter.mapping.fhir.SpecimenMap;
import org.nmdp.hmlfhirconverter.mapping.object.DoDtoMapper;
import org.nmdp.hmlfhirconverter.util.HmlToFhirConverter;

import org.nmdp.servicekafkaproducermodel.models.KafkaMessage;
import org.nmdp.servicekafkaproducermodel.models.KafkaMessagePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HmlToFhirConversionServiceImpl implements HmlToFhirConversionService {

    private final KafkaMessageProducerConfiguration config;
    private final static Logger LOG = Logger.getLogger(HmlToFhirConversionServiceImpl.class);

    @Autowired
    public HmlToFhirConversionServiceImpl(@Qualifier("kafkaProducerConfiguration")
        KafkaProducerConfiguration kafkaProducerConfiguration) {
        config = new KafkaMessageProducerConfiguration(
            kafkaProducerConfiguration.getProducerConfiguration(),
            "hml-fhir-conversion",
            "andrew-mbp"
        );
    }

    public void produceKafkaMessages(List<Hml> hmls) {
        KafkaMessageProducer producer = new KafkaMessageProducer(config);
        List<KafkaMessage> messages = transformHmlToKafka(hmls);
        producer.send(messages);
    }

    public List<Hml> convertFileToHml(MultipartFile file) throws ConversionException {
        List<Hml> hmls = new ArrayList<>();

        try {
            String xml = new String(file.getBytes());
            hmls.add(HmlToFhirConverter.convertXmlToObject(xml));
        } catch (Exception ex) {
            LOG.error("Error converting byte[] to string.", ex);
            throw (ConversionException)ex;
        }

        return hmls;
    }

    public void convertHmlToFhir(String hmlXml) {
        Hml hml = HmlToFhirConverter.convertStringToXml(hmlXml);
        FhirMessage fhir = mapHmlToFhir(hml);
    }

    private List<org.nmdp.hmlfhirconvertermodels.domain.Hml> toDataObjects(List<Hml> dtos) {
        return dtos.stream()
            .filter(Objects::nonNull)
            .map(hml -> DoDtoMapper.INSTANCE.map(hml))
            .collect(Collectors.toList());
    }

    private List<KafkaMessage> transformHmlToKafka(List<Hml> hmls) {
        List<KafkaMessage> messages = hmls.stream()
            .filter(Objects::nonNull)
            .map(hml -> createKafkaMessage(hml, hml.getId()))
            .collect(Collectors.toList());

        return messages;
    }

    private KafkaMessage createKafkaMessage(Hml hml, String id) {
        KafkaMessage message = new KafkaMessage();

        try {
            KafkaMessagePayload<Hml> payload = new KafkaMessagePayload<>(hml, id);
            message = new KafkaMessage(new Date(),
                    UUID.randomUUID().toString(), "mac os x", payload);
        } catch (Exception ex) {
            LOG.error(ex);
        }

        return message;
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
