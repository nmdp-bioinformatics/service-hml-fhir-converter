package org.nmdp.hmlfhirconverter.controller;

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

import io.swagger.api.ReportingCenterApi;
import io.swagger.model.ReportingCenter;
import io.swagger.api.NotFoundException;

import io.swagger.model.TypingTestName;
import org.apache.log4j.Logger;

import org.nmdp.hmlfhirconverter.service.ReportingCenterService;
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
@RequestMapping("/reportingCenter")
@CrossOrigin
public class ReportingCenterController implements ReportingCenterApi {

    private final ReportingCenterService reportingCenterService;
    private static final Logger LOG = Logger.getLogger(ReportingCenterController.class);

    @Autowired
    public ReportingCenterController(ReportingCenterService reportingCenterService) {
        this.reportingCenterService = reportingCenterService;
    }

    @Override
    @RequestMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<ReportingCenter>> createReportingCenter(@RequestBody ReportingCenter reportingCenter) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(reportingCenterService.createReportingCenter(reportingCenter).toDto(), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /create", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/createMulti", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<List<ReportingCenter>>> createReportingCenters(@RequestBody List<ReportingCenter> reportingCenters) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.ReportingCenter> result = reportingCenterService.createReportingCenters(reportingCenters);
            List<ReportingCenter> transferResult = Converters.convertList(result, r -> r.toDto());
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /createMulti", ex);
            return () -> new ResponseEntity<>(new ArrayList<ReportingCenter>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<Boolean>> deleteReportingCenter(@RequestBody ReportingCenter reportingCenter) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(reportingCenterService.deleteReportingCenter(reportingCenter), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /delete", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<Boolean>> deleteReportingCenter(@PathVariable String id) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(reportingCenterService.deleteReportingCenter(id), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /delete/{id}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<ReportingCenter>> getReportingCenter(@PathVariable String id) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(reportingCenterService.getReportingCenter(id).toDto(), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /get/{id}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/getMulti/{maxResults}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<List<ReportingCenter>>> getReportingCenters(@PathVariable Integer maxResults) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.ReportingCenter> result = reportingCenterService.findReportingCentersByMaxReturn(maxResults).getContent();
            List<ReportingCenter> transferResult = Converters.convertList(result, r -> r.toDto());
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /getMulti/{maxResults}", ex);
            return () -> new ResponseEntity<>(new ArrayList<ReportingCenter>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public Callable<ResponseEntity<ReportingCenter>> updateReportingCenter(@RequestBody ReportingCenter reportingCenter) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(reportingCenterService.updateReportingCenter(reportingCenter).toDto(), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /update", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}