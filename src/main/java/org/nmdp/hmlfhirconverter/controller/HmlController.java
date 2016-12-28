package org.nmdp.hmlfhirconverter.controller;

/**
 * Created by abrown3 on 12/21/16.
 */

import org.nmdp.hmlfhirconverter.service.HmlService;
import org.nmdp.hmlfhirconverter.domain.TypingTestName;
import org.nmdp.hmlfhirconverter.domain.TypingTestNameDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hml")
@Api(value = "Hml", description = "Hml Controller Actions")
public class HmlController {
    private final HmlService hmlService;

    @Autowired
    public HmlController(HmlService hmlService) {
        this.hmlService = hmlService;
    }

    @RequestMapping(value = "/typingTestNames/{size}", method = RequestMethod.GET,
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "getTestingTypeNames", response = TypingTestName.class)
    public
    @ResponseBody
    List<TypingTestName> getTypingTestNames(@PathVariable Integer size) {
        return hmlService.getTypingTestNames(size);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public
    @ResponseBody
    TypingTestName createTypingTestName(@RequestBody TypingTestNameDto typingTestNameDto) {
        return hmlService.createTypingTestName(typingTestNameDto.getName(), typingTestNameDto.getDescription());
    }
}
