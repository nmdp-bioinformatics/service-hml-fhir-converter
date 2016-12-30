package org.nmdp.hmlfhirconverter.controller;

/**
 * Created by abrown3 on 12/21/16.
 */

import org.nmdp.hmlfhirconverter.service.HmlService;
import org.nmdp.hmlfhirconverter.util.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.api.NotFoundException;
import io.swagger.model.TypingTestName;
import io.swagger.api.HmlApi;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class HmlController implements HmlApi {

    private final HmlService hmlService;

    @Autowired
    public HmlController(HmlService hmlService) {
        this.hmlService = hmlService;
    }

    @Override
    public Callable<ResponseEntity<List<TypingTestName>>> getTypingTestNames(Integer maxResults) throws NotFoundException {
        List<org.nmdp.hmlfhirconverter.domain.TypingTestName> result = hmlService.findByMaxReturn(maxResults).getContent();
        List<TypingTestName> transferResult = Converters.convertList(result, r -> r.toDto());
        return () -> new ResponseEntity<>(transferResult, HttpStatus.OK);
    }

}
