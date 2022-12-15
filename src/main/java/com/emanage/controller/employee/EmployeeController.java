package com.emanage.controller.employee;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.controller.BaseController;
import com.emanage.exception.AppsException;
import com.emanage.model.common.ResponseDTO;
import com.emanage.model.dto.employee.EmployeeDTO;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.service.employee.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${api.prefix}/employee")
public class EmployeeController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/getEmployees", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<EmployeeDTO>>> getEmployees() {
        ResponseDTO<List<EmployeeDTO>> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Get all employees by user : {}", credentialsDTO.getUserName());

        try {
            List<EmployeeDTO> allEmployees = this.employeeService.getEmployees();
            response.setResult(allEmployees);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Get all employees by user : {}", credentialsDTO.getUserName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/saveOrUpdateEmployee", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<EmployeeDTO>> saveOrUpdateEmployee(@RequestBody EmployeeDTO employeeUpdateDTO) {
        ResponseDTO<EmployeeDTO> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Save/Update employee by user : {} ", employeeUpdateDTO);

        try {
            EmployeeDTO employeeDTO = this.employeeService.saveOrUpdateEmployee(employeeUpdateDTO, credentialsDTO);
            response.setResult(employeeDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Save/Update employee by user : {} ", employeeUpdateDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
