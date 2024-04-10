package digit.assignment.validator;

import digit.assignment.helper.BirthRegistrationApplicationTestBuilder;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.repository.BirthRegistrationRepository;
import digit.assignment.validators.BirthRegistrationValidator;
import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.BirthRegistrationRequest;
import org.egov.tracer.model.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

public class BirthRegistrationValidatorTest {

    @Mock
    private BirthRegistrationRepository repository;

    @InjectMocks
    private BirthRegistrationValidator validator;


    @Test
    @DisplayName("should validate birth application")
    public void shouldValidateBirthApplication() {
        // pass tenant id
        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication().build();
        assertDoesNotThrow(() -> validator.validateBirthApplication(request));

    }

    @Test
    @DisplayName("should not validate birth application")
    public void shouldNotValidateBirthApplication() {
        // do not pass tenant id

        BirthRegistrationApplication application = BirthRegistrationApplicationTestBuilder.builder().withApplication().withTenantId(null).build();
        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication(Collections.singletonList(application)).build();
        CustomException exception = assertThrows(CustomException.class, () -> validator.validateBirthApplication(request));

        assertEquals("EG_BT_APP_ERR", exception.getCode());
        assertEquals("tenantId is mandatory for creating birth registration applications", exception.getMessage());


    }

    @Test
    @DisplayName("should validate application existence")
    public void validateApplicationExistence() {

        //check application id here
        BirthRegistrationApplication application = BirthRegistrationApplicationTestBuilder.builder().withApplication().build();
        when(repository.getApplications(Mockito.any())).thenReturn(Collections.singletonList(application));

        BirthRegistrationApplication existingApplication = validator.validateApplicationExistence(application);

        assertNotNull(existingApplication);
        assertEquals(existingApplication.getId(), application.getId());
        assertEquals(existingApplication.getApplicationNumber(), application.getApplicationNumber());

    }


}
