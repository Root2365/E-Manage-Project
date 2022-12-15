package com.emanage.controller.performer;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.controller.BaseController;
import com.emanage.exception.AppsException;
import com.emanage.model.common.ResponseDTO;
import com.emanage.model.dto.performer.TopPerformerDTO;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.service.performer.TopPerformerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${api.prefix}/top-performer")
public class TopPerformerController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(TopPerformerController.class);

    @Autowired
    private TopPerformerService topPerformerService;

    @GetMapping(value = "/getAllTopPerformers", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<TopPerformerDTO>>> getAllTopPerformers() {
        ResponseDTO<List<TopPerformerDTO>> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Get all top performers by user : {}", credentialsDTO.getUserName());

        try {
            List<TopPerformerDTO> topPerformers = this.topPerformerService.getAllTopPerformers();
            response.setResult(topPerformers);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Get all top performers by user : {}", credentialsDTO.getUserName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/saveOrUpdateTopPerformer", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<TopPerformerDTO>> saveOrUpdateTopPerformer(@RequestBody TopPerformerDTO topPerformerDTO) {
        ResponseDTO<TopPerformerDTO> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Save/Update top performer user : {} ", topPerformerDTO);

        try {
            TopPerformerDTO tpfDTO = this.topPerformerService.saveOrUpdateTopPerformer(topPerformerDTO, credentialsDTO);
            response.setResult(tpfDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Save/Update top performer user : {} ", topPerformerDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/removeTopPerformer/{topPerformerID}", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO<TopPerformerDTO>> removeTopPerformer(@PathVariable Integer topPerformerID) {
        ResponseDTO<TopPerformerDTO> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Remove top performer : {} by user {}", topPerformerID, credentialsDTO.getUserID());

        try {
            TopPerformerDTO topPerformerDTO = this.topPerformerService.removeTopPerformer(topPerformerID, credentialsDTO);
            response.setResult(topPerformerDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Remove top performer : {} by user {}", topPerformerID, credentialsDTO.getUserID());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
