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

import org.apache.log4j.Logger;
import org.nmdp.hmlfhirconverter.domain.TypingTestName;
import org.nmdp.hmlfhirconverter.dao.TypingTestNameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TypingTestNameServiceImpl implements TypingTestNameService {
    private final TypingTestNameRepository typingTestNameRepository;

    private static final Logger LOG = Logger.getLogger(TypingTestNameService.class);

    @Autowired
    public TypingTestNameServiceImpl(@Qualifier("typingTestNameRepository") TypingTestNameRepository typingTestNameRepository) {
        this.typingTestNameRepository = typingTestNameRepository;
    }

    @Override
    public TypingTestName getTypingTestName(String id) {
        return typingTestNameRepository.findOne(id);
    }

    @Override
    public Page<TypingTestName> findTypingTestNamesByMaxReturn(Integer maxResults) {
        PageRequest pageable = new PageRequest(0, maxResults);
        return typingTestNameRepository.findAll(pageable);
    }

    @Override
    public TypingTestName createTypingTestName(io.swagger.model.TypingTestName typingTestName) {
        TypingTestName nmdpModel = new TypingTestName(typingTestName);
        return typingTestNameRepository.save(nmdpModel);
    }

    @Override
    public List<TypingTestName> createTypingTestNames(List<io.swagger.model.TypingTestName> typingTestNames) {
        List<TypingTestName> nmdpModel = typingTestNames.stream()
                .filter(Objects::nonNull)
                .map(obj -> new TypingTestName(obj))
                .collect(Collectors.toList());

        return typingTestNameRepository.save(nmdpModel);
    }

    @Override
    public TypingTestName updateTypingTestName(io.swagger.model.TypingTestName typingTestName) {
        TypingTestName nmdpModel = new TypingTestName(typingTestName);
        return typingTestNameRepository.save(nmdpModel);
    }

    @Override
    public Boolean deleteTypingTestName(String id) {
        try {
            typingTestNameRepository.delete(id);
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting typing test name.", ex);
            return false;
        }
    }

    @Override
    public Boolean deleteTypingTestName(io.swagger.model.TypingTestName typingTestName) {
        try {
            typingTestNameRepository.delete(new TypingTestName(typingTestName));
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting typing test name.", ex);
            return false;
        }
    }
}
