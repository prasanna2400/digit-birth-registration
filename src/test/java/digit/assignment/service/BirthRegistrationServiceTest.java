package digit.assignment.service;

import digit.assignment.config.Configuration;
import digit.assignment.enrichment.BirthRegistrationEnrichment;
import digit.assignment.helper.BirthApplicationSearchCriteriaTestBuilder;
import digit.assignment.helper.BirthRegistrationApplicationTestBuilder;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.helper.RequestInfoTestBuilder;
import digit.assignment.kafka.Producer;
import digit.assignment.repository.BirthRegistrationRepository;
import digit.assignment.validators.BirthRegistrationValidator;
import digit.assignment.web.models.BirthApplicationSearchCriteria;
import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.BirthRegistrationRequest;
import org.egov.common.contract.request.RequestInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BirthRegistrationServiceTest {


    @Mock
    private BirthRegistrationValidator validator;
    @Mock
    private Configuration configuration;

    @Mock
    private BirthRegistrationEnrichment enrichmentUtil;

    @Mock
    private UserService userService;

    @Mock
    private BirthRegistrationRepository birthRegistrationRepository;

    @Mock
    private Producer producer;

    @Mock
    private WorkflowService workflowService;

    @InjectMocks
    private BirthRegistrationService birthRegistrationService;


    @Test
    @DisplayName("should call kafka topic if valid birth application found for create")
    public void shouldCallKafkaTopicRegister() {

        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication().build();

        List<BirthRegistrationApplication> application = birthRegistrationService.registerBtRequest(request);
        assertEquals(1, application.size());
        verify(producer, times(1)).push(eq("save-bt-application"), any());

    }

    @Test
    @DisplayName("should return Birth Application")
    public void shouldSearchOnSearchCriteria() {

        BirthApplicationSearchCriteria searchCriteria = BirthApplicationSearchCriteriaTestBuilder.builder().build();
        BirthRegistrationApplication application = BirthRegistrationApplicationTestBuilder.builder().withApplication().build();
        RequestInfo requestInfo = RequestInfoTestBuilder.builder().withCompleteRequestInfo().build();
        when(birthRegistrationRepository.getApplications(Mockito.any())).thenReturn(Collections.singletonList(application));
        List<BirthRegistrationApplication> applications = birthRegistrationService.searchBtApplications(requestInfo, searchCriteria);
        assertNotNull(applications);
        assertEquals(1, applications.size());
    }

    @Test
    @DisplayName("should call kafka topic if valid birth application found for update")
    public void shouldCallKafkaTopicUpdate() {

        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication().build();
        when(validator.validateApplicationExistence(Mockito.any())).thenReturn(request.getBirthRegistrationApplications().get(0));
        BirthRegistrationApplication application = birthRegistrationService.updateBtApplication(request);

        assertNotNull(application);
        verify(producer, times(1)).push(eq("update-bt-application"), any());

    }
}
