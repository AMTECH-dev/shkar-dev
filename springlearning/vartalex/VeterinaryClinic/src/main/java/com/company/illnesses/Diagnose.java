package com.company.illnesses;

public enum Diagnose {
    FRACTURE("fracture"),
    VIRUS("virus"),
    GASTROINTESTINAL_DISEASES("gastritis"),
    TOOTHACHE("toothache"),
    NEED_VACCINATION("need vaccination"),
    NONE("none");

    private final String nameOfIllness;

    Diagnose(String nameOfIllness) {
        this.nameOfIllness = nameOfIllness;
    }

    public String getNameOfIllness() {
        return nameOfIllness;
    }
}
