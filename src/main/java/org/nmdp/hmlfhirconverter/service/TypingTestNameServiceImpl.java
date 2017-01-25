package org.nmdp.hmlfhirconverter.service;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 12/22/16.
 * <p>
 * service-hmlFhirConverter
 * Copyright (c) 2012-2016 National Marrow Donor Program (NMDP)
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

import org.nmdp.hmlfhirconverter.dao.custom.TypingTestNameCustomRepository;
import org.nmdp.hmlfhirconverter.domain.TypingTestName;
import org.nmdp.hmlfhirconverter.dao.TypingTestNameRepository;
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
public class TypingTestNameServiceImpl extends CascadingRepositoryService<TypingTestName> implements TypingTestNameService {
    private final TypingTestNameCustomRepository typingTestNameCustomRepository;
    private static final Logger LOG = Logger.getLogger(TypingTestNameService.class);

    @Autowired
    public TypingTestNameServiceImpl(@Qualifier("typingTestNameRepository") TypingTestNameRepository typingTestNameRepository,
                                     @Qualifier("typingTestNameCustomRepository") TypingTestNameCustomRepository typingTestNameCustomRepository) {
        super(typingTestNameRepository);
        this.typingTestNameCustomRepository = typingTestNameCustomRepository;
    }

    @Override
    public TypingTestName getTypingTestName(String id) {
        return mongoRepository.findOne(id);
    }

    @Override
    public Page<TypingTestName> findTypingTestNamesByMaxReturn(Integer maxResults, Integer pageNumber) {
        PageRequest pageable = new PageRequest(pageNumber, maxResults);
        return mongoRepository.findAll(pageable);
    }

    @Override
    public List<TypingTestName> getTypeaheadTypingTestNames(Integer maxResults, TypeaheadQuery typeaheadQuery) {
        Query query = QueryBuilder.buildQuery(maxResults, typeaheadQuery);
        return typingTestNameCustomRepository.findByQuery(query);
    }

    @Override
    public List<TypingTestName> createTypingTestNames(List<io.swagger.model.TypingTestName> typingTestNames) {
        List<TypingTestName> nmdpModel = typingTestNames.stream()
                .filter(Objects::nonNull)
                .map(obj -> TypingTestName.convertFromSwagger(obj, TypingTestName.class))
                .collect(Collectors.toList());

        return mongoRepository.save(nmdpModel);
    }

    @Override
    public TypingTestName updateTypingTestName(io.swagger.model.TypingTestName typingTestName) {
        TypingTestName nmdpModel = TypingTestName.convertFromSwagger(typingTestName, TypingTestName.class);
        return mongoRepository.save(nmdpModel);
    }

    @Override
    public Boolean deleteTypingTestName(String id) {
        try {
            mongoRepository.delete(id);
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting typing test name.", ex);
            return false;
        }
    }

    @Override
    public Boolean deleteTypingTestName(io.swagger.model.TypingTestName typingTestName) {
        try {
            mongoRepository.delete(TypingTestName.convertFromSwagger(typingTestName, TypingTestName.class));
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting typing test name.", ex);
            return false;
        }
    }
}
