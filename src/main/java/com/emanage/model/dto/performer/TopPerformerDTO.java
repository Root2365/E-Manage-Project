package com.emanage.model.dto.performer;

import com.emanage.model.domain.performer.TopPerformer;
import com.emanage.util.CalendarUtil;

import java.io.Serializable;

public class TopPerformerDTO implements Serializable {

    private static final long serialVersionUID = -8686983190685729211L;

    private Integer topPerformerID;

    private String employeeName;

    private String picture;

    private String reason;

    private String createdDateStr;

    public TopPerformerDTO() {
    }

    public TopPerformerDTO(TopPerformer topPerformer) {
        this.topPerformerID = topPerformer.getTopPerformerID();
        this.employeeName = topPerformer.getEmployeeName();
        this.picture = topPerformer.getPicture();
        this.reason = topPerformer.getReason();
        this.createdDateStr = CalendarUtil.getDefaultFormattedDateTime(topPerformer.getCreatedDate());
    }

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

    public String getCreatedDateStr() {
        return createdDateStr;
    }

    public void setCreatedDateStr(String createdDateStr) {
        this.createdDateStr = createdDateStr;
    }
}
