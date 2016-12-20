package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.TypingTestName;
import java.math.BigDecimal;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-20T14:42:08.864-06:00")

@Controller
public class HmlApiController implements HmlApi {

    public ResponseEntity<List<TypingTestName>> hmlTypingtTestNamesNamesGet(@ApiParam(value = "Number of results returned", required = true) @RequestParam(value = "size", required = true) BigDecimal size



) {
        // do some magic!
        return new ResponseEntity<List<TypingTestName>>(HttpStatus.OK);
    }

}
