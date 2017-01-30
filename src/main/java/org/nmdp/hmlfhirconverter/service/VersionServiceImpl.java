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

import org.nmdp.hmlfhirconverter.dao.custom.VersionCustomRepository;
import org.nmdp.hmlfhirconverter.domain.Version;
import org.nmdp.hmlfhirconverter.dao.VersionRepository;
import org.nmdp.hmlfhirconverter.service.base.MongoCrudRepositoryService;
import org.nmdp.hmlfhirconverter.util.Converters;
import org.nmdp.hmlfhirconverter.util.QueryBuilder;
import org.nmdp.hmlfhirconverter.util.VersionStringComparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VersionServiceImpl extends MongoCrudRepositoryService<Version, io.swagger.model.Version> implements VersionService {

    @Autowired
    public VersionServiceImpl(@Qualifier("versionRepository") VersionRepository versionRepository,
                              @Qualifier("versionCustomRepository") VersionCustomRepository versionCustomRepository) {
        super(versionCustomRepository, versionRepository, Version.class, io.swagger.model.Version.class);
    }

    @Override
    public Version getByProperties(Version version, List<String> properties) {
        Query query = QueryBuilder.buildPropertyQuery(version, properties);
        return super.mongoCustomRepository.findSingleByQuery(query);
    }

    @Override
    public Version getDefault() {
        Query query = QueryBuilder.buildPropertyQuery("description", "DEFAULT", true);
        List<Version> versions = super.mongoCustomRepository.findByQuery(query);
        Collections.sort(Converters.convertList(versions, v -> v.toDto(v)), new VersionStringComparator());
        return versions.stream().findFirst().get();
    }

    @Override
    public List<Version> getAll() {
        return super.mongoRepository.findAll();
    }
}
