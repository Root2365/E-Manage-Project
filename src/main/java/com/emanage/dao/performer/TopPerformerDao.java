package com.emanage.dao.performer;

import com.emanage.model.domain.performer.TopPerformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopPerformerDao extends JpaRepository<TopPerformer, Integer> {
}
