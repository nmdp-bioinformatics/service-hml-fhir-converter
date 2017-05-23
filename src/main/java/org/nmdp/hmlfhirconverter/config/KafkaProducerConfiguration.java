package org.nmdp.hmlfhirconverter.config;

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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${kafka.bootstrap.server}")
    private String bootstrapServer;

    @Value("${kafka.acks}")
    private String acks;

    @Value("${kafka.retries}")
    private Integer retries;

    @Value("${kafka.batch.size}")
    private Integer batchSize;

    @Value("${kafka.linger.ms}")
    private Integer lingerMs;

    @Value("${kafka.buffer.memory}")
    private Integer bufferMemory;

    @Value("${kafka.key.serializer}")
    private String keySerializer;

    @Value("${kafka.value.serializer}")
    private String valueSerializer;

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.server.key}")
    private Object key;

    @Bean
    public String getTopic() {
        return topic;
    }

    @Bean
    public Object getKey() {
        return key;
    }

    @Bean
    public Properties getProducerConfiguration() {
        Properties props = new Properties();
        List<Field> fields = Arrays.stream(this.getClass().getDeclaredFields())
                .filter(Objects::nonNull)
                .filter(f -> implementsValueAttribute(f))
                .collect(Collectors.toList());

        for (Field field : fields) {
            field.setAccessible(true);

        }

        return props;
    }

    private Boolean implementsValueAttribute(Field field) {

        return true;
    }
}