package digit.assignment.helper;

import digit.assignment.web.models.User;
import org.egov.common.contract.request.Role;


import java.util.ArrayList;
import java.util.List;

public class UserTestBuilder {

    private User.UserBuilder builder = User.builder();

    public UserTestBuilder() {
    }

    public static UserTestBuilder builder() {
        return new UserTestBuilder();
    }

    public User build() {
        return this.builder.build();
    }

    public UserTestBuilder withCompleteUserInfo() {
        Role role = Role.builder().id(123L).name("System Administrator").build();
        List<Role> roles = new ArrayList();
        roles.add(role);
        this.builder.userName("some-username").roles(roles).id(123).name("some-name").type("EMPLOYEE").emailId("some-email-id").mobileNumber("9893212345").uuid("some-uuid");
        return this;
    }
}
