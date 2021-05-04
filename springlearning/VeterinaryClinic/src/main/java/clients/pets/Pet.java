package clients.pets;

import illnesses.Diagnose;

public abstract class Pet {
    private final String name;
    private final int ageInMonth;
    private Diagnose diagnose;

    public Pet(String name, int ageInMonth, Diagnose diagnose) {
        this.name = name;
        this.ageInMonth = ageInMonth;
        this.diagnose = diagnose;
    }

    public boolean isSick() {
        if (diagnose.equals(Diagnose.NONE))
            System.out.println(this.name + " is healthy!");
        else System.out.println(this.name + " is sick!");
        return diagnose != null;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }
    public Diagnose getDiagnose() {
        return diagnose;
    }

    public String getName() {
        return name;
    }

    public int getAgeInMonth() {
        return ageInMonth;
    }

    public void sleep() {
        System.out.println(this.name + " is sleeping.");
    }

    @Override
    public String toString() {
        return name + "(" + this.getClass().getSimpleName() + ")";
    }
}
