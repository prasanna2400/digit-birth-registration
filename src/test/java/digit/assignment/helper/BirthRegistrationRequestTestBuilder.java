package digit.assignment.helper;

import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.BirthRegistrationRequest;

import java.util.ArrayList;
import java.util.List;

public class BirthRegistrationRequestTestBuilder {

    ArrayList<BirthRegistrationApplication> applications = new ArrayList<>();

    private BirthRegistrationRequest.BirthRegistrationRequestBuilder builder;

    public BirthRegistrationRequestTestBuilder(){
        this.builder=BirthRegistrationRequest.builder();
    }

    public static BirthRegistrationRequestTestBuilder builder(){
        return new BirthRegistrationRequestTestBuilder();
    }

    public BirthRegistrationRequest build(){
        return this.builder.build();
    }



    public BirthRegistrationRequestTestBuilder withRequestInfo() {
        this.builder.requestInfo(RequestInfoTestBuilder.builder().withCompleteRequestInfo().build());
        return this;
    }


    public BirthRegistrationRequestTestBuilder withBirthApplication() {
        applications.add(BirthRegistrationApplicationTestBuilder.builder().withApplication().build());
        this.builder.birthRegistrationApplications(applications);
        return this;
    }

    public BirthRegistrationRequestTestBuilder withBirthApplication(List<BirthRegistrationApplication> application) {
        this.builder.birthRegistrationApplications(application);
        return this;
    }


}
