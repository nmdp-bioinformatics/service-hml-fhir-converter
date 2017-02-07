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

import org.bson.types.ObjectId;
import org.nmdp.hmlfhirconverter.domain.ICascadable;
import org.nmdp.hmlfhirconverter.domain.internal.MongoConfiguration;
import org.nmdp.hmlfhirconverter.util.*;

import org.nmdp.hmlfhirconverter.util.QueryBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.annotation.Id;
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

import java.util.*;
import java.util.stream.Collectors;

abstract class CascadingUpdate<T extends SwaggerConverter<T, U>, U> implements ICascadingUpdate<T, U> {

    @Transient
    private final static Logger LOG = Logger.getLogger(CascadingUpdate.class);

    @Override
    public void updateCollectionProperties(T entity, MongoConfiguration mongoConfiguration) {
        MongoOperations mongoOperations = getMongoOperations(mongoConfiguration);
        List<Field> saveableEntityFields = getSavableFields(entity);

        for (Field field : saveableEntityFields) {
            Class<?> propertyClass = field.getType();
            Document document = getDocumentFromField(propertyClass);
            String collectionName = document.collection();
            field.setAccessible(true);
            IMongoDataRepositoryModel model;

            try {
                model = (IMongoDataRepositoryModel)field.get(entity);
                Converters.handleDateField(model);
                setUpdatedDateOnModel(model);
                field.set(entity, upsert(model, mongoOperations, collectionName));
            } catch (Exception ex) {
                LOG.error(ex);
                continue;
            }
        }
    }

    @Override
    public void saveCollectionProperties(T entity, MongoConfiguration mongoConfiguration) {
        MongoOperations mongoOperations = getMongoOperations(mongoConfiguration);
        List<Field> saveableEntityFields = getSavableFields(entity);

        for (Field field : saveableEntityFields) {
            Class<?> propertyClass = field.getType();
            Document document = getDocumentFromField(propertyClass);
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

    private void setUpdatedDateOnModel(IMongoDataRepositoryModel model) {
        try {
            Field field = model.getClass().getDeclaredField("dateUpdated");
            field.setAccessible(true);
            field.set(model, new Date());
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    private MongoOperations getMongoOperations(MongoConfiguration configuration) {
        return new MongoTemplate(new MongoClient(createMongoConnectionString(configuration)),
                configuration.getDatabaseName());
    }

    private List<Field> getSavableFields(T entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(Objects::nonNull)
                .filter(r -> implementsCascading(r))
                .collect(Collectors.toList());
    }

    private Document getDocumentFromField(Class<?> propertyClass) {
        Annotation annotation = Arrays.stream(propertyClass.getAnnotations())
                .filter(Objects::nonNull)
                .filter(a -> a.annotationType().equals(Document.class))
                .findFirst()
                .get();

        return (Document)annotation;
    }

    private IMongoDataRepositoryModel upsert(IMongoDataRepositoryModel model, MongoOperations mongoOperations, String collectionName) throws Exception {
        WriteResult writeResult = mongoOperations.upsert(buildUpsertQuery(model), buildUpsertUpdate(model), collectionName);
        DBCollection collection = mongoOperations.getCollection(collectionName);
        Object upsertedId;

        if (writeResult.isUpdateOfExisting()) {
            try {
                Field field = model.getClass().getDeclaredField("id");
                field.setAccessible(true);
                String id = field.get(model).toString();
                upsertedId = objectifyId(id);
            } catch (Exception ex) {
                LOG.error(ex);
                throw new Exception("Unable to evaluate 'id' property", ex);
            }
        } else {
            upsertedId = writeResult.getUpsertedId();
        }

        DBObject result = collection.findOne(upsertedId);
        return model.convertGenericResultToModel(result, model, getDocumentProperties(model));
    }

    private ObjectId objectifyId(String id) {
        return new ObjectId(id);
    }

    private Query buildUpsertQuery(IMongoDataRepositoryModel model) {
        List<String> propertyNames = new ArrayList<>();

        if (hasDocumentId(model)) {
            propertyNames.add("id");
            return QueryBuilder.buildEqualsPropertyQuery(model, propertyNames, true);
        }

        propertyNames = getDocumentProperties(model).stream()
                .filter(Objects::nonNull)
                .filter(obj -> obj.getName() != "dateUpdated" && obj.getName() != "id")
                .map(obj -> obj.getName())
                .collect(Collectors.toList());

        return QueryBuilder.buildEqualsPropertyQuery(model, propertyNames, false);
    }

    private boolean hasDocumentId(IMongoDataRepositoryModel model) {
        Field id = getDocumentProperties(model).stream()
                .filter(prop -> prop.getName() == "id")
                .findFirst()
                .get();

        id.setAccessible(true);
        if (getFieldValue(model, id) == null) {
            return false;
        }

        return true;
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
