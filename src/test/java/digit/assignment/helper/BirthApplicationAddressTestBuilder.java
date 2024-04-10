package digit.assignment.helper;


import digit.assignment.web.models.BirthApplicationAddress;

public class BirthApplicationAddressTestBuilder {

    private BirthApplicationAddress.BirthApplicationAddressBuilder builder;


    public BirthApplicationAddressTestBuilder() {
        this.builder = BirthApplicationAddress.builder();
    }

    public static BirthApplicationAddressTestBuilder builder() {
        return new BirthApplicationAddressTestBuilder();
    }

    public BirthApplicationAddress build() {
        return this.builder.build();
    }


    public BirthApplicationAddressTestBuilder withBirthAddress() {
        this.builder.tenantId("default").applicationNumber("TEST_APP_001").applicantAddress(AddressTestBuilder.builder().withAddress().build());
        return this;
    }
}
