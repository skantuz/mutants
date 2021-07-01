package com.skantuz.mutants.model.config;

public enum MutantsErrorCode {

    B000(400,"B000","Error en la data enviada"),
    E000(500,"E000","Upps esto no deberia ocurrir");
    private final int status;
    private final String errorCode;
    private final String title;

    MutantsErrorCode(int status, String errorCode, String title) {
        this.status = status;
        this.errorCode = errorCode;
        this.title = title;
    }

    public int status() {
        return status;
    }

    public String errorCode() {
        return errorCode;
    }

    public String title() {
        return title;
    }
}
