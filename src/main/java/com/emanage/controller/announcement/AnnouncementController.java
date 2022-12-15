package com.emanage.controller.announcement;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.controller.BaseController;
import com.emanage.exception.AppsException;
import com.emanage.model.common.ResponseDTO;
import com.emanage.model.dto.announcement.AnnouncementDTO;
import com.emanage.model.dto.game.GameDTO;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.service.announcement.AnnouncementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${api.prefix}/announcement")
public class AnnouncementController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AnnouncementController.class);

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping(value = "/getAllAnnouncements", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<AnnouncementDTO>>> getAllGames() {
        ResponseDTO<List<AnnouncementDTO>> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Get all announcements by user : {}", credentialsDTO.getUserID());

        try {
            List<AnnouncementDTO> allAnnouncements = this.announcementService.getAllAnnouncements();
            response.setResult(allAnnouncements);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Get all announcements by user : {}", credentialsDTO.getUserName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/saveOrUpdateAnnouncement", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<AnnouncementDTO>> saveOrUpdateAnnouncement(@RequestBody AnnouncementDTO announcementUpdateDTO) {
        ResponseDTO<AnnouncementDTO> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Save/Update announcement by user : {} ", announcementUpdateDTO);

        try {
            AnnouncementDTO announcementDTO = this.announcementService.saveOrUpdateAnnouncement(announcementUpdateDTO, credentialsDTO);
            response.setResult(announcementDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Save/Update announcement by user : {} ", announcementUpdateDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/removeAnnouncement/{announcementID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<AnnouncementDTO>> removeAnnouncement(@PathVariable Integer announcementID) {
        ResponseDTO<AnnouncementDTO> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Remove announcement : {} by user {}", announcementID, credentialsDTO.getUserID());

        try {
            AnnouncementDTO announcementDTO = this.announcementService.removeAnnouncement(announcementID, credentialsDTO);
            response.setResult(announcementDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Remove announcement : {} by user {}", announcementID, credentialsDTO.getUserID());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
