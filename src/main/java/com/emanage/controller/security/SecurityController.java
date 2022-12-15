package com.emanage.controller.security;

import com.emanage.controller.BaseController;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.service.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.prefix}/security")
public class SecurityController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/log-out", headers = "Accept=application/json", method = RequestMethod.POST)
    public void expireUserCache() {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Expire cache for user : {} ", credentialsDTO.getUserName());

        try {
            LOG.info("User successfully logout {}", credentialsDTO.getUserName());
            //this.securityService.expireUserFromCache(credentialsDTO.getUserName());
        } catch (Exception e) {
            LOG.warn("User cache expiration failed for user {}", credentialsDTO.getUserName(), e);
        }

        LOG.info("END : Expire cache for user : {} ", credentialsDTO.getUserName());
    }
}
