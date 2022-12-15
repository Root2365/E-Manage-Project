package com.emanage.dao.role;

import com.emanage.model.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    Role getByRoleName(String roleName);
}
