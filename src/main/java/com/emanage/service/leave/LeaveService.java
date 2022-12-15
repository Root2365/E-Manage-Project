package com.emanage.service.leave;

import com.emanage.dao.employee.EmployeeDao;
import com.emanage.dao.leave.LeaveDao;
import com.emanage.exception.AppsException;
import com.emanage.model.domain.employee.Employee;
import com.emanage.model.domain.leave.Leave;
import com.emanage.model.dto.leave.LeaveDTO;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.util.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveDao leaveDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LeaveDTO> getAllLeaves() throws AppsException {
        List<Leave> leaves = this.leaveDao.findAll();
        List<LeaveDTO> leaveDTOList = new ArrayList<>();

        for (Leave leave : leaves) {
            leaveDTOList.add(new LeaveDTO(leave));
        }

        return leaveDTOList;
    }

    public LeaveDTO saveOrUpdateLeave(LeaveDTO leaveUpdateDTO, CredentialsDTO credentialsDTO) throws AppsException {
        Date date = new Date();
        boolean isNew = (leaveUpdateDTO.getLeaveID() == null);
        Leave leave;

        if (isNew) {
            leave = new Leave();

            leave.setCreatedDate(date);
            leave.setCreatedBy(credentialsDTO.getUserID());
        } else {
            leave = this.leaveDao.getById(leaveUpdateDTO.getLeaveID());

            leave.setModifiedDate(date);
            leave.setModifiedBy(credentialsDTO.getUserID());
        }

        Employee employee = this.employeeDao.getById(leaveUpdateDTO.getEmployeeID());
        leave.setEmployee(employee);

        leave.setLeaveStatus(leaveUpdateDTO.getLeaveStatus());
        leave.setReason(leaveUpdateDTO.getReason());
        leave.setFromDate(CalendarUtil.getDefaultParsedDateOnly(leaveUpdateDTO.getFromDateStr()));
        leave.setToDate(CalendarUtil.getDefaultParsedDateOnly(leaveUpdateDTO.getToDateStr()));

        leave = this.leaveDao.saveAndFlush(leave);

        return new LeaveDTO(leave);
    }
}
