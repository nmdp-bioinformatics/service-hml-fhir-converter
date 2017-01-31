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

import com.mongodb.*;

import org.apache.log4j.Logger;

import org.nmdp.hmlfhirconverter.domain.ICascadable;
import org.nmdp.hmlfhirconverter.domain.internal.MongoConfiguration;
import org.nmdp.hmlfhirconverter.util.*;

import org.nmdp.hmlfhirconverter.util.QueryBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.xml.bind.annotation.XmlAttribute;

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
        MongoOperations mongoOperations = new MongoTemplate(new MongoClient(createMongoConnectionString(mongoConfiguration)), mongoConfiguration.getDatabaseName());
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
            IMongoDataRepositoryModel model;

            try {
                model = (IMongoDataRepositoryModel)field.get(entity);
                Converters.handleDateField(model);
                field.set(entity, upsert(model, mongoOperations, collectionName));
            } catch (Exception ex) {
                LOG.error(ex);
                continue;
            }
        }
    }

    private IMongoDataRepositoryModel upsert(IMongoDataRepositoryModel model, MongoOperations mongoOperations, String collectionName) {
        WriteResult writeResult = mongoOperations.upsert(buildUpsertQuery(model), buildUpsertUpdate(model), collectionName);
        DBCollection collection = mongoOperations.getCollection(collectionName);
        DBObject result = collection.findOne(writeResult.getUpsertedId());

        return model.convertGenericResultToModel(result, model, getDocumentProperties(model));
    }

    private Query buildUpsertQuery(IMongoDataRepositoryModel model) {
        List<String> propertyNames = getDocumentProperties(model).stream()
                .filter(Objects::nonNull)
                .map(field -> field.getName())
                .collect(Collectors.toList());

        return QueryBuilder.buildPropertyQuery(model, propertyNames);
    }

    private Update buildUpsertUpdate(IMongoDataRepositoryModel model) {
        Update update = new Update();
        List<Field> documentPropertyFields = getDocumentProperties(model);

        for (Field field : documentPropertyFields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = getFieldValue(model, field);

            if (fieldName == "id") {
               continue;
            }

            update.set(fieldName, fieldValue);
        }

        return update;
    }

    private List<Field> getDocumentProperties(IMongoDataRepositoryModel model) {
        return Arrays.stream(model.getClass().getDeclaredFields())
                .filter(Objects::nonNull)
                .filter(obj -> !Arrays.stream(obj.getAnnotations())
                        .filter(Objects::nonNull)
                        .filter(a -> a.annotationType().equals(XmlAttribute.class))
                        .collect(Collectors.toList()).isEmpty())
                .collect(Collectors.toList());
    }

    private Object getFieldValue(Object model, Field field) {
        try {
            return field.get(model);
        } catch (Exception ex) {
            LOG.error(ex);
        }

        return null;
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
