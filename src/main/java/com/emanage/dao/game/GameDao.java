package com.emanage.dao.game;

import com.emanage.model.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDao extends JpaRepository<Game, Integer> {
}
