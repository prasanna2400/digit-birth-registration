package digit.assignment.repository;


import digit.assignment.repository.querybuilder.BirthRegistrationQueryBuilder;
import digit.assignment.repository.rowmapper.BirthRegistrationRowMapper;
import digit.assignment.web.models.BirthApplicationSearchCriteria;
import digit.assignment.web.models.BirthRegistrationApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Repository
public class BirthRegistrationRepository {

    @Autowired
    private BirthRegistrationQueryBuilder queryBuilder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BirthRegistrationRowMapper rowMapper;

    public List<BirthRegistrationApplication> getApplications(BirthApplicationSearchCriteria searchCriteria) {
        List<Object> preparedStmtList = new ArrayList<>();
        String query = queryBuilder.getBirthApplicationSearchQuery(searchCriteria, preparedStmtList);
        log.info("Final query: " + query);
        return jdbcTemplate.query(query, preparedStmtList.toArray(), rowMapper);
    }
}
