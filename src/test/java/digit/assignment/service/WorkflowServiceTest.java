package digit.assignment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import digit.assignment.config.Configuration;
import digit.assignment.repository.ServiceRequestRepository;
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
    @DisplayName("")
    public void testUpdateWorkflowStatus() {
        // Setup
//        BirthRegistrationRequest birthRegistrationRequest = new BirthRegistrationRequest();
//        BirthRegistrationApplication application = new BirthRegistrationApplication();
//        application.setWorkflow(new Workflow());
//        birthRegistrationRequest.getBirthRegistrationApplications().add(application);
//
//        // Mock getProcessInstanceForBTR response
//        ProcessInstance processInstance = new ProcessInstance();
//
//        // Mock callWorkFlow method
//        when(workflowService.callWorkFlow(any())).thenReturn(new State());
//
//        // Invoke
//        assertDoesNotThrow(() -> workflowService.updateWorkflowStatus(birthRegistrationRequest));
//
//        // Verify
//        verify(workflowService, times(1)).callWorkFlow(any());
    }


    @Test
    @DisplayName("")
    public void testCallWorkFlow() {
        // Setup
//        ProcessInstanceRequest request = new ProcessInstanceRequest();
//        ProcessInstanceResponse response = new ProcessInstanceResponse();
//        response.setProcessInstances(Collections.singletonList(new ProcessInstance()));
//        when(repository.fetchResult(any(), any())).thenReturn(response);
//
//        // Invoke
//        State state = workflowService.callWorkFlow(request);
//
//        // Verify
//        assertNotNull(state);
    }


    @Test
    @DisplayName("")
    public void testGetProcessInstanceForBTR() {
        // Setup
//        BirthRegistrationApplication application = new BirthRegistrationApplication();
//        application.setTenantId("tenantId");
//        application.setApplicationNumber("appNumber");
//        application.setWorkflow(new Workflow());
//
//        // Mock repository response
//        when(repository.fetchResult(any(), any())).thenReturn(new Object());
//
//        // Invoke
//
//        // Verify
    }

    @Test
    @DisplayName("")
    public void testGetCurrentWorkflow() {
        // Setup
//        RequestInfo requestInfo = new RequestInfo();
//        String tenantId = "tenantId";
//        String businessId = "businessId";
//
//        // Mock repository response
//        when(repository.fetchResult(any(), any())).thenReturn(new Object());
//
//        // Invoke
//        ProcessInstance processInstance = workflowService.getCurrentWorkflow(requestInfo, tenantId, businessId);
//
//        // Verify
//        assertNotNull(processInstance);
    }

    @Test
    @DisplayName("")
    public void testGetProcessInstanceForBirthRegistrationPayment() {
        // Setup
//        BirthRegistrationRequest updateRequest = new BirthRegistrationRequest();
//        BirthRegistrationApplication application = new BirthRegistrationApplication();
//        application.setTenantId("tenantId");
//        application.setApplicationNumber("appNumber");
//        updateRequest.setRequestInfo(new RequestInfo());
//        updateRequest.getBirthRegistrationApplications().add(application);
//
//        // Invoke
//        ProcessInstanceRequest processInstanceRequest = workflowService.getProcessInstanceForBirthRegistrationPayment(updateRequest);
//
//        // Verify
//        assertNotNull(processInstanceRequest);
//        assertEquals(1, processInstanceRequest.getProcessInstances().size());
    }
}
