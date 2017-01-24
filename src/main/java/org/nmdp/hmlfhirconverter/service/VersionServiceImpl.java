package org.nmdp.hmlfhirconverter.service;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 1/23/17.
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

import org.nmdp.hmlfhirconverter.dao.custom.VersionCustomRepository;
import org.nmdp.hmlfhirconverter.domain.Version;
import org.nmdp.hmlfhirconverter.dao.VersionRepository;

import org.nmdp.hmlfhirconverter.util.Converters;
import org.nmdp.hmlfhirconverter.util.QueryBuilder;
import org.nmdp.hmlfhirconverter.util.VersionStringComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VersionServiceImpl implements VersionService {
    private final VersionRepository versionRepository;
    private final VersionCustomRepository versionCustomRepository;
    private static final Logger LOG = Logger.getLogger(VersionServiceImpl.class);

    @Autowired
    public VersionServiceImpl(@Qualifier("versionRepository") VersionRepository versionRepository,
                              @Qualifier("versionCustomRepository") VersionCustomRepository versionCustomRepository) {
        this.versionRepository = versionRepository;
        this.versionCustomRepository = versionCustomRepository;
    }

    @Override
    public Version getVersion(String id) {
        return versionRepository.findOne(id);
    }

    @Override
    public Page<Version> findVersionsByMaxReturn(Integer maxResults, Integer pageNumber) {
        PageRequest pageable = new PageRequest(pageNumber, maxResults);
        return versionRepository.findAll(pageable);
    }

    @Override
    public List<Version> getTypeaheadVersions(Integer maxResults, TypeaheadQuery typeaheadQuery) {
        Query query = QueryBuilder.buildQuery(maxResults, typeaheadQuery);
        return versionCustomRepository.findByQuery(query);
    }

    @Override
    public Version getVersionByProperties(Version version, List<String> properties) {
        Query query = QueryBuilder.buildPropertyQuery(version, properties);
        return versionCustomRepository.findSingleByQuery(query);
    }

    @Override
    public Version getDefaultVersion() {
        Query query = QueryBuilder.buildPropertyQuery("description", "DEFAULT", true);
        List<Version> versions = versionCustomRepository.findByQuery(query);
        Collections.sort(Converters.convertList(versions, v -> v.toDto(v)), new VersionStringComparator());
        return versions.stream().findFirst().get();
    }

    @Override
    public List<Version> getAllVersions() {
        return versionRepository.findAll();
    }

    @Override
    public List<Version> createVersions(List<io.swagger.model.Version> versions) {
        List<Version> nmdpModel = versions.stream()
                .filter(Objects::nonNull)
                .map(obj -> Version.convertFromSwagger(obj, Version.class))
                .collect(Collectors.toList());

        return versionRepository.save(nmdpModel);
    }

    @Override
    public Version updateVersion(io.swagger.model.Version version) {
        Version nmdpModel = Version.convertFromSwagger(version, Version.class);
        return versionRepository.save(nmdpModel);
    }

    @Override
    public Boolean deleteVersion(String id) {
        try {
            versionRepository.delete(id);
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting version.", ex);
            return false;
        }
    }

    @Override
    public Boolean deleteVersion(io.swagger.model.Version version) {
        try {
            versionRepository.delete(Version.convertFromSwagger(version, Version.class));
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting version.", ex);
            return false;
        }
    }
}
