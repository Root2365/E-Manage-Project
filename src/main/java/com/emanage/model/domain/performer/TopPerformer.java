package com.emanage.model.domain.performer;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.domain.common.UserTrackableEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_top_performer")
public class TopPerformer extends UserTrackableEntity {

    private static final long serialVersionUID = -6828505851080365497L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "TOP_PERFORMER_ID")
    private Integer topPerformerID;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "PICTURE")
    private String picture;

    @Column(name = "REASON")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status;

    public Integer getTopPerformerID() {
        return topPerformerID;
    }

    public void setTopPerformerID(Integer topPerformerID) {
        this.topPerformerID = topPerformerID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AppsConstants.Status getStatus() {
        return status;
    }

    public void setStatus(AppsConstants.Status status) {
        this.status = status;
    }
}
