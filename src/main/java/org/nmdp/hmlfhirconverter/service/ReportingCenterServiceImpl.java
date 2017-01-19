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

import io.swagger.model.QueryCriteria;
import io.swagger.model.TypeaheadQuery;
import org.apache.log4j.Logger;

import org.nmdp.hmlfhirconverter.dao.ReportingCenterRepository;
import org.nmdp.hmlfhirconverter.dao.custom.ReportingCenterCustomRepository;
import org.nmdp.hmlfhirconverter.domain.ReportingCenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportingCenterServiceImpl implements ReportingCenterService {
    private final ReportingCenterRepository reportingCenterRepository;
    private final ReportingCenterCustomRepository reportingCenterCustomRepository;
    private static final Logger LOG = Logger.getLogger(ReportingCenterServiceImpl.class);

    @Autowired
    public ReportingCenterServiceImpl(@Qualifier("reportingCenterRepository") ReportingCenterRepository reportingCenterRepository,
                                      @Qualifier("reportingCenterCustomRepository") ReportingCenterCustomRepository reportingCenterCustomRepository) {
        this.reportingCenterRepository = reportingCenterRepository;
        this.reportingCenterCustomRepository = reportingCenterCustomRepository;
    }

    @Override
    public ReportingCenter getReportingCenter(String id) {
        return reportingCenterRepository.findOne(id);
    }

    @Override
    public List<ReportingCenter> getTypeaheadReportingCenters(Integer maxResults, TypeaheadQuery typeaheadQuery) {
        final Pageable pageable = new PageRequest(0, maxResults);
        Query query = new Query();

        query.with(pageable);

        for (QueryCriteria criteria : typeaheadQuery.getCriteria()) {
            query.addCriteria(Criteria.where(criteria.getPropertyName()).regex(criteria.getQueryValue()));
        }

        return reportingCenterCustomRepository.findByQuery(query);
    }

    @Override
    public Page<ReportingCenter> findReportingCentersByMaxReturn(Integer maxResults, Integer pageNumber) {
        PageRequest pageable = new PageRequest(pageNumber, maxResults);
        return reportingCenterRepository.findAll(pageable);
    }

    @Override
    public List<ReportingCenter> createReportingCenters(List<io.swagger.model.ReportingCenter> reportingCenters) {
        List<ReportingCenter> nmdpModel = reportingCenters.stream()
                .filter(Objects::nonNull)
                .map(obj -> new ReportingCenter(obj))
                .collect(Collectors.toList());

        return reportingCenterRepository.save(nmdpModel);
    }

    @Override
    public ReportingCenter updateReportingCenter(io.swagger.model.ReportingCenter reportingCenter) {
        ReportingCenter nmdpModel = new ReportingCenter(reportingCenter);
        return reportingCenterRepository.save(nmdpModel);
    }

    @Override
    public Boolean deleteReportingCenter(String id) {
        try {
            reportingCenterRepository.delete(id);
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting reporting center.", ex);
            return false;
        }
    }

    @Override
    public Boolean deleteReportingCenter(io.swagger.model.ReportingCenter reportingCenter) {
        try {
            reportingCenterRepository.delete(new ReportingCenter(reportingCenter));
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting reporting center.", ex);
            return false;
        }
    }
}
