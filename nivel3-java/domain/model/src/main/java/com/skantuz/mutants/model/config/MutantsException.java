package com.skantuz.mutants.model.config;

public class MutantsException extends RuntimeException{
    private final int status;
    private final String log;
    private final String code;
    private final String title;

    public MutantsException(MutantsErrorCode errorCode, String log, String message) {
        super(message);
        this.status = errorCode.status();
        this.log = log;
        this.code = errorCode.errorCode();
        this.title = errorCode.title();
    }

    public int getStatus() {
        return status;
    }

    public String getLog() {
        return log;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
