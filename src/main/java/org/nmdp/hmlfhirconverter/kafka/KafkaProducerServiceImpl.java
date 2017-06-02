package org.nmdp.hmlfhirconverter.kafka;

/**
 * Created by Andrew S. Brown, Ph.D., <andrew@nmdp.org>, on 5/31/17.
 * <p>
 * service-hml-fhir-converter
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
import org.nmdp.hmlfhirconverter.config.KafkaProducerConfiguration;
import org.nmdp.hmlfhirconverter.kafka.config.KafkaMessageProducerConfiguration;
import org.nmdp.hmlfhirconvertermodels.dto.Hml;

import org.nmdp.servicekafkaproducermodel.models.KafkaMessage;
import org.nmdp.servicekafkaproducermodel.models.KafkaMessagePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaProducerConfiguration kafkaConfig;
    private final static Logger LOG = Logger.getLogger(KafkaProducerServiceImpl.class);

    @Autowired
    public KafkaProducerServiceImpl(@Qualifier("kafkaProducerConfiguration") KafkaProducerConfiguration kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    @Override
    public void produceKafkaMessages(Map<String, Hml> hmls, String topic, String key) {
        KafkaMessageProducerConfiguration config = new KafkaMessageProducerConfiguration(
            kafkaConfig.getProducerConfiguration(), topic, key);

        KafkaMessageProducer producer = new KafkaMessageProducer(config);
        List<KafkaMessage> kafkaMessages = transformHmlToKafka(hmls, key);
        producer.send(kafkaMessages);
    }

    private List<KafkaMessage> transformHmlToKafka(Map<String, Hml> hmls, String key) {
        List<KafkaMessage> messages = new ArrayList<>();

        for (Map.Entry<String, Hml> hml : hmls.entrySet()) {
            messages.add(createKafkaMessage(hml.getValue(), hml.getKey(), key));
        }

        return messages;
    }

    private KafkaMessage createKafkaMessage(Hml hml, String id, String key) {
        KafkaMessage message = new KafkaMessage();

        try {
            KafkaMessagePayload<Hml> payload = new KafkaMessagePayload<>(hml, id);
            message = new KafkaMessage(new Date(),
                    UUID.randomUUID().toString(), key, payload);
        } catch (Exception ex) {
            LOG.error(ex);
        }

        return message;
    }

}
