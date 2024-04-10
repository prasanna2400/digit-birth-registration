package digit.assignment.service;

import digit.assignment.config.Configuration;
import digit.assignment.util.UserUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserUtil userUtils;

    @Mock
    private Configuration config;

    @InjectMocks
    private UserService userService;

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    @DisplayName("")
    public void testCallUserService() {
        // Setup
//        BirthRegistrationRequest request = new BirthRegistrationRequest();
//        BirthRegistrationApplication father = new BirthRegistrationApplication();
//        father.setId("1");
//        father.setFather(new User());
//        request.getBirthRegistrationApplications().add(father);
//
//        // Mock searchUser response
//        UserDetailResponse userDetailResponse = new UserDetailResponse();
//        when(userUtils.userCall(any(), any())).thenReturn(userDetailResponse);
//
//        // Invoke
//        userService.callUserService(request);
//
//        // Verify
//        verify(userUtils, times(2)).userCall(any(), any());
    }


    @Test
    @DisplayName("")
    public void testCreateFatherUser() {
        // Setup
//        BirthRegistrationApplication application = new BirthRegistrationApplication();
//        application.setFather(new User());
//
//        // Invoke
//        User user = userService.createFatherUser(application);
//
//        // Verify
//        assertNotNull(user);
    }


    @Test
    @DisplayName("")
    public void testCreateMotherUser() {
        // Setup
//        BirthRegistrationApplication application = new BirthRegistrationApplication();
//        application.setMother(new User());
//
//        // Invoke
//        User user = userService.createMotherUser(application);
//
//        // Verify
//        assertNotNull(user);
    }


    @Test
    @DisplayName("")
    public void testUpsertUser() {
        // Setup
//        User user = new User();
//        RequestInfo requestInfo = new RequestInfo();
//
//        // Mock userCall response
//        UserDetailResponse userDetailResponse = new UserDetailResponse();
//        when(userUtils.userCall(any(), any())).thenReturn(userDetailResponse);
//
//        // Invoke
//        String accountId = userService.upsertUser(user, requestInfo);
//
//        // Verify
//        assertEquals(1, userDetailResponse.getUser().size());
    }


    @Test
    @DisplayName("")
    public void testEnrichUser() {
        // Setup
//        BirthRegistrationApplication application = new BirthRegistrationApplication();
//        application.setFather(new User());
//        application.setMother(new User());
//
//        // Mock searchUser response
//        UserDetailResponse userDetailResponse = new UserDetailResponse();
//        when(userUtils.userCall(any(), any())).thenReturn(userDetailResponse);
//
//        // Invoke
//        assertDoesNotThrow(() -> userService.enrichUser(application, new RequestInfo()));
    }


    @Test
    @DisplayName("")
    public void testCreateUser() {
        // Setup
//        RequestInfo requestInfo = new RequestInfo();
//        User user = new User();
//
//        // Mock userCall response
//        UserDetailResponse userDetailResponse = new UserDetailResponse();
//        when(userUtils.userCall(any(), any())).thenReturn(userDetailResponse);
//
//        // Invoke
//        org.egov.common.contract.request.User createdUser = userService.createUser(requestInfo, "tenantId", user);
//
//        // Verify
//        assertEquals(1, userDetailResponse.getUser().size());
    }


    @Test
    @DisplayName("")
    public void testUpdateUser() {
        // Setup
//        User user = new User();
//        org.egov.common.contract.request.User userFromSearch = new org.egov.common.contract.request.User();
//        RequestInfo requestInfo = new RequestInfo();
//
//        // Mock userCall response
//        UserDetailResponse userDetailResponse = new UserDetailResponse();
//        when(userUtils.userCall(any(), any())).thenReturn(userDetailResponse);
//
//        // Invoke
//        org.egov.common.contract.request.User updatedUser = userService.updateUser(requestInfo, user, userFromSearch);
//
//        // Verify
//        assertEquals(1, userDetailResponse.getUser().size());
    }


    @Test
    @DisplayName("")
    public void testSearchUser() {
//        // Setup
//        UserDetailResponse userDetailResponse = new UserDetailResponse();
//        when(userUtils.userCall(any(), any())).thenReturn(userDetailResponse);
//
//        // Invoke
//        UserDetailResponse result = userService.searchUser("stateLevelTenant", "accountId", "userName");
//
//        // Verify
//        assertEquals(userDetailResponse, result);
    }
}
