package digit.assignment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import digit.assignment.config.Configuration;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.repository.ServiceRequestRepository;
import digit.assignment.web.models.BirthRegistrationRequest;
import digit.models.coremodels.ProcessInstanceRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class WorkflowServiceTest {


    @Mock
    private ObjectMapper mapper;

    @Mock
    private ServiceRequestRepository repository;

    @Mock
    private Configuration config;

    @InjectMocks
    private WorkflowService workflowService;


    @Test
    @DisplayName("should update workflow status")
    public void shouldUpdateWorkFlowStatus() {

        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().build();

        workflowService.updateWorkflowStatus(request);

    }


    @Test
    @DisplayName("should call work flow")
    public void shouldCallWorkFlowStatus() {


        workflowService.callWorkFlow(new ProcessInstanceRequest());

    }
    
}
