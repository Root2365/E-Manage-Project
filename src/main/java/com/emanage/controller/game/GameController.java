package com.emanage.controller.game;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.controller.BaseController;
import com.emanage.exception.AppsException;
import com.emanage.model.common.ResponseDTO;
import com.emanage.model.dto.game.GameDTO;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.service.game.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${api.prefix}/games")
public class GameController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/getAllGames", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<GameDTO>>> getAllGames() {
        ResponseDTO<List<GameDTO>> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Get all games by user : {}", credentialsDTO.getUserName());

        try {
            List<GameDTO> allGames = this.gameService.getAllGames();
            response.setResult(allGames);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Get all games by user : {}", credentialsDTO.getUserName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/saveOrUpdateGame", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<GameDTO>> saveOrUpdateGame(@RequestBody GameDTO gameUpdateDTO) {
        ResponseDTO<GameDTO> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Save/Update game by user : {} ", gameUpdateDTO);

        try {
            GameDTO gameDTO = this.gameService.saveOrUpdateGame(gameUpdateDTO, credentialsDTO);
            response.setResult(gameDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Save/Update game by user : {} ", gameUpdateDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/removeGame/{gameID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<GameDTO>> removeGame(@PathVariable Integer gameID) {
        ResponseDTO<GameDTO> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Remove game : {} by user {}", gameID, credentialsDTO.getUserID());

        try {
            GameDTO gameDTO = this.gameService.removeGame(gameID, credentialsDTO);
            response.setResult(gameDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Remove game : {} by user {}", gameID, credentialsDTO.getUserID());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
