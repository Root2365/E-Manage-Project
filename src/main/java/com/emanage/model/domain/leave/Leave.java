package com.emanage.model.domain.leave;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.domain.common.UserTrackableEntity;
import com.emanage.model.domain.employee.Employee;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_leave")
public class Leave extends UserTrackableEntity {

    private static final long serialVersionUID = -4726041710786887022L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "LEAVE_ID")
    private Integer leaveID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "REASON")
    private String reason;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TO_DATE")
    private Date toDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "LEAVE_STATUS")
    private AppsConstants.LeaveStatus leaveStatus;

    public Integer getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(Integer leaveID) {
        this.leaveID = leaveID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public AppsConstants.LeaveStatus getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(AppsConstants.LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
