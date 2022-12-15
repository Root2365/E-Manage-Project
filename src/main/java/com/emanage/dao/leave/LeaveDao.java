package com.emanage.dao.leave;

import com.emanage.model.domain.leave.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveDao extends JpaRepository<Leave, Integer> {
}
