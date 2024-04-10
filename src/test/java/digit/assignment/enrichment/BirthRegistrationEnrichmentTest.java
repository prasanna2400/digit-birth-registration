package digit.assignment.enrichment;


import digit.assignment.helper.BirthRegistrationApplicationTestBuilder;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.helper.UserDetailResponseTestBuilder;
import digit.assignment.service.UserService;
import digit.assignment.util.IdgenUtil;
import digit.assignment.util.UserUtil;
import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.BirthRegistrationRequest;
import org.egov.common.contract.request.RequestInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
public class BirthRegistrationEnrichmentTest {

    @InjectMocks
    BirthRegistrationEnrichment birthRegistrationEnrichment;

    @Mock
    IdgenUtil idgenUtil;

    @Mock
    UserService userService;

    @Mock
    UserUtil userUtil;


    @BeforeEach
    void setUp() throws Exception {
        List<String> idList = new ArrayList<>();
        idList.add("ID_TEST_001");
        lenient().when(idgenUtil.getIdList(any(RequestInfo.class), any(String.class), any(), any(), anyInt())).thenReturn(idList);

    }


    @Test
    @DisplayName("should enrich create birth registration application")
    void shouldEnrichCreateBirthRegistrationRequestApplication() {
        BirthRegistrationRequest birthRegistrationRequest = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication().build();

        birthRegistrationEnrichment.enrichBirthApplication(birthRegistrationRequest);

        assertNotNull(birthRegistrationRequest);
        BirthRegistrationApplication application = birthRegistrationRequest.getBirthRegistrationApplications().get(0);

        assertNotNull(application);
        assertNotNull(application.getId());
        assertNotNull(application.getAddress().getId());
        assertNotNull(application.getAddress().getApplicantAddress().getId());
        assertEquals("ID_TEST_001", application.getApplicationNumber());


    }

    @Test
    @DisplayName("should enrich update birth registration application")
    void shouldEnrichBirthApplicationUponUpdate() {
        BirthRegistrationRequest birthRegistrationRequest = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication().build();

        birthRegistrationEnrichment.enrichBirthApplicationUponUpdate(birthRegistrationRequest);

        assertNotNull(birthRegistrationRequest);
        BirthRegistrationApplication application = birthRegistrationRequest.getBirthRegistrationApplications().get(0);
        assertNotNull(application.getAuditDetails());
        assertEquals(application.getAuditDetails().getLastModifiedBy(), "some-uuid");
        assertNotEquals(application.getAuditDetails().getLastModifiedTime(), 1L);
    }


    @Test
    @DisplayName("should enrich father application on search")
    void shouldEnrichFatherApplicationOnSearch() {
        BirthRegistrationApplication birthRegistrationApplication = BirthRegistrationApplicationTestBuilder.builder().withApplication().withFatherApplication().build();

        lenient().when(userUtil.getStateLevelTenant(any())).thenReturn("default");
        lenient().when(userService.searchUser(any(), any(), any())).thenReturn(UserDetailResponseTestBuilder.builder().withUserDetailResponse().build());
        birthRegistrationEnrichment.enrichFatherApplicantOnSearch(birthRegistrationApplication);

        assertNotNull(birthRegistrationApplication);
        assertNotNull(birthRegistrationApplication.getFather());
        assertEquals(birthRegistrationApplication.getFather().getUuid(), "some-uuid");
        assertEquals(birthRegistrationApplication.getFather().getName(), "some-name");


    }

    @Test
    @DisplayName("should enrich mother application on search")
    void shouldEnrichMotherApplicationOnSearch() {
        BirthRegistrationApplication birthRegistrationApplication = BirthRegistrationApplicationTestBuilder.builder().withApplication().withMotherApplication().build();

        lenient().when(userUtil.getStateLevelTenant(any())).thenReturn("default");
        lenient().when(userService.searchUser(any(), any(), any())).thenReturn(UserDetailResponseTestBuilder.builder().withUserDetailResponse().build());
        birthRegistrationEnrichment.enrichMotherApplicantOnSearch(birthRegistrationApplication);

        assertNotNull(birthRegistrationApplication);
        assertNotNull(birthRegistrationApplication.getMother());
        assertEquals(birthRegistrationApplication.getMother().getUuid(), "some-uuid");
        assertEquals(birthRegistrationApplication.getMother().getName(), "some-name");

    }
}
