package com.emanage.model.dto.game;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.domain.game.Game;
import com.emanage.util.CalendarUtil;

import java.io.Serializable;

public class GameDTO implements Serializable {

    private static final long serialVersionUID = 1315601196661201173L;

    private Integer gameID;

    private String picture;

    private AppsConstants.Status status;

    private String expiryDateStr;

    private String createdDateStr;

    public GameDTO() {
    }

    public GameDTO(Game game) {
        this.gameID = game.getGameID();
        this.picture = game.getPicture();
        this.status = game.getStatus();
        this.expiryDateStr = CalendarUtil.getDefaultFormattedDateTime(game.getExpiryDate());
        this.createdDateStr = CalendarUtil.getDefaultFormattedDateTime(game.getCreatedDate());
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public AppsConstants.Status getStatus() {
        return status;
    }

    public void setStatus(AppsConstants.Status status) {
        this.status = status;
    }

    public String getExpiryDateStr() {
        return expiryDateStr;
    }

    public void setExpiryDateStr(String expiryDateStr) {
        this.expiryDateStr = expiryDateStr;
    }

    public String getCreatedDateStr() {
        return createdDateStr;
    }

    public void setCreatedDateStr(String createdDateStr) {
        this.createdDateStr = createdDateStr;
    }
}
