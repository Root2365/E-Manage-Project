package com.emanage.commons.constants;

import org.apache.commons.lang3.StringUtils;

public class AppsConstants {

    public enum Status {
        ACT("Active"),
        INA("Inactive");

        private final String description;

        Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ResponseStatus {
        SUCCESS, FAILED;

        public static ResponseStatus resolveStatus(String statusStr) {
            ResponseStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ResponseStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }
    }

    public enum AuthorizationError {
        USER_UNAUTHORIZED("User is unauthorized for action");

        private String description;

        AuthorizationError(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum UserType {
        ADMIN, USER
    }

    public enum YesNo {
        Y("Yes"),
        N("No");

        private final String description;

        YesNo(String description) {
            this.description = description;
        }

        public static YesNo resolveYesNo(String str) {
            YesNo matchingStr = null;
            if (!StringUtils.isEmpty(str)) {
                matchingStr = YesNo.valueOf(str.trim());
            }
            return matchingStr;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Gender {
        M("Male"),
        F("Female");

        private final String description;

        Gender(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum LeaveStatus {
        APPROVED("Approved"),
        NOT_APPROVED("Not Approved");

        private final String description;

        LeaveStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
