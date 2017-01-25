package org.nmdp.hmlfhirconverter.service;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 1/19/17.
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

import io.swagger.model.TypeaheadQuery;

import org.apache.log4j.Logger;

import org.nmdp.hmlfhirconverter.dao.PropertyRepository;
import org.nmdp.hmlfhirconverter.dao.custom.PropertyCustomRepository;
import org.nmdp.hmlfhirconverter.domain.Property;
import org.nmdp.hmlfhirconverter.util.QueryBuilder;
import org.nmdp.hmlfhirconverter.service.base.CascadingRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl extends CascadingRepositoryService<Property> implements PropertyService {
    private final PropertyCustomRepository propertyCustomRepository;
    private static final Logger LOG = Logger.getLogger(PropertyService.class);

    @Autowired
    public PropertyServiceImpl(@Qualifier("propertyRepository") PropertyRepository propertyRepository,
                           @Qualifier("propertyCustomRepository") PropertyCustomRepository propertyCustomRepository) {
        super(propertyRepository);
        this.propertyCustomRepository = propertyCustomRepository;
    }

    @Override
    public Property getProperty(String id) {
        return mongoRepository.findOne(id);
    }

    @Override
    public List<Property> getTypeaheadProperties(Integer maxResults, TypeaheadQuery typeaheadQuery) {
        Query query = QueryBuilder.buildQuery(maxResults, typeaheadQuery);
        return propertyCustomRepository.findByQuery(query);
    }

    @Override
    public Page<Property> findPropertiesByMaxReturn(Integer maxResults, Integer pageNumber) {
        PageRequest pageable = new PageRequest(pageNumber, maxResults);
        return mongoRepository.findAll(pageable);
    }

    @Override
    public List<Property> createProperties(List<io.swagger.model.Property> properties) {
        List<Property> nmdpModel = properties.stream()
                .filter(Objects::nonNull)
                .map(obj -> Property.convertFromSwagger(obj, Property.class))
                .collect(Collectors.toList());

        return mongoRepository.save(nmdpModel);
    }

    @Override
    public Property updateProperty(io.swagger.model.Property property) {
        Property nmdpModel = Property.convertFromSwagger(property, Property.class);
        return mongoRepository.save(nmdpModel);
    }

    @Override
    public Boolean deleteProperty(String id) {
        try {
            mongoRepository.delete(id);
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting property.", ex);
            return false;
        }
    }

    @Override
    public Boolean deleteProperty(io.swagger.model.Property property) {
        try {
            mongoRepository.delete(Property.convertFromSwagger(property, Property.class));
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting property.", ex);
            return false;
        }
    }
}
