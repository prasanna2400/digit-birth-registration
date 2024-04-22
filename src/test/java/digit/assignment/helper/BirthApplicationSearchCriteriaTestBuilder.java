package digit.assignment.helper;


import digit.assignment.web.models.BirthApplicationSearchCriteria;

public class BirthApplicationSearchCriteriaTestBuilder {

    private BirthApplicationSearchCriteria.BirthApplicationSearchCriteriaBuilder builder;


    public BirthApplicationSearchCriteriaTestBuilder() {
        this.builder = BirthApplicationSearchCriteria.builder();
    }

    public static BirthApplicationSearchCriteriaTestBuilder builder() {
        return new BirthApplicationSearchCriteriaTestBuilder();
    }

    public BirthApplicationSearchCriteria build() {
        return this.builder.build();
    }

    public BirthApplicationSearchCriteriaTestBuilder withSearchCriteria() {
        this.builder.tenantId("tenantId").applicationNumber("application-number").build();
        return this;
    }
}
