package digit.assignment.validator;

import digit.assignment.helper.BirthRegistrationApplicationTestBuilder;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.repository.BirthRegistrationRepository;
import digit.assignment.validators.BirthRegistrationValidator;
import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.BirthRegistrationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)

public class BirthRegistrationValidatorTest {

    @Mock
    private BirthRegistrationRepository repository;

    @InjectMocks
    private BirthRegistrationValidator validator;


    @Test
    @DisplayName("should validate birth application")
    public void validateBirthApplication() {
        //dont pass tenant id and catch the exception
        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication().build();
        validator.validateBirthApplication(request);


    }

    @Test
    @DisplayName("should validate application existence")
    public void validateApplicationExistence() {


        //check application id here
        BirthRegistrationApplication  application= BirthRegistrationApplicationTestBuilder.builder().withApplication().build();

        BirthRegistrationApplication existingApplication = validator.validateApplicationExistence(application);


    }


}
