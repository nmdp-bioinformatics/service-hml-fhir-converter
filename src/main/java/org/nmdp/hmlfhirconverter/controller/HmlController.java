package org.nmdp.hmlfhirconverter.controller;

/**
 * Created by abrown3 on 12/21/16.
 */

import org.nmdp.hmlfhirconverter.service.HmlService;
import org.nmdp.hmlfhirconverter.util.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.api.NotFoundException;
import io.swagger.model.TypingTestName;
import io.swagger.api.HmlApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

@RestController
@CrossOrigin
public class HmlController implements HmlApi {

    private final HmlService hmlService;
    private static final Logger LOG = Logger.getLogger(HmlController.class);

    @Autowired
    public HmlController(HmlService hmlService) {
        this.hmlService = hmlService;
    }

    @Override
    @RequestMapping(path = "typingTestNames/{maxResults}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<List<TypingTestName>>> getTypingTestNames(@PathVariable Integer maxResults) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.TypingTestName> result = hmlService.findByMaxReturn(maxResults).getContent();
            List<TypingTestName> transferResult = Converters.convertList(result, r -> r.toDto());
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on typingTestNames/{maxResults}", ex);
            return () -> new ResponseEntity<>(new ArrayList<TypingTestName>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "typingTestName/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<Boolean>> deleteTypingTestName(@PathVariable String id) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(hmlService.deleteTypingTestName(id), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on typingTestNames/delete/{id}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "typingTestName/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<TypingTestName>> getTypingTestName(@PathVariable String id) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(hmlService.getTypingTestName(id).toDto(), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on typingTestName/{id}", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "typingTestName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<TypingTestName>> createTypingTestName(@RequestBody TypingTestName typingTestName) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(hmlService.createTypingTestName(typingTestName).toDto(), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on typingTestName", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "typingTestNames", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<List<TypingTestName>>> createTypingTestNames(@PathVariable List<TypingTestName> typingTestNames) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.TypingTestName> result = hmlService.createTypingTestNames(typingTestNames);
            List<TypingTestName> transferResult = Converters.convertList(result, r -> r.toDto());
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on typingTestNames", ex);
            return () -> new ResponseEntity<>(new ArrayList<TypingTestName>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "typingTestName/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<Boolean>> deleteTypingTestName(@RequestBody TypingTestName typingTestName) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(hmlService.deleteTypingTestName(typingTestName), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on typingTestName/delete", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(path = "typingTestName/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public Callable<ResponseEntity<TypingTestName>> updateTypingTestName(@RequestBody TypingTestName typingTestName) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(hmlService.updateTypingTestName(typingTestName).toDto(), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error on typingTestName/update", ex);
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
