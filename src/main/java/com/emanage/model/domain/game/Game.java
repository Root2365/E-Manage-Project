package com.emanage.model.domain.game;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.domain.common.UserTrackableEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_game")
public class Game extends UserTrackableEntity {

    private static final long serialVersionUID = 3970015454388650527L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "GAME_ID")
    private Integer gameID;

    @Column(name = "PICTURE")
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
