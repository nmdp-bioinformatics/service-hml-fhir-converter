package org.nmdp.hmlfhirconverter.service.base;

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

import org.nmdp.hmlfhirconvertermodels.dto.TypeaheadQuery;
import org.apache.log4j.Logger;

import org.nmdp.hmlfhirconverter.dao.custom.MongoTemplateRepository;
import org.nmdp.hmlfhirconvertermodels.domain.base.SwaggerConverter;
import org.nmdp.hmlfhirconverter.util.QueryBuilder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class MongoCrudRepositoryService<T extends SwaggerConverter<T, U>, U> extends CascadingRepositoryService<T> implements IMongoCrudRepositoryService<T, U> {

    protected final MongoTemplateRepository<T> mongoCustomRepository;
    private final Class<T> tClass;
    private final Class<U> uClass;
    private static final Logger LOG = Logger.getLogger(MongoCrudRepositoryService.class);

    public MongoCrudRepositoryService(MongoTemplateRepository<T> mongoCustomRepository, MongoRepository<T, String> mongoRepository,
                                      Class<T> tClass, Class<U> uClass) {
        super(mongoRepository);
        this.mongoCustomRepository = mongoCustomRepository;
        this.tClass = tClass;
        this.uClass = uClass;
    }

    @Override
    public T getById(String id) {
        return super.mongoRepository.findOne(id);
    }

    @Override
    public Page<T> findByMaxReturn(Integer maxResults, Integer pageNumber) {
        PageRequest pageable = new PageRequest(pageNumber, maxResults);
        return super.mongoRepository.findAll(pageable);
    }

    @Override
    public List<T> getTypeaheadItems(Integer maxResults, TypeaheadQuery typeaheadQuery) {
        Query query = QueryBuilder.buildQuery(maxResults, typeaheadQuery);
        return mongoCustomRepository.findByQuery(query);
    }

    @Override
    public List<T> createItems(List<U> items) {
        List<T> nmdpModel = items.stream()
                .filter(Objects::nonNull)
                .map(obj -> new SwaggerConverter(tClass, uClass).convertFromSwagger(obj, tClass))
                .collect(Collectors.toList());

        return mongoRepository.save(nmdpModel);
    }

    @Override
    public T updateItem(U item) {
        T nmdpModel = new SwaggerConverter(tClass, uClass).convertFromSwagger(item, tClass);

        try {
            Field field = nmdpModel.getClass().getDeclaredField("dateUpdated");
            field.setAccessible(true);
            field.set(nmdpModel, new Date());
        } catch (Exception ex) {
            LOG.error("Error setting dateUpdated field.", ex);
        }

        return super.mongoRepository.save(nmdpModel);
    }

    @Override
    public Boolean deleteItem(String id) {
        try {
            mongoRepository.delete(id);
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting item by ID.", ex);
            return false;
        }
    }

    @Override
    public Boolean deleteItem(U item) {
        try {
            T nmdpModel = new SwaggerConverter(tClass, uClass).convertFromSwagger(item, tClass);
            mongoRepository.delete(nmdpModel);
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting item by ID.", ex);
            return false;
        }
    }
}
