package com.company.utils;

public enum Sex {
    UNKNOWN(-1, "ТЫ КТО??"),
    MALE(1, "Мужчина"),
    FEMALE(2, "Женщина"),
    ;
    private final int id;
    private final String desc;


    Sex(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public static Sex getById(int id) {
        for (Sex value : values()) {
            if (value.getId() == id) {
                return value;
            }
        }

        return UNKNOWN;
    }
}
