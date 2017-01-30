package org.nmdp.hmlfhirconverter.domain.base;


/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 1/25/17.
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

import com.mongodb.MongoClient;
import org.apache.log4j.Logger;

import org.nmdp.hmlfhirconverter.config.MongoConfig;
import org.nmdp.hmlfhirconverter.domain.ICascadable;

import org.nmdp.hmlfhirconverter.domain.internal.MongoConfiguration;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

abstract class CascadingUpdate<T extends SwaggerConverter<T, U>, U> implements ICascadingUpdate<T, U> {

    @Transient
    private final static Logger LOG = Logger.getLogger(CascadingUpdate.class);

    @Override
    public void saveCollectionProperties(T entity, MongoConfiguration mongoConfiguration) {
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(createMongoConnectionString(mongoConfiguration)), mongoConfiguration.getDatabaseName());
        List<Field> saveableEntityFields = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(Objects::nonNull)
                .filter(r -> implementsCascading(r))
                .collect(Collectors.toList());

        for (Field field : saveableEntityFields) {
            Class<?> propertyClass = field.getType();
            Annotation annotation = Arrays.stream(propertyClass.getAnnotations())
                    .filter(Objects::nonNull)
                    .filter(a -> a.annotationType().equals(Document.class))
                    .findFirst()
                    .get();

            Document document = (Document)annotation;
            String collectionName = document.collection();
            field.setAccessible(true);
            Object propertyValue;

            try {
                propertyValue = field.get(entity);
            } catch (Exception ex) {
                LOG.error(ex);
                continue;
            }

            mongoTemplate.save(propertyValue, collectionName);
        }
    }

    private Boolean implementsCascading(Field field) {
        return Arrays.stream(field.getType().getInterfaces())
                .filter(Objects::nonNull)
                .anyMatch(i -> i.equals(ICascadable.class));
    }

    private String createMongoConnectionString(MongoConfiguration config) {
        return config.getHost() + ":" + config.getPortNo();
    }
}
