package com.emanage.controller.enquiry;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.controller.BaseController;
import com.emanage.exception.AppsException;
import com.emanage.model.common.ResponseDTO;
import com.emanage.model.dto.enquiry.EnquiryDTO;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.service.enquiry.EnquiryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.prefix}/enquiry")
public class EnquiryController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(EnquiryController.class);

    @Autowired
    private EnquiryService enquiryService;

    @PostMapping(value = "/saveEnquiry", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<EnquiryDTO>> saveEnquiry(@RequestBody EnquiryDTO enquiryUpdateDTO) {
        ResponseDTO<EnquiryDTO> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Save enquiry by user : {} ", enquiryUpdateDTO);

        try {
            EnquiryDTO enquiryDTO = this.enquiryService.saveEnquiry(enquiryUpdateDTO, credentialsDTO);
            response.setResult(enquiryDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Save enquiry by user : {} ", enquiryUpdateDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
