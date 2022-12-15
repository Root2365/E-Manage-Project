package com.emanage.controller.leave;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.controller.BaseController;
import com.emanage.exception.AppsException;
import com.emanage.model.common.ResponseDTO;
import com.emanage.model.dto.leave.LeaveDTO;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.service.leave.LeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${api.prefix}/leave")
public class LeaveController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(LeaveController.class);

    @Autowired
    private LeaveService leaveService;

    @GetMapping(value = "/getAllLeaves", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<LeaveDTO>>> getAllLeaves() {
        ResponseDTO<List<LeaveDTO>> response = new ResponseDTO<>();
        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Get all leaves by user : {}", credentialsDTO.getUserName());

        try {
            List<LeaveDTO> allLeaves = this.leaveService.getAllLeaves();
            response.setResult(allLeaves);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Get all leaves by user : {}", credentialsDTO.getUserName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/saveOrUpdateLeave", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<LeaveDTO>> saveOrUpdateLeave(@RequestBody LeaveDTO leaveUpdateDTO) {
        ResponseDTO<LeaveDTO> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Save/Update leave by user : {} ", leaveUpdateDTO);

        try {
            LeaveDTO leaveDTO = this.leaveService.saveOrUpdateLeave(leaveUpdateDTO, credentialsDTO);
            response.setResult(leaveDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Save/Update leave by user : {} ", leaveUpdateDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
