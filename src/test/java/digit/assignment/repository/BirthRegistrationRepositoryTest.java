package digit.assignment.repository;

import digit.assignment.helper.BirthApplicationSearchCriteriaTestBuilder;
import digit.assignment.repository.querybuilder.BirthRegistrationQueryBuilder;
import digit.assignment.repository.rowmapper.BirthRegistrationRowMapper;
import digit.assignment.web.models.BirthApplicationSearchCriteria;
import digit.assignment.web.models.BirthRegistrationApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BirthRegistrationRepositoryTest {


    @Mock
    private BirthRegistrationQueryBuilder queryBuilder;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private BirthRegistrationRowMapper rowMapper;

    @InjectMocks
    private BirthRegistrationRepository repository;



    @Test
    @DisplayName("")
    void shouldSearchForProvidedCriteria() {
        // Arrange
        BirthApplicationSearchCriteria searchCriteria = BirthApplicationSearchCriteriaTestBuilder.builder().build();
        List<BirthRegistrationApplication> expectedList = new ArrayList<>();
        when(queryBuilder.getBirthApplicationSearchQuery(searchCriteria, new ArrayList<>())).thenReturn("SELECT * FROM applications WHERE ...");
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BirthApplicationRowMapper.class))).thenReturn(expectedList);

        // Act
        List<BirthRegistrationApplication> actualList = repository.getApplications(searchCriteria);

        // Assert
        assertEquals(expectedList, actualList);
        verify(queryBuilder, times(1)).getBirthApplicationSearchQuery(searchCriteria, new ArrayList<>());
//        verify(jdbcTemplate, times(1)).query(anyString(), any(Object[].class), any(BirthRegistrationRowMapper.class));
    }

    @Test
    @DisplayName("")
    void testGetApplications_WithNullCriteria() {
        // Arrange
        BirthApplicationSearchCriteria searchCriteria = null;
        List<BirthRegistrationApplication> expectedList = new ArrayList<>();
        when(queryBuilder.getBirthApplicationSearchQuery(null, new ArrayList<>())).thenReturn("SELECT * FROM applications WHERE ...");
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BirthRegistrationRowMapper.class))).thenReturn(expectedList);

        // Act
        List<BirthRegistrationApplication> actualList = repository.getApplications(searchCriteria);

        // Assert
        assertEquals(expectedList, actualList);
        verify(queryBuilder, times(1)).getBirthApplicationSearchQuery(null, new ArrayList<>());
//        verify(jdbcTemplate, times(1)).query(anyString(), any(Object[].class), any(BirthApplicationRowMapper.class));
    }

}
