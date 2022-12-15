package com.emanage.dao.user;

import com.emanage.model.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User getByUserName(String userName);

    User getByEmail(String email);
}
