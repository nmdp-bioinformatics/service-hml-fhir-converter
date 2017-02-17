package org.nmdp.hmlfhirconverter.controller;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 1/13/17.
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

import io.swagger.api.NotFoundException;
import io.swagger.api.GsspApi;
import io.swagger.model.Gssp;
import io.swagger.model.TypeaheadQuery;

import org.apache.log4j.Logger;
import org.nmdp.hmlfhirconverter.service.GsspService;
import org.nmdp.hmlfhirconverter.util.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/gssp")
@CrossOrigin
public class GsspController implements GsspApi {

    private final GsspService gsspService;
    private static final Logger LOG = Logger.getLogger(GsspController.class);

    @Autowired
    public GsspController(GsspService gsspService) {
        this.gsspService = gsspService;
    }

    @Override
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<List<Gssp>>> createGssps(@RequestBody List<Gssp> gssps) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.Gssp> result = gsspService.createItems(gssps);
            List<Gssp> transferResult = Converters.convertList(result, r -> r.toDto(r));
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /createMulti", ex);
            return () -> new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public Callable<ResponseEntity<Boolean>> deleteGssp(@RequestBody Gssp gssp) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(gsspService.deleteItem(gssp), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /delete", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public Callable<ResponseEntity<Boolean>> deleteGssp(@PathVariable String id) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(gsspService.deleteItem(id), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /delete/{id}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<Gssp>> getGssp(@PathVariable String id) throws NotFoundException {
        try {
            org.nmdp.hmlfhirconverter.domain.Gssp gssp = gsspService.getById(id);
            return () -> new ResponseEntity<>(gssp.toDto(gssp), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /get/{id}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{maxResults}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<List<Gssp>>> getTypeaheadGssps(@PathVariable(value = "maxResults") Integer maxResults, @RequestBody TypeaheadQuery typeaheadQuery) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.Gssp> result = gsspService.getTypeaheadItems(maxResults, typeaheadQuery);
            List<Gssp> transferResult = Converters.convertList(result, r -> r.toDto(r));
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /{maxResults}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{maxResults}/{pageNumber}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<List<Gssp>>> getGssps(@PathVariable Integer maxResults, @PathVariable Integer pageNumber) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.Gssp> result = gsspService.findByMaxReturn(maxResults, pageNumber).getContent();
            List<Gssp> transferResult = Converters.convertList(result, r -> r.toDto(r));
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /getMulti/{maxResults}", ex);
            return () -> new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<Gssp>> getModel() {
        try {
            org.nmdp.hmlfhirconverter.domain.Gssp gssp = new org.nmdp.hmlfhirconverter.domain.Gssp(true);
            return () -> new ResponseEntity<>(gssp.toDto(gssp), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error getting model.", ex);
            return () -> new ResponseEntity<>(new Gssp(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public Callable<ResponseEntity<Gssp>> updateGssp(@RequestBody Gssp gssp) throws NotFoundException {
        try {
            org.nmdp.hmlfhirconverter.domain.Gssp nmdpModel = gsspService.updateItem(gssp);
            return () -> new ResponseEntity<>(nmdpModel.toDto(nmdpModel), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /update", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
