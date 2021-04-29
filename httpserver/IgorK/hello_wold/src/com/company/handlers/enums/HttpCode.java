package com.company.handlers.enums;

public enum HttpCode {

    CORRECT(200);
    private int number;

    HttpCode(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
