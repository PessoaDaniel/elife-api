package com.dansales.elife.elifeapi.models.enums;

public enum StatusEmail {
    SENT("sent"),
    ERROR("error");

    private final String status;

    StatusEmail(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
