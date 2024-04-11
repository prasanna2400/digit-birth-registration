package digit.assignment.web.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import digit.assignment.service.BirthRegistrationService;
import digit.assignment.util.ResponseInfoFactory;
import digit.assignment.web.models.BirthApplicationSearchCriteria;
import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.BirthRegistrationRequest;
import digit.assignment.web.models.BirthRegistrationResponse;
import digit.models.coremodels.RequestInfoWrapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.egov.common.contract.response.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@javax.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2024-04-02T19:03:47.640013960+05:30[Asia/Kolkata]")
@Controller
@RequestMapping("/birth")
public class BirthApiController {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private BirthRegistrationService birthRegistrationService;

    @Autowired
    private ResponseInfoFactory responseInfoFactory;


    @Autowired
    public BirthApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @RequestMapping(value = "/registration/v1/_create", method = RequestMethod.POST)
    public ResponseEntity<BirthRegistrationResponse> birthRegistrationV1CreatePost(@Parameter(in = ParameterIn.DEFAULT, description = "Details for the new Birth Registration Application(s) + RequestInfo meta data.", required = true, schema = @Schema()) @Valid @RequestBody BirthRegistrationRequest body) {
        try {
            List<BirthRegistrationApplication> applications = birthRegistrationService.registerBtRequest(body);
            ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(body.getRequestInfo(), true);
            BirthRegistrationResponse response = BirthRegistrationResponse.builder().birthRegistrationApplications(applications).responseInfo(responseInfo).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return handledException(e);
        }
    }


    @RequestMapping(value = "/registration/v1/_search", method = RequestMethod.POST)
    public ResponseEntity<BirthRegistrationResponse> birthRegistrationV1SearchPost(@Parameter(in = ParameterIn.DEFAULT, description = "Parameter to carry Request metadata in the request body", schema = @Schema()) @RequestBody RequestInfoWrapper requestInfoWrapper, @Valid @ModelAttribute BirthApplicationSearchCriteria birthApplicationSearchCriteria) {
        try {
            List<BirthRegistrationApplication> applications = birthRegistrationService.searchBtApplications(requestInfoWrapper.getRequestInfo(), birthApplicationSearchCriteria);
            ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfoWrapper.getRequestInfo(), true);
            BirthRegistrationResponse response = BirthRegistrationResponse.builder().birthRegistrationApplications(applications).responseInfo(responseInfo).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return handledException(e);
        }
    }


    @RequestMapping(value = "/registration/v1/_update", method = RequestMethod.POST)
    public ResponseEntity<BirthRegistrationResponse> birthRegistrationV1UpdatePost(@Parameter(in = ParameterIn.DEFAULT, description = "Details for the new (s) + RequestInfo meta data.", required = true, schema = @Schema()) @Valid @RequestBody BirthRegistrationRequest body) {
        try {
            BirthRegistrationApplication application = birthRegistrationService.updateBtApplication(body);
            ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(body.getRequestInfo(), true);
            BirthRegistrationResponse response = BirthRegistrationResponse.builder().birthRegistrationApplications(Collections.singletonList(application)).responseInfo(responseInfo).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return handledException(e);
        }
    }


    private ResponseEntity<BirthRegistrationResponse> handledException(Exception e) {
        // Log the error
        Logger logger = LoggerFactory.getLogger(BirthApiController.class);
        logger.error("Error occurred while processing request", e);

        // Create error response
        ResponseInfo errorResponseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(null, false);
        BirthRegistrationResponse errorResponse = BirthRegistrationResponse.builder().responseInfo(errorResponseInfo).build();


        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e instanceof IllegalArgumentException) {

            status = HttpStatus.BAD_REQUEST;
        }

        // Return error response with appropriate status code
        return new ResponseEntity<>(errorResponse, status);
    }

}
