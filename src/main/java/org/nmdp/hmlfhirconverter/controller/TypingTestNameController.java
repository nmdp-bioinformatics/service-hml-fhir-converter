package org.nmdp.hmlfhirconverter.controller;

/**
 * Created by abrown3 on 12/21/16.
 */

import io.swagger.model.TypeaheadQuery;
import org.nmdp.hmlfhirconverter.service.TypingTestNameService;
import org.nmdp.hmlfhirconverter.util.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.api.NotFoundException;
import io.swagger.model.TypingTestName;
import io.swagger.api.TypingTestNameApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

@RestController
@RequestMapping("/typingTestName")
@CrossOrigin
public class TypingTestNameController implements TypingTestNameApi {

    private final TypingTestNameService typingTestNameService;
    private static final Logger LOG = Logger.getLogger(TypingTestNameController.class);

    @Autowired
    public TypingTestNameController(TypingTestNameService typingTestNameService) {
        this.typingTestNameService = typingTestNameService;
    }

    @Override
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<List<TypingTestName>>> createTypingTestNames(@RequestBody List<TypingTestName> typingTestNames) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.TypingTestName> result = typingTestNameService.createTypingTestNames(typingTestNames);
            List<TypingTestName> transferResult = Converters.convertList(result, r -> r.toDto(r));
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /createMulti", ex);
            return () -> new ResponseEntity<>(new ArrayList<TypingTestName>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public Callable<ResponseEntity<Boolean>> deleteTypingTestName(@RequestBody TypingTestName typingTestName) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(typingTestNameService.deleteTypingTestName(typingTestName), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /delete", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<Boolean>> deleteTypingTestName(@PathVariable String id) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(typingTestNameService.deleteTypingTestName(id), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /delete/{id}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{maxResults}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<List<TypingTestName>>> getTypeaheadTypingTestNames(@PathVariable(value = "maxResults") Integer maxResults, @RequestBody TypeaheadQuery typeaheadQuery) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.TypingTestName> result = typingTestNameService.getTypeaheadTypingTestNames(maxResults, typeaheadQuery);
            List<TypingTestName> transferResult = Converters.convertList(result, r -> r.toDto(r));
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /{maxResults}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public Callable<ResponseEntity<TypingTestName>> getTypingTestName(@PathVariable String id) throws NotFoundException {
        try {
            org.nmdp.hmlfhirconverter.domain.TypingTestName typingTestName = typingTestNameService.getTypingTestName(id);
            return () -> new ResponseEntity<>(typingTestName.toDto(typingTestName), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /get/{id}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "/{maxResults}/{pageNumber}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<List<TypingTestName>>> getTypingTestNames(@PathVariable Integer maxResults, @PathVariable Integer pageNumber) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.TypingTestName> result = typingTestNameService.findTypingTestNamesByMaxReturn(maxResults, pageNumber).getContent();
            List<TypingTestName> transferResult = Converters.convertList(result, r -> r.toDto(r));
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /getMulti/{maxResults}", ex);
            return () -> new ResponseEntity<>(new ArrayList<TypingTestName>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public Callable<ResponseEntity<TypingTestName>> updateTypingTestName(@RequestBody TypingTestName typingTestName) throws NotFoundException {
        try {
            org.nmdp.hmlfhirconverter.domain.TypingTestName nmdpModel = typingTestNameService.updateTypingTestName(typingTestName);
            return () -> new ResponseEntity<>(nmdpModel.toDto(nmdpModel), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on /update", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
