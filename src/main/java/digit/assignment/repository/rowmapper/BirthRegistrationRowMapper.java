package digit.assignment.repository.rowmapper;

import digit.assignment.web.models.Address;
import digit.assignment.web.models.BirthApplicationAddress;
import digit.assignment.web.models.BirthRegistrationApplication;
import digit.assignment.web.models.User;
import digit.models.coremodels.AuditDetails;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


@Component
public class BirthRegistrationRowMapper implements RowMapper<BirthRegistrationApplication> {


    @Override
    public BirthRegistrationApplication mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            BirthRegistrationApplication birthApplication = BirthRegistrationApplication.builder()
                    .applicationNumber(resultSet.getString("bapplicationnumber"))
                    .tenantId(resultSet.getString("btenantid"))
                    .id(resultSet.getString("bid"))
                    .babyFirstName(resultSet.getString("bbabyfirstname"))
                    .babyLastName(resultSet.getString("bbabylastname"))
                    .doctorName(resultSet.getString("bdoctorname"))
                    .hospitalName(resultSet.getString("bhospitalname"))
                    .placeOfBirth(resultSet.getString("bplaceofbirth"))
                    .timeOfBirth(resultSet.getInt("btimeofbirth"))
                    .father(User.builder().uuid(resultSet.getString("bfatherid")).build())
                    .mother(User.builder().uuid(resultSet.getString("bmotherid")).build())
                    .auditDetails(
                            AuditDetails.builder()
                                    .createdBy(resultSet.getString("bcreatedby"))
                                    .createdTime(resultSet.getLong("bcreatedtime"))
                                    .lastModifiedBy(resultSet.getString("blastmodifiedby"))
                                    .lastModifiedTime(resultSet.getLong("blastModifiedtime"))
                                    .build()
                    )

                    .address(BirthApplicationAddress.builder()
                            .id(UUID.fromString(resultSet.getString("amid")))
                            .tenantId(resultSet.getString("amtenantid"))
                            .applicationNumber(resultSet.getString("amapplicationnumber"))
                            .applicantAddress(Address.builder()
                                    .id(resultSet.getString("aid"))
                                    .tenantId(resultSet.getString("atenantid"))
                                    .doorNo(resultSet.getString("adoorno"))
                                    .street(resultSet.getString("astreet"))
                                    .latitude(resultSet.getDouble("alatitude"))
                                    .longitude(resultSet.getDouble("alongitude"))
                                    .addressNumber(resultSet.getString("aaddressnumber"))
                                    .addressLine1(resultSet.getString("aaddressline1"))
                                    .addressLine2(resultSet.getString("aaddressline2"))
                                    .landmark(resultSet.getString("alandmark"))
                                    .city(resultSet.getString("acity"))
                                    .pincode(resultSet.getString("apincode"))
                                    .detail(resultSet.getString("adetail"))
                                    .registrationId(resultSet.getString("aregistrationid"))
                                    .build())
                            .build())
                    .build();


            return birthApplication;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
