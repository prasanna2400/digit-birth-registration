package digit.assignment.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import digit.models.coremodels.Action;
import digit.models.coremodels.CreateUserRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

/**
 * Representation of a address. Indiavidual APIs may choose to extend from this using allOf if more details needed to be added in their case.
 */
@Schema(description = "Representation of a address. Indiavidual APIs may choose to extend from this using allOf if more details needed to be added in their case. ")
@Validated
@javax.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2024-04-02T19:03:47.640013960+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @JsonProperty("registrationId")
    private String registrationId = null;

    @JsonProperty("tenantId")
    private String tenantId = null;

    @JsonProperty("doorNo")
    @Size(min = 2, max = 64)
    private String doorNo = null;

    @JsonProperty("latitude")
    private Double latitude = null;

    @JsonProperty("longitude")
    private Double longitude = null;

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("addressNumber")
    private String addressNumber = null;

    @JsonProperty("addressLine1")
    private String addressLine1 = null;

    @JsonProperty("addressLine2")
    private String addressLine2 = null;

    @JsonProperty("landmark")
    private String landmark = null;

    @JsonProperty("city")
    private String city = null;

    @JsonProperty("pincode")
    private String pincode = null;

    @JsonProperty("detail")
    private String detail = null;

    @JsonProperty("street")
    private String street = null;



}
