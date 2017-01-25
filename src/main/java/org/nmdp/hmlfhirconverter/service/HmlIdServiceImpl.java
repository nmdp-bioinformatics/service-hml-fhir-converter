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

import io.swagger.model.TypeaheadQuery;

import org.apache.log4j.Logger;

import org.nmdp.hmlfhirconverter.dao.HmlIdRepository;
import org.nmdp.hmlfhirconverter.dao.custom.HmlIdCustomRepository;
import org.nmdp.hmlfhirconverter.domain.HmlId;
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
public class HmlIdServiceImpl extends CascadingRepositoryService<HmlId> implements HmlIdService {
    private final HmlIdCustomRepository hmlIdCustomRepository;
    private static final Logger LOG = Logger.getLogger(HmlIdService.class);

    @Autowired
    public HmlIdServiceImpl(@Qualifier("hmlIdRepository") HmlIdRepository hmlIdRepository,
                            @Qualifier("hmlIdCustomRepository") HmlIdCustomRepository hmlIdCustomRepository) {
        super(hmlIdRepository);
        this.hmlIdCustomRepository = hmlIdCustomRepository;
    }

    @Override
    public HmlId getHmlId(String id) {
        return mongoRepository.findOne(id);
    }

    @Override
    public List<HmlId> getTypeaheadHmlIds(Integer maxResults, TypeaheadQuery typeaheadQuery) {
        Query query = QueryBuilder.buildQuery(maxResults, typeaheadQuery);
        return hmlIdCustomRepository.findByQuery(query);
    }

    @Override
    public Page<HmlId> findHmlIdsByMaxReturn(Integer maxResults, Integer pageNumber) {
        PageRequest pageable = new PageRequest(pageNumber, maxResults);
        return mongoRepository.findAll(pageable);
    }

    @Override
    public List<HmlId> createHmlIds(List<io.swagger.model.HmlId> hmlIds) {
        List<HmlId> nmdpModel = hmlIds.stream()
                .filter(Objects::nonNull)
                .map(obj -> HmlId.convertFromSwagger(obj, HmlId.class))
                .collect(Collectors.toList());

        return mongoRepository.save(nmdpModel);
    }

    @Override
    public HmlId updateHmlId(io.swagger.model.HmlId hmlId) {
        HmlId nmdpModel = HmlId.convertFromSwagger(hmlId, HmlId.class);
        return mongoRepository.save(nmdpModel);
    }

    @Override
    public Boolean deleteHmlId(String id) {
        try {
            mongoRepository.delete(id);
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting hml.", ex);
            return false;
        }
    }

    @Override
    public Boolean deleteHmlId(io.swagger.model.HmlId hmlId) {
        try {
            mongoRepository.delete(HmlId.convertFromSwagger(hmlId, HmlId.class));
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting hml.", ex);
            return false;
        }
    }
}
