package com.emanage.service.performer;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.dao.performer.TopPerformerDao;
import com.emanage.exception.AppsException;
import com.emanage.model.domain.performer.TopPerformer;
import com.emanage.model.dto.performer.TopPerformerDTO;
import com.emanage.model.security.CredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TopPerformerService {

    @Autowired
    private TopPerformerDao topPerformerDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<TopPerformerDTO> getAllTopPerformers() throws AppsException {
        List<TopPerformerDTO> topPerformerDTOList = new ArrayList<>();
        List<TopPerformer> topPerformers = this.topPerformerDao.findAll();

        for (TopPerformer topPerformer : topPerformers) {
            if (topPerformer.getStatus() == AppsConstants.Status.ACT) {
                topPerformerDTOList.add(new TopPerformerDTO(topPerformer));
            }
        }

        return topPerformerDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public TopPerformerDTO saveOrUpdateTopPerformer(TopPerformerDTO updateTopPerformerDTO, CredentialsDTO credentialsDTO) throws AppsException {
        Date date = new Date();
        boolean isNew = (updateTopPerformerDTO.getTopPerformerID() == null);
        TopPerformer topPerformer;

        if (isNew) {
            topPerformer = new TopPerformer();

            topPerformer.setCreatedDate(date);
            topPerformer.setCreatedBy(credentialsDTO.getUserID());
        } else {
            topPerformer = this.topPerformerDao.getById(updateTopPerformerDTO.getTopPerformerID());

            topPerformer.setModifiedDate(date);
            topPerformer.setModifiedBy(credentialsDTO.getUserID());
        }

        topPerformer.setEmployeeName(updateTopPerformerDTO.getEmployeeName());
        topPerformer.setPicture(updateTopPerformerDTO.getPicture());
        topPerformer.setReason(updateTopPerformerDTO.getReason());
        topPerformer.setStatus(AppsConstants.Status.ACT);

        topPerformer = this.topPerformerDao.saveAndFlush(topPerformer);

        return new TopPerformerDTO(topPerformer);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public TopPerformerDTO removeTopPerformer(Integer topPerformerID, CredentialsDTO credentialsDTO) throws AppsException {
        TopPerformer topPerformer = this.topPerformerDao.getById(topPerformerID);

        topPerformer.setStatus(AppsConstants.Status.INA);
        topPerformer.setModifiedBy(credentialsDTO.getUserID());
        topPerformer.setModifiedDate(new Date());

        topPerformer = this.topPerformerDao.saveAndFlush(topPerformer);

        return new TopPerformerDTO(topPerformer);
    }
}
