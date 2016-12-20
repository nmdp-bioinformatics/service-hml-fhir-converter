package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.TypingTestName;
import java.math.BigDecimal;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-20T14:42:08.864-06:00")

@Api(value = "hml", description = "the hml API")
public interface HmlApi {

    @ApiOperation(value = "", notes = "Gets the list of available typing test names ", response = TypingTestName.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response", response = TypingTestName.class),
        @ApiResponse(code = 200, message = "Unexpected Error", response = TypingTestName.class) })
    @RequestMapping(value = "/hml/typingtTestNames/names",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<TypingTestName>> hmlTypingtTestNamesNamesGet(@ApiParam(value = "Number of results returned", required = true) @RequestParam(value = "size", required = true) BigDecimal size



);

}
