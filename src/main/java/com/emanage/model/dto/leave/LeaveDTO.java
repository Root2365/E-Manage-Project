package com.emanage.model.dto.leave;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.domain.leave.Leave;
import com.emanage.util.CalendarUtil;

import java.io.Serializable;

public class LeaveDTO implements Serializable {

    private static final long serialVersionUID = -1280581700711926607L;

    private Integer leaveID;

    private Integer employeeID;

    private String employeeName;

    private String reason;

    private String fromDateStr;

    private String toDateStr;

    private AppsConstants.LeaveStatus leaveStatus;

    public LeaveDTO() {
    }

    public LeaveDTO(Leave leave) {
        this.leaveID = leave.getLeaveID();
        this.employeeID = leave.getEmployee().getEmployeeID();
        this.employeeName = leave.getEmployee().getName();
        this.reason = leave.getReason();
        this.fromDateStr = CalendarUtil.getDefaultFormattedDate(leave.getFromDate());
        this.toDateStr = CalendarUtil.getDefaultFormattedDate(leave.getToDate());
        this.leaveStatus = leave.getLeaveStatus();
    }

    public Integer getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(Integer leaveID) {
        this.leaveID = leaveID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFromDateStr() {
        return fromDateStr;
    }

    public void setFromDateStr(String fromDateStr) {
        this.fromDateStr = fromDateStr;
    }

    public String getToDateStr() {
        return toDateStr;
    }

    public void setToDateStr(String toDateStr) {
        this.toDateStr = toDateStr;
    }

    public AppsConstants.LeaveStatus getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(AppsConstants.LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
