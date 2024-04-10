package digit.assignment.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import digit.assignment.config.Configuration;
import digit.assignment.helper.RequestInfoTestBuilder;
import digit.assignment.repository.ServiceRequestRepository;
import digit.models.coremodels.IdGenerationResponse;
import digit.models.coremodels.IdResponse;
import org.egov.tracer.model.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class IdgenUtilTest {


    @InjectMocks
    private IdgenUtil idgenUtil;

    @Mock
    private ObjectMapper mapper;

    @Mock
    private ServiceRequestRepository restRepo;

    @Mock
    private Configuration configs;


    @BeforeEach
    void setUp() {

        lenient().when(configs.getIdGenHost()).thenReturn("localhost://domain");
        lenient().when(configs.getIdGenPath()).thenReturn("/path");
    }


    @Test
    @DisplayName("should provide list of ids")
    void shouldProvideListOfIds() {
        List<IdResponse> idList = new ArrayList<>();
        IdResponse idResponse = new IdResponse("ID_TEST_001");
        idList.add(idResponse);


        lenient().when(restRepo.fetchResult(Mockito.any(), Mockito.any())).thenReturn(Object.class);
        lenient().when(mapper.convertValue(Mockito.any(), eq(IdGenerationResponse.class))).thenReturn(IdGenerationResponse.builder().idResponses(idList).build());


        List<String> idListResponse = idgenUtil.getIdList(RequestInfoTestBuilder.builder().withCompleteRequestInfo().build(), "default", "btr", "btr.format", 1);

        assertNotNull(idListResponse);
        assertEquals(idListResponse.size(), 1);
        assertEquals(idListResponse.get(0), "ID_TEST_001");

    }

    @Test
    @DisplayName("should throw idgen error")
    void shouldThrowIdgenError() {
        List<IdResponse> idList = new ArrayList<>();

        lenient().when(restRepo.fetchResult(Mockito.any(), Mockito.any())).thenReturn(Object.class);
        lenient().when(mapper.convertValue(Mockito.any(), eq(IdGenerationResponse.class))).thenReturn(IdGenerationResponse.builder().idResponses(idList).build());

        CustomException exception = assertThrows(CustomException.class, () -> idgenUtil.getIdList(RequestInfoTestBuilder.builder().withCompleteRequestInfo().build(), "default", "btr", "btr.format", 1));

        assertEquals("IDGEN ERROR", exception.getCode());
        assertEquals("No ids returned from idgen Service", exception.getMessage());

    }
}
