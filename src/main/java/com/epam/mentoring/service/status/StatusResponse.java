package com.epam.mentoring.service.status;

public enum StatusResponse {
    SUCCESS(1, 200), NOT_FOUND(0, 200), UNAUTHORIZED(-1, 401), BAD_REQUEST(-2, 400);

    private Integer code;
    private Integer httpStatus;

    StatusResponse(Integer code, Integer httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public Integer getCode() {
        return code;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public static Integer getHttpStatusByCustomCode(int customCode) {
        for (StatusResponse value : StatusResponse.values()) {
            if(value.getCode() == customCode) {
                return value.getHttpStatus();
            }
        }

        return 400;
    }
}
