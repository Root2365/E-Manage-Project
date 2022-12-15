package com.emanage.model.dto.enquiry;

import com.emanage.model.domain.enquiry.Enquiry;

import java.io.Serializable;

public class EnquiryDTO implements Serializable {

    private static final long serialVersionUID = -2942051889744675301L;

    private Integer enquiryID;

    private String name;

    private String fromEmail;

    private String toEmail;

    private String subject;

    private String message;

    public EnquiryDTO() {
    }

    public EnquiryDTO(Enquiry enquiry) {
        this.enquiryID = enquiry.getEnquiryID();
        this.name = enquiry.getName();
        this.fromEmail = enquiry.getFromEmail();
        this.toEmail = enquiry.getToEmail();
        this.subject = enquiry.getSubject();
        this.message = enquiry.getMessage();
    }

    public Integer getEnquiryID() {
        return enquiryID;
    }

    public void setEnquiryID(Integer enquiryID) {
        this.enquiryID = enquiryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
