package com.company.illnesses;

public enum Diagnose {
    FRACTURE(1),
    VIRUS(2),
    GASTROINTESTINAL_DISEASES(3),
    TOOTHACHE(4),
    NEED_VACCINATION(5),
    NONE(6);

    private final int id;

    Diagnose(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
