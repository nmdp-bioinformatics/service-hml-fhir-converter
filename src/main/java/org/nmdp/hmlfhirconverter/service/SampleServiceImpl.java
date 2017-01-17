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

import org.nmdp.hmlfhirconverter.dao.SampleRepository;
import org.nmdp.hmlfhirconverter.domain.Sample;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SampleServiceImpl implements SampleService {
    private final SampleRepository sampleRepository;
    private static final Logger LOG = Logger.getLogger(SampleServiceImpl.class);

    @Autowired
    public SampleServiceImpl(@Qualifier("sampleRepository") SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Sample getSample(String id) {
        return sampleRepository.findOne(id);
    };

    @Override
    public Page<Sample> findSamplesByMaxReturn(Integer maxResults, Integer pageNumber) {
        PageRequest pageable = new PageRequest(pageNumber, maxResults);
        return sampleRepository.findAll(pageable);
    }

    @Override
    public List<Sample> createSamples(List<io.swagger.model.Sample> samples) {
        List<Sample> nmdpModel = samples.stream()
                .filter(Objects::nonNull)
                .map(obj -> new Sample(obj))
                .collect(Collectors.toList());

        return sampleRepository.save(nmdpModel);
    }

    @Override
    public Sample updateSample(io.swagger.model.Sample sample) {
        Sample nmdpModel = new Sample(sample);
        return sampleRepository.save(nmdpModel);
    }

    @Override
    public Boolean deleteSample(String id) {
        try {
            sampleRepository.delete(id);
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting sample.", ex);
            return false;
        }
    }

    @Override
    public Boolean deleteSample(io.swagger.model.Sample sample) {
        try {
            sampleRepository.delete(new Sample(sample));
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting sample.", ex);
            return false;
        }
    }
}
