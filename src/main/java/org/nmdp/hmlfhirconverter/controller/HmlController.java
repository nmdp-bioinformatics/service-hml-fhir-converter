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

import io.swagger.annotations.ApiOperation;
import io.swagger.api.NotFoundException;
import io.swagger.model.TypingTestName;
import io.swagger.api.HmlApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@CrossOrigin
public class HmlController implements HmlApi {

    private final HmlService hmlService;

    @Autowired
    public HmlController(HmlService hmlService) {
        this.hmlService = hmlService;
    }

    @Override
    @RequestMapping(value = "typingTestNames/{maxResults}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Callable<ResponseEntity<List<TypingTestName>>> getTypingTestNames(@PathVariable Integer maxResults) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.TypingTestName> result = hmlService.findByMaxReturn(maxResults).getContent();
            List<TypingTestName> transferResult = Converters.convertList(result, r -> r.toDto());
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            return () -> new ResponseEntity<>(new ArrayList<TypingTestName>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @ApiOperation(value = "Add typing test name object to database", notes = "Returns object from database", response = TypingTestName.class,
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "typingTestName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<TypingTestName>> createTypingTestName(@RequestBody(required = false) TypingTestName typingTestName) throws NotFoundException {
        try {
            return () -> new ResponseEntity<>(hmlService.createTypingTestName(typingTestName).toDto(), HttpStatus.OK);
        } catch (Exception ex) {
            return () -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(value = "typingTestNames", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<List<TypingTestName>>> createTypingTestNames(@PathVariable List<TypingTestName> typingTestNames) throws NotFoundException {
        try {
            List<org.nmdp.hmlfhirconverter.domain.TypingTestName> result = hmlService.createTypingTestNames(typingTestNames);
            List<TypingTestName> transferResult = Converters.convertList(result, r -> r.toDto());
            return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
        } catch (Exception ex) {
            return () -> new ResponseEntity<>(new ArrayList<TypingTestName>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
