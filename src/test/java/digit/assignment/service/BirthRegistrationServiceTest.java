package digit.assignment.service;

import digit.assignment.enrichment.BirthRegistrationEnrichment;
import digit.assignment.kafka.Producer;
import digit.assignment.repository.BirthRegistrationRepository;
import digit.assignment.validators.BirthRegistrationValidator;
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

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    @DisplayName("")
    public void testRegisterBtRequest() {
        // Setup
//        BirthRegistrationRequest request = new BirthRegistrationRequest();
//        when(birthRegistrationRepository.getApplications(any())).thenReturn(Collections.emptyList());
//
//        // Invoke
//        List<BirthRegistrationApplication> result = birthRegistrationService.registerBtRequest(request);
//
//        // Verify
//        assertEquals(0, result.size());
//        verify(validator, times(1)).validateBirthApplication(request);
//        verify(enrichmentUtil, times(1)).enrichBirthApplication(request);
//        verify(userService, times(1)).callUserService(request);
//        verify(producer, times(1)).push(anyString(), eq(request));
    }

    @Test
    @DisplayName("")
    public void testSearchBtApplications() {
//        // Setup
//        RequestInfo requestInfo = new RequestInfo();
//        BirthApplicationSearchCriteria searchCriteria = new BirthApplicationSearchCriteria();
//        when(birthRegistrationRepository.getApplications(any())).thenReturn(Collections.emptyList());
//
//        // Invoke
//        List<BirthRegistrationApplication> result = birthRegistrationService.searchBtApplications(requestInfo, searchCriteria);
//
//        // Verify
//        assertEquals(0, result.size());
//        verify(birthRegistrationRepository, times(1)).getApplications(searchCriteria);
    }

    @Test
    @DisplayName("")
    public void testUpdateBtApplication() {
        // Setup
//        BirthRegistrationRequest request = new BirthRegistrationRequest();
//        BirthRegistrationApplication application = new BirthRegistrationApplication();
//        request.setBirthRegistrationApplications(Collections.singletonList(application));
//
//        // Invoke
//        BirthRegistrationApplication result = birthRegistrationService.updateBtApplication(request);
//
//        // Verify
//        assertEquals(application, result);
//        verify(validator, times(1)).validateApplicationExistence(application);
//        verify(enrichmentUtil, times(1)).enrichBirthApplicationUponUpdate(request);
//        verify(producer, times(1)).push(anyString(), eq(request));
    }
}
