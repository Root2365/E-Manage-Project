package com.emanage.model.domain.announcement;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.domain.common.UserTrackableEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_announcement")
public class Announcement extends UserTrackableEntity {

    private static final long serialVersionUID = -4430557241322783265L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ANNOUNCEMENT_ID")
    private Integer announcementID;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status;

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
}
