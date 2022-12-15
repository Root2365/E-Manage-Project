package com.emanage.service.game;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.dao.game.GameDao;
import com.emanage.exception.AppsException;
import com.emanage.model.domain.game.Game;
import com.emanage.model.dto.game.GameDTO;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.util.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameDao gameDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<GameDTO> getAllGames() throws AppsException {
        List<Game> games = this.gameDao.findAll();
        List<GameDTO> gameDTOList = new ArrayList<>();

        for (Game game : games) {
            gameDTOList.add(new GameDTO(game));
        }

        return gameDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public GameDTO saveOrUpdateGame(GameDTO gameUpdateDTO, CredentialsDTO credentialsDTO) throws AppsException {
        Date date = new Date();
        boolean isNew = (gameUpdateDTO.getGameID() == null);
        Game game;

        if (isNew) {
            game = new Game();

            game.setCreatedDate(date);
            game.setCreatedBy(credentialsDTO.getUserID());
        } else {
            game = this.gameDao.getById(gameUpdateDTO.getGameID());

            game.setModifiedDate(date);
            game.setModifiedBy(credentialsDTO.getUserID());
        }

        game.setPicture(gameUpdateDTO.getPicture());
        game.setStatus(AppsConstants.Status.ACT);
        game.setExpiryDate(CalendarUtil.getDefaultParsedDateOnly(gameUpdateDTO.getExpiryDateStr()));

        game = this.gameDao.saveAndFlush(game);

        return new GameDTO(game);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public GameDTO removeGame(Integer gameID, CredentialsDTO credentialsDTO) throws AppsException {
        Game game = this.gameDao.getById(gameID);

        game.setStatus(AppsConstants.Status.INA);
        game.setModifiedBy(credentialsDTO.getUserID());
        game.setModifiedDate(new Date());

        game = this.gameDao.saveAndFlush(game);

        return new GameDTO(game);
    }
}
