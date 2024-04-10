package digit.assignment.helper;

import digit.assignment.web.models.Address;

public class AddressTestBuilder {
    private Address.AddressBuilder builder;

    public AddressTestBuilder() {
        this.builder = Address.builder();
    }

    public static AddressTestBuilder builder() {
        return new AddressTestBuilder();
    }

    public Address build() {
        return this.builder.build();
    }

    public AddressTestBuilder withAddress() {
        this.builder.tenantId("default").addressLine1("line 1").addressLine2("line 2").id("some-id").city("city")
                .landmark("landmark").pincode("98909");
        return this;
    }
}