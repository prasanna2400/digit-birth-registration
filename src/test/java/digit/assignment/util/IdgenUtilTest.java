package digit.assignment.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import digit.assignment.config.Configuration;
import digit.assignment.helper.RequestInfoTestBuilder;
import digit.assignment.repository.ServiceRequestRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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


    @Test
    @DisplayName("should provide list of ids")
    void shouldProvideListOfIds(){


        List<String> idList = idgenUtil.getIdList(RequestInfoTestBuilder.builder().withCompleteRequestInfo().build(), "", "", "", 1);
    }
}
