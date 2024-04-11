package digit.assignment.service;

import digit.assignment.config.Configuration;
import digit.assignment.helper.BirthRegistrationApplicationTestBuilder;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.helper.UserDetailResponseTestBuilder;
import digit.assignment.util.UserUtil;
import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.BirthRegistrationRequest;
import digit.assignment.web.models.UserDetailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserUtil userUtils;

    @Mock
    private Configuration config;

    @InjectMocks
    @Spy
    private UserService userService;


    @BeforeEach
    void setUp() {

        lenient().when(config.getUserHost()).thenReturn("localhost://domain");
        lenient().when(config.getUserSearchEndpoint()).thenReturn("/path");
    }


    @Test
    @DisplayName("should enrich father and mother with user uuid")
    public void shouldEnrichFatherUserWithUUID() {

        BirthRegistrationApplication application = BirthRegistrationApplicationTestBuilder.builder().withApplication().withFatherApplication().withMotherApplication().build();

        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication(Collections.singletonList(application)).build();
        when(userUtils.getStateLevelTenant(Mockito.any())).thenReturn("default");
        when(userUtils.userCall(Mockito.any(), Mockito.any())).thenReturn(UserDetailResponseTestBuilder.builder().withUserDetailResponse().build());

        userService.callUserService(request);


        assertNotNull(request);
        assertNotNull(request.getBirthRegistrationApplications().get(0).getFather());
        assertNotNull(request.getBirthRegistrationApplications().get(0).getMother());
        assertEquals(request.getBirthRegistrationApplications().get(0).getFather().getUuid(), "some-uuid");
        assertEquals(request.getBirthRegistrationApplications().get(0).getMother().getUuid(), "some-uuid");


    }


    @Test
    @DisplayName("should update father and mother")
    public void shouldUpdateTheUser() {
        BirthRegistrationApplication application = BirthRegistrationApplicationTestBuilder.builder().withApplication().withFatherApplication().withMotherApplication().build();

        application.getFather().setUuid(null);
        application.getMother().setUuid(null);
        application.getFather().setMobileNumber("9999999999");
        application.getMother().setUuid("8888888888");

        when(userUtils.getStateLevelTenant(Mockito.any())).thenReturn("default");

        when(userUtils.userCall(Mockito.any(), Mockito.any())).thenReturn(UserDetailResponseTestBuilder.builder().withUserDetailResponse().build());
        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication(Collections.singletonList(application)).build();

        userService.callUserService(request);
        assertNotNull(request);
        assertNotNull(request.getBirthRegistrationApplications().get(0).getFather());
        assertNotNull(request.getBirthRegistrationApplications().get(0).getMother());
        assertEquals(request.getBirthRegistrationApplications().get(0).getFather().getUuid(), "some-uuid");
        assertEquals(request.getBirthRegistrationApplications().get(0).getMother().getUuid(), "some-uuid");


    }


    @Test
    @DisplayName("should search for a user")
    public void shouldSearchUser() {
        when(userUtils.userCall(Mockito.any(), Mockito.any())).thenReturn(UserDetailResponseTestBuilder.builder().withUserDetailResponse().build());

        UserDetailResponse response = userService.searchUser("tenantID", "accountId", "userName");

        assertNotNull(response);

    }

}
