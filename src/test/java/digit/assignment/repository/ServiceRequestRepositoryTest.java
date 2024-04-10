package digit.assignment.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)

public class ServiceRequestRepositoryTest {

    @InjectMocks
    private ServiceRequestRepository serviceRequestRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock

    private RestTemplate restTemplate;


    @Test
    @DisplayName("")
    void shouldFetchResult(){
        StringBuilder sb = new StringBuilder();
        Object object= new Object();
        serviceRequestRepository.fetchResult(sb,object);
    }


}
