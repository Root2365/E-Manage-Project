package com.emanage.service.announcement;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.dao.announcement.AnnouncementDao;
import com.emanage.exception.AppsException;
import com.emanage.model.domain.announcement.Announcement;
import com.emanage.model.dto.announcement.AnnouncementDTO;
import com.emanage.model.security.CredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<AnnouncementDTO> getAllAnnouncements() throws AppsException {
        List<AnnouncementDTO> announcementDTOList = new ArrayList<>();
        List<Announcement> announcements = this.announcementDao.findAll();

        for (Announcement announcement : announcements) {
            announcementDTOList.add(new AnnouncementDTO(announcement));
        }

        return announcementDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public AnnouncementDTO saveOrUpdateAnnouncement(AnnouncementDTO announcementUpdateDTO, CredentialsDTO credentialsDTO) throws AppsException {
        Date date = new Date();
        boolean isNew = (announcementUpdateDTO.getAnnouncementID() == null);
        Announcement announcement;

        if (isNew) {
            announcement = new Announcement();

            announcement.setCreatedDate(date);
            announcement.setCreatedBy(credentialsDTO.getUserID());
        } else {
            announcement = this.announcementDao.getById(announcementUpdateDTO.getAnnouncementID());

            announcement.setModifiedDate(date);
            announcement.setModifiedBy(credentialsDTO.getUserID());
        }

        announcement.setTitle(announcementUpdateDTO.getTitle());
        announcement.setDescription(announcementUpdateDTO.getDescription());
        announcement.setStatus(announcementUpdateDTO.getStatus());

        announcement = this.announcementDao.saveAndFlush(announcement);

        return new AnnouncementDTO(announcement);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public AnnouncementDTO removeAnnouncement(Integer announcementID, CredentialsDTO credentialsDTO) throws AppsException {
        Announcement announcement = this.announcementDao.getById(announcementID);

        announcement.setStatus(AppsConstants.Status.INA);
        announcement.setModifiedDate(new Date());
        announcement.setModifiedBy(credentialsDTO.getUserID());

        announcement = this.announcementDao.saveAndFlush(announcement);

        return new AnnouncementDTO(announcement);
    }
}
