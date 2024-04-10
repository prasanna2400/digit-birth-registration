package digit.assignment.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import digit.assignment.TestConfiguration;
import digit.assignment.config.Configuration;
import digit.assignment.helper.BirthRegistrationApplicationTestBuilder;
import digit.assignment.helper.BirthRegistrationRequestTestBuilder;
import digit.assignment.kafka.Producer;
import digit.assignment.service.BirthRegistrationService;
import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.BirthRegistrationRequest;
import digit.assignment.web.models.BirthRegistrationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * API tests for BirthApiController
 */
@ExtendWith(MockitoExtension.class)

@WebMvcTest(BirthApiController.class)
@Import(TestConfiguration.class)
public class BirthApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @MockBean
    private BirthRegistrationService birthRegistrationService;

    @MockBean
    private Producer producer;

    @MockBean
    private Configuration configuration;


    @BeforeEach
    void setUp() {
        when(configuration.getSaveTopic()).thenReturn("save-bt-application");
        when(configuration.getUpdateTopic()).thenReturn("update-bt-application");
    }


    @Test
    @DisplayName("should return birth registration response for create")
    void shouldReturnBirthRegistrationResponseForCreate() throws Exception {
        BirthRegistrationRequest request = BirthRegistrationRequestTestBuilder.builder().build();
        List<BirthRegistrationApplication> responseApplication = Collections.singletonList(BirthRegistrationApplicationTestBuilder.builder().build());
        when(birthRegistrationService.registerBtRequest(any(BirthRegistrationRequest.class))).thenReturn(responseApplication);

        MvcResult result = mockMvc.perform(post("/registration/v1/_create").contentType(MediaType
                        .APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted()).andReturn();

        BirthRegistrationResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), BirthRegistrationResponse.class);
        assertNotNull(response.getBirthRegistrationApplications());
        verify(birthRegistrationService, times(1)).registerBtRequest(any(BirthRegistrationRequest.class));
    }


    @Test
    public void birthRegistrationV1CreatePostSuccess() throws Exception {
        mockMvc.perform(post("/birth/registration/v1/_create").contentType(MediaType
                        .APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void birthRegistrationV1CreatePostFailure() throws Exception {
        mockMvc.perform(post("/birth/registration/v1/_create").contentType(MediaType
                        .APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void birthRegistrationV1SearchPostSuccess() throws Exception {
        mockMvc.perform(post("/birth/registration/v1/_search").contentType(MediaType
                        .APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void birthRegistrationV1SearchPostFailure() throws Exception {
        mockMvc.perform(post("/birth/registration/v1/_search").contentType(MediaType
                        .APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void birthRegistrationV1UpdatePostSuccess() throws Exception {
        mockMvc.perform(post("/birth/registration/v1/_update").contentType(MediaType
                        .APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void birthRegistrationV1UpdatePostFailure() throws Exception {
        mockMvc.perform(post("/birth/registration/v1/_update").contentType(MediaType
                        .APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

}
