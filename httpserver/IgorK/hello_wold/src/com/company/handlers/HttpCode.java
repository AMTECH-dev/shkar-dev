package com.company.handlers;

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
