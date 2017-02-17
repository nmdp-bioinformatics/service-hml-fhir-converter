package org.nmdp.hmlfhirconverter.service;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 1/12/17.
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

import org.nmdp.hmlfhirconverter.dao.ExtendedItemRepository;
import org.nmdp.hmlfhirconverter.dao.custom.ExtendedItemCustomRepository;
import org.nmdp.hmlfhirconverter.domain.ExtendedItem;
import org.nmdp.hmlfhirconverter.service.base.MongoCrudRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ExtendedItemServiceImpl extends MongoCrudRepositoryService<ExtendedItem, io.swagger.model.ExtendedItem> implements ExtendedItemService {

    @Autowired
    public ExtendedItemServiceImpl(@Qualifier("extendedItemRepository") ExtendedItemRepository extendedItemRepository,
                             @Qualifier("extendedItemCustomRepository") ExtendedItemCustomRepository extendedItemCustomRepository) {
        super(extendedItemCustomRepository, extendedItemRepository, ExtendedItem.class, io.swagger.model.ExtendedItem.class);
    }
}
