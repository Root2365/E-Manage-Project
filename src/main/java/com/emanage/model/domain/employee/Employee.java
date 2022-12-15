package com.emanage.model.domain.employee;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.domain.common.UserTrackableEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "t_employee")
public class Employee extends UserTrackableEntity {

    private static final long serialVersionUID = 4020371651456441452L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NTIC_NO")
    private String nticNo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PASSPORT_EXPIRY_DATE")
    private Date passportExpiryDate;

    @Column(name = "SEAT_LOCATION")
    private String seatLocation;

    @Column(name = "WORK_INFORMATION")
    private String workInformation;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private AppsConstants.Gender gender;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "PHOTO_URL")
    private String photoURL;

    @Column(name = "SALARY")
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status;

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNticNo() {
        return nticNo;
    }

    public void setNticNo(String nticNo) {
        this.nticNo = nticNo;
    }

    public Date getPassportExpiryDate() {
        return passportExpiryDate;
    }

    public void setPassportExpiryDate(Date passportExpiryDate) {
        this.passportExpiryDate = passportExpiryDate;
    }

    public String getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(String seatLocation) {
        this.seatLocation = seatLocation;
    }

    public String getWorkInformation() {
        return workInformation;
    }

    public void setWorkInformation(String workInformation) {
        this.workInformation = workInformation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AppsConstants.Gender getGender() {
        return gender;
    }

    public void setGender(AppsConstants.Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public AppsConstants.Status getStatus() {
        return status;
    }

    public void setStatus(AppsConstants.Status status) {
        this.status = status;
    }
}
