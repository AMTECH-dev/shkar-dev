package fox.spring.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Component
public class CardForm {
    @Size(min=100, message="Name must be minimum * symbols!")
    @NotBlank
    private String fio;

    private String chBoxResult;

    private Map<String, String> chBoxResults;

    private String notifyType;


    public CardForm() {
        chBoxResults = new HashMap<>();
        chBoxResults.put("male", "Male");
        chBoxResults.put("fema", "Female");
        chBoxResults.put("other", "Other...");
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getChBoxResult() {
        return chBoxResult;
    }

    public void setChBoxResult(String chBoxResult) {
        this.chBoxResult = chBoxResult;
    }

    public Map<String, String> getChBoxResults() {
        return chBoxResults;
    }

    public void setChBoxResults(Map<String, String> chBoxResults) {
        this.chBoxResults = chBoxResults;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    @Override
    public String toString() {
        return "CardForm{}";
    }
}