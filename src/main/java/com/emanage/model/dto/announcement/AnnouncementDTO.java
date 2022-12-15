package com.emanage.model.dto.announcement;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.domain.announcement.Announcement;
import com.emanage.util.CalendarUtil;

import java.io.Serializable;

public class AnnouncementDTO implements Serializable {

    private static final long serialVersionUID = -8756693257083358344L;

    private Integer announcementID;

    private String title;

    private String description;

    private AppsConstants.Status status;

    private String createdDateStr;

    public AnnouncementDTO() {
    }

    public AnnouncementDTO(Announcement announcement) {
        this.announcementID = announcement.getAnnouncementID();
        this.title = announcement.getTitle();
        this.description = announcement.getDescription();
        this.status = announcement.getStatus();
        this.createdDateStr = CalendarUtil.getDefaultFormattedDate(announcement.getCreatedDate());
    }

    public Integer getAnnouncementID() {
        return announcementID;
    }

    public void setAnnouncementID(Integer announcementID) {
        this.announcementID = announcementID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppsConstants.Status getStatus() {
        return status;
    }

    public void setStatus(AppsConstants.Status status) {
        this.status = status;
    }

    public String getCreatedDateStr() {
        return createdDateStr;
    }

    public void setCreatedDateStr(String createdDateStr) {
        this.createdDateStr = createdDateStr;
    }
}
