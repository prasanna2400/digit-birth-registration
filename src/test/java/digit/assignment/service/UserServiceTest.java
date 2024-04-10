package digit.assignment.service;

import digit.assignment.config.Configuration;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.util.UserUtil;
import digit.assignment.web.models.BirthRegistrationRequest;
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



    @Test
    @DisplayName("should enrich father and mother with user uuid")
    public void shouldEnrichFatherUserWithUUID() {

        BirthRegistrationRequest request= BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication().build();

        userService.callUserService(request);

    }


    @Test
    @DisplayName("should upsert father and mother")
    public void shouldUpsertTheUser() {

        BirthRegistrationRequest request= BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication().build();

        userService.callUserService(request);

    }





    @Test
    @DisplayName("should search for a user")
    public void shouldSearchUser() {

        userService.searchUser("tenantID","accountId","userName");

    }

}
