package digit.assignment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import digit.assignment.config.Configuration;
import digit.assignment.helper.BirthRegistrationApplicationTestBuilder;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.repository.ServiceRequestRepository;
import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.BirthRegistrationRequest;
import digit.models.coremodels.ProcessInstance;
import digit.models.coremodels.ProcessInstanceRequest;
import digit.models.coremodels.ProcessInstanceResponse;
import digit.models.coremodels.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


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

    @BeforeEach
    void setUp() {

        lenient().when(config.getWfHost()).thenReturn("localhost://domain");
        lenient().when(config.getWfTransitionPath()).thenReturn("/path");
    }

    @Test
    @DisplayName("should update workflow status")
    public void shouldUpdateWorkFlowStatus() {


        BirthRegistrationApplication application = BirthRegistrationApplicationTestBuilder.builder().withApplication().withWorkFlow().build();
        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().withRequestInfo().withBirthApplication(Collections.singletonList(application)).build();
        when(repository.fetchResult(Mockito.any(), Mockito.any())).thenReturn(new Object());
        when(mapper.convertValue(Mockito.any(), eq(ProcessInstanceResponse.class))).thenReturn(ProcessInstanceResponse.builder().processInstances(Collections.singletonList(
                ProcessInstance.builder().state(State.builder().uuid("uuid").build()).build()
        )).build());
        workflowService.updateWorkflowStatus(request);

        assertNotNull(request);
        assertNotNull(request.getBirthRegistrationApplications());
        assertNotNull(request.getBirthRegistrationApplications().get(0).getWorkflow());
        assertEquals(request.getBirthRegistrationApplications().get(0).getWorkflow().getStatus(),"status");

    }


    @Test
    @DisplayName("should call work flow")
    public void shouldCallWorkFlow() {

        when(repository.fetchResult(Mockito.any(), Mockito.any())).thenReturn(new Object());
        when(mapper.convertValue(Mockito.any(), eq(ProcessInstanceResponse.class))).thenReturn(ProcessInstanceResponse.builder().processInstances(Collections.singletonList(
                ProcessInstance.builder().state(State.builder().uuid("uuid").build()).build()
        )).build());
       State response= workflowService.callWorkFlow(new ProcessInstanceRequest());

       assertNotNull(response);
       assertEquals(response.getUuid(),"uuid");

    }

}
