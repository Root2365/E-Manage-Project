package com.emanage.model.dto.user;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.domain.user.User;
import com.emanage.util.CalendarUtil;

import java.io.Serializable;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 7596882416211699561L;

    private Integer userID;

    private String userName;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String dateOfBirthStr;

    private AppsConstants.Status status;

    private AppsConstants.YesNo isAdmin;

    private List<Integer> roles;

    private List<Integer> addedRoles;

    private List<Integer> removedRoles;

    private SortedSet<String> privileges;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.userID = user.getUserID();
        this.userName = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.dateOfBirthStr = CalendarUtil.getDefaultFormattedDateOnly(user.getDateOfBirth());
        this.status = user.getStatus();
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirthStr() {
        return dateOfBirthStr;
    }

    public void setDateOfBirthStr(String dateOfBirthStr) {
        this.dateOfBirthStr = dateOfBirthStr;
    }

    public AppsConstants.YesNo getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(AppsConstants.YesNo isAdmin) {
        this.isAdmin = isAdmin;
    }

    public AppsConstants.Status getStatus() {
        return status;
    }

    public void setStatus(AppsConstants.Status status) {
        this.status = status;
    }

    public SortedSet<String> getPrivileges() {
        if (privileges == null) {
            privileges = new TreeSet<>();
        }
        return privileges;
    }

    public void setPrivileges(SortedSet<String> privileges) {
        this.privileges = privileges;
    }

    public void addPrivilege(String privilegeCode) {
        if (privileges == null) {
            privileges = new TreeSet<>();
        }
        privileges.add(privilegeCode);
    }
}
