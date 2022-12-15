package com.emanage.exception;

import com.emanage.commons.constants.AppsConstants;

import java.util.List;

public interface BaseException {

    List<AppsErrorMessage> getAppsErrorMessages();

    int getHttpStatus();

    AppsConstants.ResponseStatus getResponseStatus();

    Boolean containsErrorCode(String errorCode);
}
