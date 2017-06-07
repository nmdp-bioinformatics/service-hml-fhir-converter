package org.nmdp.hmlfhirconverter.controller.conversion;

/**
 * Created by Andrew S. Brown, Ph.D., <andrew@nmdp.org>, on 5/22/17.
 * <p>
 * service-hml-fhir-converter
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

import org.nmdp.hmlfhirconverter.kafka.KafkaProducerService;
import org.nmdp.hmlfhirconvertermodels.domain.fhir.FhirMessage;
import org.nmdp.hmlfhirconvertermodels.dto.Hml;

import org.nmdp.hmlfhirconverter.service.HmlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.List;

import org.apache.log4j.Logger;

@RestController
@RequestMapping("/conversion")
@CrossOrigin
public class ConversionController {
    private static final Logger LOG = Logger.getLogger(ConversionController.class);
    private final HmlService hmlService;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public ConversionController(HmlService hmlService, KafkaProducerService kafkaProducerService) {
        this.hmlService = hmlService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @RequestMapping(path = "/hmlToFhir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PATCH)
    public Callable<ResponseEntity<Boolean>> convertHmlFileToFhirByString(@RequestBody String xml) {
        try {
            List<Hml> hmls = hmlService.convertStringToHmls(xml, "ns2:");
            Map<String, Hml> dbHmls = hmlService.writeHmlToMongoConversionDb(hmls);
            kafkaProducerService.produceHmlKafkaMessages(dbHmls, "hml-fhir-conversion", "andrew-mbp");
            return () -> new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error in file upload hml to fhir conversion.", ex);
            return () -> new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/hmlToFhir", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<Boolean>> convertHmlFileToFhir(@RequestBody MultipartFile file) {
        try {
            List<Hml> hmls = hmlService.convertByteArrayToHmls(file.getBytes(), "ns2:");
            Map<String, Hml> dbHmls = hmlService.writeHmlToMongoConversionDb(hmls);
            kafkaProducerService.produceHmlKafkaMessages(dbHmls, "hml-fhir-conversion", "andrew-mbp");
            return () -> new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error in file upload hml to fhir conversion.", ex);
            return () -> new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/fhirToHml", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Callable<ResponseEntity<Boolean>> convertFhirFileToHml(@RequestBody MultipartFile file) {
        try {
            List<FhirMessage> fhirMessages = hmlService.convertByteArrayToFhirMessages(file.getBytes());
            Map<String, FhirMessage> dbFhirs = hmlService.writeFhirToMongoConversionDb(fhirMessages);
            kafkaProducerService.produceFhirKafkaMessages(dbFhirs, "fhir-hml-conversion", "andrew-mbp");
            return () -> new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error in file upload fhir to hml conversion.", ex);
            return () -> new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/fhirToHml", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PATCH)
    public Callable<ResponseEntity<Boolean>> convertFhirJsonToHml(@RequestBody FhirMessage fhirMessage) {
        try {
            Map<String, FhirMessage> dbFhirs = hmlService.writeFhirToMongoConversionDb(Arrays.asList(fhirMessage));
            kafkaProducerService.produceFhirKafkaMessages(dbFhirs, "fhir-hml-conversion", "andrew-mbp");
            return () -> new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error("Error in file upload fhir to hml conversion.", ex);
            return () -> new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
