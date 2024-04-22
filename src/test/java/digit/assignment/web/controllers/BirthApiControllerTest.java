package digit.assignment.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import digit.assignment.TestConfiguration;
import digit.assignment.config.Configuration;
import digit.assignment.helper.BirthApplicationSearchCriteriaTestBuilder;
import digit.assignment.helper.BirthRegistrationApplicationTestBuilder;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.helper.RequestInfoTestBuilder;
import digit.assignment.kafka.Producer;
import digit.assignment.repository.BirthRegistrationRepository;
import digit.assignment.service.BirthRegistrationService;
import digit.assignment.web.models.BirthApplicationSearchCriteria;
import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.BirthRegistrationRequest;
import digit.assignment.web.models.BirthRegistrationResponse;
import org.egov.common.contract.request.RequestInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

/**
 * API tests for BirthApiController
 */
@ExtendWith(MockitoExtension.class)

@Import(TestConfiguration.class)
public class BirthApiControllerTest {


    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private BirthRegistrationService birthRegistrationService;

    @Mock
    private Producer producer;

    @Mock
    private Configuration configuration;

    @Mock
    private BirthRegistrationRepository birthRegistrationRepository;

    @InjectMocks
    private BirthApiController birthApiController;


    @Test
    @DisplayName("should return birth registration response for create")
    void shouldReturnBirthRegistrationResponseForCreate() throws Exception {
        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().build();
        List<BirthRegistrationApplication> responseApplication = Collections.singletonList(BirthRegistrationApplicationTestBuilder.builder().build());

        when(birthRegistrationService.registerBtRequest(any())).thenReturn(responseApplication);

        ResponseEntity<BirthRegistrationResponse> responseEntity = birthApiController.birthRegistrationV1CreatePost(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


    }


    @Test
    @DisplayName("should return birth registration response for search")
    void shouldReturnBirthRegistrationResponseForSearch() {
        
        BirthRegistrationApplication  searchApplication= BirthRegistrationApplicationTestBuilder.builder().withApplication().withFatherApplication().withMotherApplication().build();
        RequestInfo requestInfo=RequestInfoTestBuilder.builder().withCompleteRequestInfo().build();
        BirthApplicationSearchCriteria searchCriteria= BirthApplicationSearchCriteriaTestBuilder.builder().withSearchCriteria().build();
        lenient().when (birthRegistrationRepository.getApplications(any())).thenReturn(Collections.singletonList(searchApplication));
        List<BirthRegistrationApplication> birthRegistrationApplications = birthRegistrationService.searchBtApplications(requestInfo, searchCriteria);
        assertNotNull(birthRegistrationApplications);

    }


    @Test
    @DisplayName("should return birth registration response for update")

    public void birthRegistrationV1CreatePostFailure() throws Exception {
        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().build();
        List<BirthRegistrationApplication> responseApplication = Collections.singletonList(BirthRegistrationApplicationTestBuilder.builder().build());

        when(birthRegistrationService.registerBtRequest(any())).thenReturn(responseApplication);

        ResponseEntity<BirthRegistrationResponse> responseEntity = birthApiController.birthRegistrationV1UpdatePost(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }


}
