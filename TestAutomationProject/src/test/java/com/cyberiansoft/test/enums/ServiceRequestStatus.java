package com.cyberiansoft.test.enums;

import lombok.Getter;

@Getter
public enum ServiceRequestStatus {

    ON_HOLD("On Hold"),
    SCHEDULED("Scheduled"),
    PROPOSED("Proposed");

    private String value;

    ServiceRequestStatus(String value) {
        this.value = value;
    }

}