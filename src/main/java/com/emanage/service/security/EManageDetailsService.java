package com.emanage.service.security;

import com.emanage.exception.AppsException;
import com.emanage.exception.AppsRuntimeException;
import com.emanage.model.dto.user.UserDTO;
import com.emanage.model.security.EManageUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EManageDetailsService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(EManageDetailsService.class);

    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user;

        try {
            user = securityService.getUserByUsername(username);
        } catch (AppsException e) {
            if (e.getAppsErrorMessages().contains("")) {
                LOG.warn("User with name {} does not exist", username);
                throw new UsernameNotFoundException("Invalid userIdentifiableToken");
            } else {
                LOG.warn("Getting user for {} failed with error {}", username, e.getAppsErrorMessages());
                throw new AppsRuntimeException();
            }
        } catch (Exception e) {
            LOG.warn("Getting user for {} failed with unknown error ", e);
            throw new AppsRuntimeException();
        }

        return new EManageUserDetails(user);
    }
}
