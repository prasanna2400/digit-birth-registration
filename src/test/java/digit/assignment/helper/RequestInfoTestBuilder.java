package digit.assignment.helper;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.request.Role;
import org.egov.common.contract.request.User;

import java.util.ArrayList;
import java.util.List;

public class RequestInfoTestBuilder {

    private RequestInfo.RequestInfoBuilder builder = RequestInfo.builder();

    public RequestInfoTestBuilder() {
    }

    public static RequestInfoTestBuilder builder() {
        return new RequestInfoTestBuilder();
    }

    public RequestInfo build() {
        return this.builder.build();
    }

    public RequestInfoTestBuilder withCompleteRequestInfo() {
        Role role = Role.builder().id(123L).name("System Administrator").build();
        List<Role> roles = new ArrayList();
        roles.add(role);
        this.builder.userInfo(User.builder().userName("some-username").roles(roles).id(123L).name("some-name").type("EMPLOYEE").emailId("some-email-id").mobileNumber("9893212345").uuid("some-uuid").build()).action("create").apiId("some-api-id").authToken("some-auth-token").did("some-did").correlationId("some-correlation-id").key("some-key").msgId("some-msg-id").ts(System.currentTimeMillis()).ver("1");
        return this;
    }
}
