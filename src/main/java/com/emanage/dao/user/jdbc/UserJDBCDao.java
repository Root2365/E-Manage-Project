package com.emanage.dao.user.jdbc;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.dao.BaseJDBCDao;
import com.emanage.exception.AppsException;
import com.emanage.model.dto.user.UserDTO;
import com.emanage.util.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserJDBCDao extends BaseJDBCDao {

    private static final Logger LOG = LoggerFactory.getLogger(UserJDBCDao.class);

    private QueryBuilder getUserQuery(String userName) {

        QueryBuilder userSQL = new QueryBuilder();

        userSQL.append("SELECT \n");
        userSQL.append("  USR.USER_NAME, \n");
        userSQL.append("  USR.PASSWORD, \n");
        userSQL.append("  USR.IS_ADMIN, \n");
        userSQL.append("  PR.PRIVILEGE_CODE, \n");
        userSQL.append("  USR.USER_ID AS USER_ID \n");
        userSQL.append("FROM t_user USR \n");
        userSQL.append("  LEFT JOIN t_user_role UR ON USR.USER_ID = UR.USER_ID \n");
        userSQL.append("  LEFT JOIN t_role_privilege RP ON RP.ROLE_ID = UR.ROLE_ID \n");
        userSQL.append("  LEFT JOIN t_privilege PR ON PR.PRIVILEGE_ID = RP.PRIVILEGE_ID \n");
        userSQL.append("WHERE \n");
        userSQL.appendNotNullMandatory("USR.STATUS = :status ", AppsConstants.Status.ACT.toString());
        userSQL.appendNotNullMandatory("AND USR.USER_NAME = BINARY :username ", userName);

        return userSQL;
    }

    public UserDTO getUser(String userName) throws AppsException {
        LOG.info("START: User retrieval for user {} ", userName);

        final UserDTO user = new UserDTO();
        QueryBuilder userSQL = this.getUserQuery(userName);

        try {
            namedParameterJdbcTemplate.query(userSQL.toString(), userSQL.getParameterMap(), rs -> {
                if (user.getUserID() == null) {
                    user.setUserID(rs.getInt("USER_ID"));
                    user.setUserName(rs.getString("USER_NAME"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setIsAdmin(AppsConstants.YesNo.resolveYesNo(rs.getString("IS_ADMIN")));
                }

                Optional<String> privilege = Optional.ofNullable(rs.getString("PRIVILEGE_CODE"));
                if (privilege.isPresent()) {
                    user.addPrivilege(privilege.get());
                }
            });

        } catch (EmptyResultDataAccessException e) {
            LOG.warn("User with user details `{}` does not exist", userName);
            throw new AppsException("Empty results");
        }

        LOG.info("END: User retrieval for {} completed as {}", userName, user);

        return user;
    }
}
