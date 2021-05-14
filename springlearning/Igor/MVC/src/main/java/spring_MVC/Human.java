package spring_MVC;

import java.util.HashMap;
import java.util.Map;

public class Human {
    private String name;
    private String lastname;
    private int age;
    private String selectProfession;
    private String choiceCheckbox;
    private String radioButton;
    private Map<String,String> useRadioButton;

    public Human() {
        useRadioButton = new HashMap<>();
        useRadioButton.put("Loser","LOSER");
        useRadioButton.put("Middle","MIDDLE");
        useRadioButton.put("Boss","BOSS ");
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSelectProfession() {
        return selectProfession;
    }

    public void setSelectProfession(String selectProfession) {
        this.selectProfession = selectProfession;
    }

    public String getChoiceCheckbox() {
        return choiceCheckbox;
    }

    public void setChoiceCheckbox(String choiceCheckbox) {
        this.choiceCheckbox = choiceCheckbox;
    }

    public Map<String, String> getUseRadioButton() {
        return useRadioButton;
    }

    public void setUseRadioButton(Map<String, String> useRadioButton) {
        this.useRadioButton = useRadioButton;
    }

    public String getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(String radioButton) {
        this.radioButton = radioButton;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", ahe=" + age +
                '}';
    }
}
