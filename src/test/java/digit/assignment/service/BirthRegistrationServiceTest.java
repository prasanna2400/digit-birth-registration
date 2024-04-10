package digit.assignment.service;

import digit.assignment.enrichment.BirthRegistrationEnrichment;
import digit.assignment.helper.BirthApplicationSearchCriteriaTestBuilder;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.helper.RequestInfoTestBuilder;
import digit.assignment.kafka.Producer;
import digit.assignment.repository.BirthRegistrationRepository;
import digit.assignment.validators.BirthRegistrationValidator;
import digit.assignment.web.models.BirthApplicationSearchCriteria;
import digit.assignment.web.models.BirthRegistrationRequest;
import org.egov.common.contract.request.RequestInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class BirthRegistrationServiceTest {


    @Mock
    private BirthRegistrationValidator validator;

    @Mock
    private BirthRegistrationEnrichment enrichmentUtil;

    @Mock
    private UserService userService;

    @Mock
    private BirthRegistrationRepository birthRegistrationRepository;

    @Mock
    private Producer producer;

    @InjectMocks
    private BirthRegistrationService birthRegistrationService;

    @Test
    @DisplayName("should call kafka topic if valid birth application found for create")
    public void shouldCallKafkaTopicRegister() {

        BirthRegistrationRequest request= BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication().build();

        birthRegistrationService.registerBtRequest(request);

    }

    @Test
    @DisplayName("should return Birth Application")
    public void shouldSearchOnSearchCriteria() {


        BirthApplicationSearchCriteria searchCriteria= BirthApplicationSearchCriteriaTestBuilder.builder().build();

        RequestInfo requestInfo= RequestInfoTestBuilder.builder().withCompleteRequestInfo().build();

        birthRegistrationService.searchBtApplications(requestInfo,searchCriteria);

    }

    @Test
    @DisplayName("should call kafka topic if valid birth application found for update")
    public void shouldCallKafkaTopicUpdate() {

        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication().build();

        birthRegistrationService.updateBtApplication(request);

    }
}
