package illnesses;

public interface Symptom {
    boolean isHard();

    boolean hasConsequences();

    boolean canStopByItself();

    boolean needOperation();
}
