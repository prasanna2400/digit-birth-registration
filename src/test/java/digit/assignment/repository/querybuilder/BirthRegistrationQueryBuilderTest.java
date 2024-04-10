package digit.assignment.repository.querybuilder;


import digit.assignment.helper.BirthApplicationSearchCriteriaTestBuilder;
import digit.assignment.web.models.BirthApplicationSearchCriteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class BirthRegistrationQueryBuilderTest {

    @InjectMocks
    private BirthRegistrationQueryBuilder queryBuilder;


    @Test
    @DisplayName("")
    void shouldReturnBirthRegistrationApplicationSearchQuery() {
        BirthApplicationSearchCriteria searchCriteria = BirthApplicationSearchCriteriaTestBuilder.builder().build();

        queryBuilder.getBirthApplicationSearchQuery(searchCriteria, new ArrayList<>());
    }

}
