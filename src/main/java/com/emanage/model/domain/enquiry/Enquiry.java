package com.emanage.model.domain.enquiry;

import com.emanage.model.domain.common.UserTrackableEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_enquiry")
public class Enquiry extends UserTrackableEntity {

    private static final long serialVersionUID = 2083243563932231908L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ENQUIRY_ID")
    private Integer enquiryID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FROM_EMAIL")
    private String fromEmail;

    @Column(name = "TO_EMAIL")
    private String toEmail;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "message")
    private String message;

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
