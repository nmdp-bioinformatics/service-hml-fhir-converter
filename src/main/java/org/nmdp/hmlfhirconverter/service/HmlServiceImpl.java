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

import org.apache.log4j.Logger;
import org.nmdp.hmlfhir.ConvertFhirToHml;
import org.nmdp.hmlfhir.ConvertFhirToHmlImpl;
import org.nmdp.hmlfhir.ConvertHmlToFhir;
import org.nmdp.hmlfhir.ConvertHmlToFhirImpl;
import org.nmdp.hmlfhir.deserialization.HmlXmlDeserializerHyphenatedProperties;
import org.nmdp.hmlfhirconverter.dao.HmlRepository;
import org.nmdp.hmlfhirconverter.dao.custom.HmlCustomRepository;
import org.nmdp.hmlfhirconvertermodels.domain.Hml;
import org.nmdp.hmlfhirconvertermodels.domain.fhir.FhirMessage;
import org.nmdp.hmlfhirconvertermodels.domain.internal.MongoConfiguration;
import org.nmdp.hmlfhirconverter.service.base.MongoCrudRepositoryService;

import org.nmdp.hmlfhirmongo.models.ConversionStatus;
import org.nmdp.hmlfhirmongo.models.Status;
import org.nmdp.hmlfhirmongo.mongo.MongoConversionStatusDatabase;
import org.nmdp.hmlfhirmongo.mongo.MongoFhirDatabase;
import org.nmdp.hmlfhirmongo.mongo.MongoHmlDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HmlServiceImpl extends MongoCrudRepositoryService<Hml, org.nmdp.hmlfhirconvertermodels.dto.Hml> implements HmlService {

    private final MongoConfiguration mongoConfiguration;
    private final Yaml yaml;
    private static final Logger LOG = Logger.getLogger(HmlServiceImpl.class);

    @Autowired
    public HmlServiceImpl(@Qualifier("hmlRepository") HmlRepository hmlRepository,
                          @Qualifier("hmlCustomRepository") HmlCustomRepository hmlCustomRepository,
                          @Qualifier("mongoConfiguration") MongoConfiguration mongoConfiguration) {
        super(hmlCustomRepository, hmlRepository, Hml.class, org.nmdp.hmlfhirconvertermodels.dto.Hml.class);
        this.mongoConfiguration = mongoConfiguration;
        this.yaml = new Yaml();
    }

    @Override
    public List<Hml> createItems(List<org.nmdp.hmlfhirconvertermodels.dto.Hml> items) {
        List<Hml> nmdpModel = items.stream()
                .filter(Objects::nonNull)
                .map(obj -> Hml.convertFromSwagger(obj, Hml.class))
                .collect(Collectors.toList());

        for (Hml hml : nmdpModel) {
            hml.saveCollectionProperties(hml, mongoConfiguration);
        }

        return super.mongoRepository.save(nmdpModel);
    }

    @Override
    public Hml updateItem(org.nmdp.hmlfhirconvertermodels.dto.Hml item) {
        Hml nmdpModel = Hml.convertFromSwagger(item, Hml.class);
        nmdpModel.updateCollectionProperties(nmdpModel, mongoConfiguration);
        return super.mongoRepository.save(nmdpModel);
    }

    public List<org.nmdp.hmlfhirconvertermodels.dto.Hml> convertStringToHmls(String xml, String xmlPrefix) throws Exception {
        try {
            HmlXmlDeserializerHyphenatedProperties deserializer = new HmlXmlDeserializerHyphenatedProperties();
            ConvertHmlToFhir converter = new ConvertHmlToFhirImpl(deserializer);
            List<org.nmdp.hmlfhirconvertermodels.dto.Hml> hmls = new ArrayList<>();
            hmls.add(converter.convertToDto(xml, xmlPrefix));

            return hmls;
        } catch (Exception ex) {
            LOG.error("Error converting file to HML.", ex);
            throw ex;
        }
    }

    public List<FhirMessage> convertByteArrayToFhirMessages(byte[] bytes) throws Exception {
        try {
            ConvertFhirToHml converter = new ConvertFhirToHmlImpl();
            List<FhirMessage> fhirMessages = new ArrayList<>();
            fhirMessages.add(converter.toDto(new String(bytes), null));

            return fhirMessages;
        } catch (Exception ex) {
            LOG.error("Error converting file to FhirMessage.", ex);
            throw ex;
        }
    }

    public List<org.nmdp.hmlfhirconvertermodels.dto.Hml> convertByteArrayToHmls(byte[] bytes, String xmlPrefix) throws Exception {
        try {
            HmlXmlDeserializerHyphenatedProperties deserializer = new HmlXmlDeserializerHyphenatedProperties();
            ConvertHmlToFhir converter = new ConvertHmlToFhirImpl(deserializer);
            List<org.nmdp.hmlfhirconvertermodels.dto.Hml> hmls = new ArrayList<>();
            hmls.add(converter.convertToDto(new String(bytes), xmlPrefix));

            return hmls;
        } catch (Exception ex) {
            LOG.error("Error converting file to HML.", ex);
            throw ex;
        }
    }

    public Map<String, FhirMessage> writeFhirToMongoConversionDb(List<FhirMessage> fhirMessages) {
        List<FhirMessage> ids = new ArrayList<>();
        org.nmdp.hmlfhirmongo.config.MongoConfiguration config = null;

        try {
            URL url = new URL("file:." + "/src/main/resources/mongo-configuration.yaml");

            try (InputStream is = url.openStream()) {
                config = yaml.loadAs(is, org.nmdp.hmlfhirmongo.config.MongoConfiguration.class);
            }

            final MongoFhirDatabase database = new MongoFhirDatabase(config);

            for (FhirMessage fhirMessage : fhirMessages) {
                ids.add(database.save(fhirMessage));
            }
        } catch (Exception ex) {
            LOG.error("Error writing Fhir to Mongo.", ex);
        }

        return writeConversionStatusToMongo(config, ids);
    }

    public Map<String, org.nmdp.hmlfhirconvertermodels.dto.Hml> writeHmlToMongoConversionDb(List<org.nmdp.hmlfhirconvertermodels.dto.Hml> hmls) {
        List<org.nmdp.hmlfhirconvertermodels.dto.Hml> ids = new ArrayList<>();
        org.nmdp.hmlfhirmongo.config.MongoConfiguration config = null;

        try {
            URL url = new URL("file:." + "/src/main/resources/mongo-configuration.yaml");

            try (InputStream is = url.openStream()) {
                config = yaml.loadAs(is, org.nmdp.hmlfhirmongo.config.MongoConfiguration.class);
            }

            final MongoHmlDatabase database = new MongoHmlDatabase(config);

            for (org.nmdp.hmlfhirconvertermodels.dto.Hml hml : hmls) {
                ids.add(database.save(hml));
            }
        } catch (Exception ex) {
            LOG.error("Error writing Hml to Mongo.", ex);
        }

        return writeConversionStatusToMongo(ids, config);
    }

    private Map<String, org.nmdp.hmlfhirconvertermodels.dto.Hml> writeConversionStatusToMongo(
            List<org.nmdp.hmlfhirconvertermodels.dto.Hml> hmls, org.nmdp.hmlfhirmongo.config.MongoConfiguration config) {
        Map<String, org.nmdp.hmlfhirconvertermodels.dto.Hml> ids = new HashMap<>();

        try {
            final MongoConversionStatusDatabase database = new MongoConversionStatusDatabase(config);

            for (org.nmdp.hmlfhirconvertermodels.dto.Hml hml : hmls) {
                ConversionStatus status = new ConversionStatus(hml.getId(), Status.QUEUED, 0);
                ids.put(database.save(status).getId(), hml);
            }
        } catch (Exception ex) {
            LOG.error("Error writing status to Mongo.", ex);
        }

        return ids;
    }

    private Map<String, FhirMessage> writeConversionStatusToMongo(
            org.nmdp.hmlfhirmongo.config.MongoConfiguration config, List<FhirMessage> fhirMessages) {
        Map<String, FhirMessage> ids = new HashMap<>();

        try {
            final MongoConversionStatusDatabase database = new MongoConversionStatusDatabase(config);

            for (FhirMessage fhirMessage : fhirMessages) {
                ConversionStatus status = new ConversionStatus(fhirMessage.getId(), Status.QUEUED, 0);
                ids.put(database.save(status).getId(), fhirMessage);
            }
        } catch (Exception ex) {
            LOG.error("Error writing status to Mongo.", ex);
        }

        return ids;
    }
}
