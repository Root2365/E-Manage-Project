package com.emanage.service.employee;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.dao.employee.EmployeeDao;
import com.emanage.exception.AppsException;
import com.emanage.model.domain.employee.Employee;
import com.emanage.model.dto.employee.EmployeeDTO;
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
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<EmployeeDTO> getEmployees() throws AppsException {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<Employee> employeeList = this.employeeDao.findAll();

        for (Employee employee : employeeList) {
            employeeDTOList.add(new EmployeeDTO(employee));
        }

        return employeeDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public EmployeeDTO saveOrUpdateEmployee(EmployeeDTO employeeUpdateDTO, CredentialsDTO credentialsDTO) throws AppsException {
        Date date = new Date();
        boolean isNew = (employeeUpdateDTO.getEmployeeID() == null);
        Employee employee;

        if (isNew) {
            employee = new Employee();

            employee.setCreatedDate(date);
            employee.setCreatedBy(credentialsDTO.getUserID());
        } else {
            employee = this.employeeDao.getById(employeeUpdateDTO.getEmployeeID());

            employee.setModifiedDate(date);
            employee.setModifiedBy(credentialsDTO.getUserID());
        }

        employee.setName(employeeUpdateDTO.getName());
        employee.setAddress(employeeUpdateDTO.getAddress());
        employee.setNticNo(employeeUpdateDTO.getNticNo());
        employee.setPassportExpiryDate(CalendarUtil.getDefaultParsedDateOnly(employeeUpdateDTO.getPassportExpiryDateStr()));
        employee.setSeatLocation(employeeUpdateDTO.getSeatLocation());
        employee.setWorkInformation(employeeUpdateDTO.getWorkInformation());
        employee.setPhoneNumber(employeeUpdateDTO.getPhoneNumber());
        employee.setGender(employeeUpdateDTO.getGender());
        employee.setEmail(employeeUpdateDTO.getEmail());
        employee.setDepartment(employeeUpdateDTO.getDepartment());
        employee.setPhotoURL(employeeUpdateDTO.getPhotoURL());
        employee.setSalary(employeeUpdateDTO.getSalary());
        employee.setStatus(AppsConstants.Status.ACT);

        employee = this.employeeDao.saveAndFlush(employee);

        return new EmployeeDTO(employee);
    }
}
