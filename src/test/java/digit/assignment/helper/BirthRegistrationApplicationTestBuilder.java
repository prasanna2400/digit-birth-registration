package digit.assignment.helper;

import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.User;
import digit.models.coremodels.AuditDetails;

public class BirthRegistrationApplicationTestBuilder {


    private BirthRegistrationApplication.BirthRegistrationApplicationBuilder builder;

    public BirthRegistrationApplicationTestBuilder() {

        this.builder = BirthRegistrationApplication.builder();

    }


    public static BirthRegistrationApplicationTestBuilder builder() {
        return new BirthRegistrationApplicationTestBuilder();
    }

    public BirthRegistrationApplication build() {
        return this.builder.build();
    }


    public BirthRegistrationApplicationTestBuilder withApplication() {
        this.builder.id("someId").tenantId("tenantID").applicationNumber("application-number").auditDetails(AuditDetails.builder().createdBy("creator").lastModifiedBy("creator").lastModifiedTime(1L).createdTime(1L).build()).address(BirthApplicationAddressTestBuilder.builder().withBirthAddress().build()).build();
        return this;
    }

    public BirthRegistrationApplicationTestBuilder withTenantId(String tenantId) {
        this.builder.tenantId(tenantId).build();
        return this;
    }


    public BirthRegistrationApplicationTestBuilder withFatherApplication() {
        this.builder.father(User.builder().uuid("uuid").name("father").build());
        return this;
    }

    public BirthRegistrationApplicationTestBuilder withMotherApplication() {
        this.builder.father(User.builder().uuid("uuid").name("mother").build());
        return this;
    }
}
