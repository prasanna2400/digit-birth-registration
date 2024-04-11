package digit.assignment.service;

import digit.assignment.config.Configuration;
import digit.assignment.util.UserUtil;
import digit.assignment.web.models.*;
import digit.models.coremodels.UserSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.egov.common.contract.request.RequestInfo;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@Slf4j
public class UserService {
    private UserUtil userUtils;

    private Configuration config;

    @Autowired
    public UserService(UserUtil userUtils, Configuration config) {
        this.userUtils = userUtils;
        this.config = config;
    }

    /**
     * Calls user service to enrich user from search or upsert user
     *
     * @param request
     */
    public void callUserService(BirthRegistrationRequest request) {
        request.getBirthRegistrationApplications().forEach(application -> {
            if (!StringUtils.isEmpty(application.getFather().getUuid()))
                enrichUser(application, request.getRequestInfo());
            else {
                application.getFather().setUuid(upsertUser(application.getFather(), request.getRequestInfo()));
            }
        });

        request.getBirthRegistrationApplications().forEach(application -> {
            if (!StringUtils.isEmpty(application.getMother().getUuid()))
                enrichUser(application, request.getRequestInfo());
            else {
                application.getMother().setUuid(upsertUser(application.getMother(), request.getRequestInfo()));
            }
        });
    }


    private String upsertUser(User user, RequestInfo requestInfo) {

        String tenantId = user.getTenantId();
        User userServiceResponse = null;

        // Search on mobile number as user name
        UserDetailResponse userDetailResponse = searchUser(userUtils.getStateLevelTenant(tenantId), null, user.getMobileNumber());
        if (!userDetailResponse.getUser().isEmpty()) {
            User userFromSearch = userDetailResponse.getUser().get(0);
            log.info(userFromSearch.toString());
            if (!user.getMobileNumber().equalsIgnoreCase(userFromSearch.getUserName())) {
                userServiceResponse = updateUser(requestInfo, user, userFromSearch);
            } else userServiceResponse = userDetailResponse.getUser().get(0);
        } else {
            userServiceResponse = createUser(requestInfo, tenantId, user);
        }

        // Enrich the accountId
        user.setUuid(userServiceResponse.getUuid());
        return userServiceResponse.getUuid();
    }


    private void enrichUser(BirthRegistrationApplication application, RequestInfo requestInfo) {
        String accountIdFather = application.getFather().getUuid();
        String accountIdMother = application.getMother().getUuid();
        String tenantId = application.getTenantId();

        UserDetailResponse userDetailResponseFather = searchUser(userUtils.getStateLevelTenant(tenantId), accountIdFather, null);
        UserDetailResponse userDetailResponseMother = searchUser(userUtils.getStateLevelTenant(tenantId), accountIdMother, null);
        if (userDetailResponseFather.getUser().isEmpty())
            throw new CustomException("INVALID_ACCOUNTID", "No user exist for the given accountId");

        else application.getFather().setUuid(userDetailResponseFather.getUser().get(0).getUuid());

        if (userDetailResponseMother.getUser().isEmpty())
            throw new CustomException("INVALID_ACCOUNTID", "No user exist for the given accountId");

        else application.getMother().setUuid(userDetailResponseMother.getUser().get(0).getUuid());

    }

    /**
     * Creates the user from the given userInfo by calling user service
     *
     * @param requestInfo
     * @param tenantId
     * @param userInfo
     * @return
     */
    private User createUser(RequestInfo requestInfo, String tenantId, User userInfo) {

        userUtils.addUserDefaultFields(userInfo.getMobileNumber(), tenantId, userInfo, userInfo.getType());
        StringBuilder uri = new StringBuilder(config.getUserHost())
                .append(config.getUserContextPath())
                .append(config.getUserCreateEndpoint());

        CreateUserRequest user = new CreateUserRequest(requestInfo, userInfo);
        log.info(user.getUser().toString());
        UserDetailResponse userDetailResponse = userUtils.userCall(user, uri);

        return userDetailResponse.getUser().get(0);

    }

    /**
     * Updates the given user by calling user service
     *
     * @param requestInfo
     * @param user
     * @param userFromSearch
     * @return
     */
    private User updateUser(RequestInfo requestInfo, User user, User userFromSearch) {

        userFromSearch.setName(user.getName());
        userFromSearch.setActive(true);

        StringBuilder uri = new StringBuilder(config.getUserHost())
                .append(config.getUserContextPath())
                .append(config.getUserUpdateEndpoint());


        UserDetailResponse userDetailResponse = userUtils.userCall(new CreateUserRequest(requestInfo, userFromSearch), uri);

        return userDetailResponse.getUser().get(0);

    }

    /**
     * calls the user search API based on the given accountId and userName
     *
     * @param stateLevelTenant
     * @param accountId
     * @param userName
     * @return
     */
    public UserDetailResponse searchUser(String stateLevelTenant, String accountId, String userName) {

        UserSearchRequest userSearchRequest = new UserSearchRequest();
        userSearchRequest.setActive(true);
        userSearchRequest.setUserType("CITIZEN");
        userSearchRequest.setTenantId(stateLevelTenant);

        if (StringUtils.isEmpty(accountId) && StringUtils.isEmpty(userName))
            return null;

        if (!StringUtils.isEmpty(accountId))
            userSearchRequest.setUuid(Collections.singletonList(accountId));

        if (!StringUtils.isEmpty(userName))
            userSearchRequest.setUserName(userName);

        StringBuilder uri = new StringBuilder(config.getUserHost()).append(config.getUserSearchEndpoint());
        return userUtils.userCall(userSearchRequest, uri);

    }

}
