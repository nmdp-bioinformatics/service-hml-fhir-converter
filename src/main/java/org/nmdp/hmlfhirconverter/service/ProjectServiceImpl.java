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

import org.nmdp.hmlfhirconverter.dao.ProjectRepository;
import org.nmdp.hmlfhirconverter.dao.custom.ProjectCustomRepository;
import org.nmdp.hmlfhirconverter.domain.Project;
import org.nmdp.hmlfhirconverter.util.QueryBuilder;

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
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectCustomRepository projectCustomRepository;
    private static final Logger LOG = Logger.getLogger(ProjectService.class);

    @Autowired
    public ProjectServiceImpl(@Qualifier("projectRepository") ProjectRepository projectRepository,
                              @Qualifier("projectCustomRepository") ProjectCustomRepository projectCustomRepository) {
        this.projectRepository = projectRepository;
        this.projectCustomRepository = projectCustomRepository;
    }

    @Override
    public Project getProject(String id) {
        return projectRepository.findOne(id);
    }

    @Override
    public List<Project> getTypeaheadProjects(Integer maxResults, TypeaheadQuery typeaheadQuery) {
        Query query = QueryBuilder.buildQuery(maxResults, typeaheadQuery);
        return projectCustomRepository.findByQuery(query);
    }

    @Override
    public Page<Project> findProjectsByMaxReturn(Integer maxResults, Integer pageNumber) {
        PageRequest pageable = new PageRequest(pageNumber, maxResults);
        return projectRepository.findAll(pageable);
    }

    @Override
    public List<Project> createProjects(List<io.swagger.model.Project> projects) {
        List<Project> nmdpModel = projects.stream()
                .filter(Objects::nonNull)
                .map(obj -> Project.convertFromSwagger(obj, Project.class))
                .collect(Collectors.toList());

        return projectRepository.save(nmdpModel);
    }

    @Override
    public Project updateProject(io.swagger.model.Project project) {
        Project nmdpModel = Project.convertFromSwagger(project, Project.class);
        return projectRepository.save(nmdpModel);
    }

    @Override
    public Boolean deleteProject(String id) {
        try {
            projectRepository.delete(id);
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting hml.", ex);
            return false;
        }
    }

    @Override
    public Boolean deleteProject(io.swagger.model.Project project) {
        try {
            projectRepository.delete(Project.convertFromSwagger(project, Project.class));
            return true;
        } catch (Exception ex) {
            LOG.error("Error deleting hml.", ex);
            return false;
        }
    }
}
