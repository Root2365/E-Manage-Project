package com.emanage.controller;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.model.security.EManageUserDetails;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    protected CredentialsDTO getCredentialsDTO() throws AccessDeniedException {

        CredentialsDTO credentialsDTO = new CredentialsDTO();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessDeniedException(AppsConstants.AuthorizationError.USER_UNAUTHORIZED.getDescription());
        }

        EManageUserDetails userDetails = (EManageUserDetails) authentication.getPrincipal();

        credentialsDTO.setUserID(userDetails.getUserId());
        credentialsDTO.setUserName(userDetails.getUsername());
        credentialsDTO.setRequestIpAddress(request.getRemoteAddr());
        credentialsDTO.setAuthorities(userDetails.getAuthorities());

        return credentialsDTO;
    }

    protected boolean isAuthorized(String privilegeCode) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EManageUserDetails userDetails = (EManageUserDetails) authentication.getPrincipal();

        return userDetails.getPrivileges().contains(privilegeCode);
    }
}
