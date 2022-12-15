package com.emanage.model.dto.employee;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.domain.employee.Employee;
import com.emanage.util.CalendarUtil;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = -6353662516427974567L;

    private Integer employeeID;

    private String name;

    private String address;

    private String nticNo;

    private String passportExpiryDateStr;

    private String seatLocation;

    private String workInformation;

    private String phoneNumber;

    private AppsConstants.Gender gender;

    private String email;

    private String department;

    private String photoURL;

    private BigDecimal salary;

    private AppsConstants.Status status;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee) {
        this.employeeID = employee.getEmployeeID();
        this.name = employee.getName();
        this.address = employee.getAddress();
        this.nticNo = employee.getNticNo();
        this.passportExpiryDateStr = CalendarUtil.getDefaultFormattedDate(employee.getPassportExpiryDate());
        this.seatLocation = employee.getSeatLocation();
        this.workInformation = employee.getWorkInformation();
        this.phoneNumber = employee.getPhoneNumber();
        this.gender = employee.getGender();
        this.email = employee.getEmail();
        this.department = employee.getDepartment();
        this.photoURL = employee.getPhotoURL();
        this.salary = employee.getSalary();
        this.status = employee.getStatus();
    }

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

    public String getPassportExpiryDateStr() {
        return passportExpiryDateStr;
    }

    public void setPassportExpiryDateStr(String passportExpiryDateStr) {
        this.passportExpiryDateStr = passportExpiryDateStr;
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
