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

import org.nmdp.hmlfhirconverter.domain.HmlId;

import org.springframework.data.domain.Page;

import java.util.List;

public interface HmlIdService {
    HmlId getHmlId(String id);
    Page<HmlId> findHmlIdsByMaxReturn(Integer maxResults, Integer pageNumber);
    List<HmlId> getTypeaheadHmlIds(Integer maxResults, TypeaheadQuery typeaheadQuery);
    List<HmlId> createHmlIds(List<io.swagger.model.HmlId> hmlId);
    HmlId updateHmlId(io.swagger.model.HmlId hmlId);
    Boolean deleteHmlId(String id);
    Boolean deleteHmlId(io.swagger.model.HmlId hmlId);
}
