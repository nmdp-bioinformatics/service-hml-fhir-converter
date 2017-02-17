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
import io.swagger.api.GenotypeApi;
import io.swagger.model.Genotype;
import io.swagger.model.TypeaheadQuery;

import org.apache.log4j.Logger;
import org.nmdp.hmlfhirconverter.service.GenotypeService;
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
@RequestMapping("/genotype")
@CrossOrigin
public class GenotypeController implements GenotypeApi {

    private final GenotypeService genotypeService;
    private static final Logger LOG = Logger.getLogger(GenotypeController.class);

    @Autowired
    public GenotypeController(GenotypeService genotypeService) {
        this.genotypeService = genotypeService;
    }

    @Override
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<List<Genotype>>> createGenotypes(@RequestBody List<Genotype> genotypes) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.Genotype> result = genotypeService.createItems(genotypes);
            List<Genotype> transferResult = Converters.convertList(result, r -> r.toDto(r));
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /createMulti", ex);
            return () -> new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public Callable<ResponseEntity<Boolean>> deleteGenotype(@RequestBody Genotype genotype) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(genotypeService.deleteItem(genotype), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /delete", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public Callable<ResponseEntity<Boolean>> deleteGenotype(@PathVariable String id) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(genotypeService.deleteItem(id), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /delete/{id}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<Genotype>> getGenotype(@PathVariable String id) throws NotFoundException {
        try {
            org.nmdp.hmlfhirconverter.domain.Genotype genotype = genotypeService.getById(id);
            return () -> new ResponseEntity<>(genotype.toDto(genotype), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /get/{id}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{maxResults}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<List<Genotype>>> getTypeaheadGenotypes(@PathVariable(value = "maxResults") Integer maxResults, @RequestBody TypeaheadQuery typeaheadQuery) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.Genotype> result = genotypeService.getTypeaheadItems(maxResults, typeaheadQuery);
            List<Genotype> transferResult = Converters.convertList(result, r -> r.toDto(r));
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /{maxResults}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{maxResults}/{pageNumber}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<List<Genotype>>> getGenotypes(@PathVariable Integer maxResults, @PathVariable Integer pageNumber) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.Genotype> result = genotypeService.findByMaxReturn(maxResults, pageNumber).getContent();
            List<Genotype> transferResult = Converters.convertList(result, r -> r.toDto(r));
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /getMulti/{maxResults}", ex);
            return () -> new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<Genotype>> getModel() {
        try {
            org.nmdp.hmlfhirconverter.domain.Genotype genotype = new org.nmdp.hmlfhirconverter.domain.Genotype(true);
            return () -> new ResponseEntity<>(genotype.toDto(genotype), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error getting model.", ex);
            return () -> new ResponseEntity<>(new Genotype(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public Callable<ResponseEntity<Genotype>> updateGenotype(@RequestBody Genotype genotype) throws NotFoundException {
        try {
            org.nmdp.hmlfhirconverter.domain.Genotype nmdpModel = genotypeService.updateItem(genotype);
            return () -> new ResponseEntity<>(nmdpModel.toDto(nmdpModel), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /update", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
