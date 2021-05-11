package clients;

public enum Illness {
    FRACTURE("fracture"),
    VIRUS("virus"),
    GASTRITIS("gastritis"),
    TOOTHACHE("toothache"),
    NEED_VACCINATION("need vaccination"),
    NONE("none");

    private final String nameOfIllness;

    Illness(String nameOfIllness) {
        this.nameOfIllness = nameOfIllness;
    }

    public String getNameOfIllness() {
        return nameOfIllness;
    }
}
