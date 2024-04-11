package digit.assignment.helper;

import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.User;
import digit.assignment.web.models.UserDetailResponse;

import java.util.ArrayList;
import java.util.List;

public class UserDetailResponseTestBuilder {

    ArrayList<User> applications = new ArrayList<>();


    private UserDetailResponse.UserDetailResponseBuilder builder;


    public UserDetailResponseTestBuilder() {
        this.builder = UserDetailResponse.builder();
    }

    public static UserDetailResponseTestBuilder builder() {
        return new UserDetailResponseTestBuilder();
    }

    public UserDetailResponse build() {
        return this.builder.build();
    }


    public UserDetailResponseTestBuilder withUserDetailResponse() {
        applications.add(UserTestBuilder.builder().withCompleteUserInfo().build());
        this.builder.user(applications);
        return this;
    }

    public UserDetailResponseTestBuilder withUserDetailResponse(List<User> application) {
        this.builder.user(application);
        return this;
    }

}
