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

import org.nmdp.hmlfhirconverter.dao.HmlRepository;
import org.nmdp.hmlfhirconverter.dao.custom.HmlCustomRepository;
import org.nmdp.hmlfhirconvertermodels.domain.Hml;
import org.nmdp.hmlfhirconvertermodels.domain.internal.MongoConfiguration;
import org.nmdp.hmlfhirconverter.service.base.MongoCrudRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HmlServiceImpl extends MongoCrudRepositoryService<Hml, org.nmdp.hmlfhirconvertermodels.dto.Hml> implements HmlService {

    private final MongoConfiguration mongoConfiguration;

    @Autowired
    public HmlServiceImpl(@Qualifier("hmlRepository") HmlRepository hmlRepository,
                          @Qualifier("hmlCustomRepository") HmlCustomRepository hmlCustomRepository,
                          @Qualifier("mongoConfiguration") MongoConfiguration mongoConfiguration) {
        super(hmlCustomRepository, hmlRepository, Hml.class, org.nmdp.hmlfhirconvertermodels.dto.Hml.class);
        this.mongoConfiguration = mongoConfiguration;
    }

    @Override
    public List<Hml> createItems(List<org.nmdp.hmlfhirconvertermodels.dto.Hml> items) {
        List<Hml> nmdpModel = items.stream()
                .filter(Objects::nonNull)
                .map(obj -> Hml.convertFromSwagger(obj, Hml.class))
                .collect(Collectors.toList());

        for (Hml hml : nmdpModel) {
            hml.saveCollectionProperties(hml, mongoConfiguration);
        }

        return super.mongoRepository.save(nmdpModel);
    }

    @Override
    public Hml updateItem(org.nmdp.hmlfhirconvertermodels.dto.Hml item) {
        Hml nmdpModel = Hml.convertFromSwagger(item, Hml.class);
        nmdpModel.updateCollectionProperties(nmdpModel, mongoConfiguration);
        return super.mongoRepository.save(nmdpModel);
    }
}
