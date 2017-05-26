package org.nmdp.hmlfhirconverter.kafka;

/**
 * Created by Andrew S. Brown, Ph.D., <andrew@nmdp.org>, on 5/22/17.
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

import java.util.*;
import java.util.stream.Collectors;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import org.nmdp.hmlfhirconverter.kafka.config.KafkaMessageProducerConfiguration;
import org.nmdp.servicekafkaproducermodel.models.KafkaMessage;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class KafkaMessageProducer {

    private final KafkaMessageProducerConfiguration config;
    private static final Logger LOG = Logger.getLogger(KafkaMessageProducer.class);

    public KafkaMessageProducer(KafkaMessageProducerConfiguration config) {
        this.config = config;
    }

    public void send(List<KafkaMessage> messages) {
        Properties properties = config.getProperties();
        Producer<byte[], byte[]> producer = new KafkaProducer<>(properties);
        List<KafkaProducerCallback> callbacks = new ArrayList<>();
        List<ProducerRecord<byte[], byte[]>> records =  messages.stream()
            .filter(Objects::nonNull)
            .map(message -> new ProducerRecord<>(config.getTopic(), toBinary(config.getKey()), message.toBinary()))
            .collect(Collectors.toList());

        for (ProducerRecord record : records) {
            KafkaProducerCallback callback = new KafkaProducerCallback();
            producer.send(record, callback);
            callbacks.add(callback);
        }

        producer.flush();

        while (!messageProductionComplete(callbacks)) {
            LOG.info("Sending kafka messages.");
        }

        producer.close();
    }

    private byte[] toBinary(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] array = new byte[0];

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            array = byteArrayOutputStream.toByteArray();
        } catch (Exception ex) {
            LOG.error("Error converting object to byte[].", ex);
        }

        return array;
    }

    private Boolean messageProductionComplete(List<KafkaProducerCallback> callbacks) {
        return !callbacks.stream()
            .filter(Objects::nonNull)
            .anyMatch(callback -> !callback.getIsComplete());
    }
}
