package com.emanage.service.enquiry;

import com.emanage.dao.enquiry.EnquiryDao;
import com.emanage.exception.AppsException;
import com.emanage.model.domain.enquiry.Enquiry;
import com.emanage.model.dto.enquiry.EnquiryDTO;
import com.emanage.model.security.CredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class EnquiryService {

    @Autowired
    private EnquiryDao enquiryDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public EnquiryDTO saveEnquiry(EnquiryDTO enquiryUpdateDTO, CredentialsDTO credentialsDTO) throws AppsException {
        Enquiry enquiry = new Enquiry();
        Date date = new Date();

        enquiry.setFromEmail(enquiryUpdateDTO.getFromEmail());
        enquiry.setToEmail(enquiryUpdateDTO.getToEmail());
        enquiry.setName(enquiryUpdateDTO.getName());
        enquiry.setSubject(enquiryUpdateDTO.getSubject());
        enquiry.setMessage(enquiryUpdateDTO.getMessage());
        enquiry.setCreatedDate(date);
        enquiry.setCreatedBy(credentialsDTO.getUserID());

        enquiry = enquiryDao.saveAndFlush(enquiry);

        return new EnquiryDTO(enquiry);
    }
}
