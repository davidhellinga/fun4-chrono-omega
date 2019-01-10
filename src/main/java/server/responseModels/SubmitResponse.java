package server.responseModels;

import java.util.List;

public class SubmitResponse {

    private List<String> problemWords;
    private List<String> translationWords;
    private String title;
    private String problemLanguage;
    private String translationLanguage;
    private String personEmail;

    public List<String> getProblemWords() {
        return problemWords;
    }

    public void setProblemWords(List<String> dutchWords) {
        this.problemWords = dutchWords;
    }

    public List<String> getTranslationWords() {
        return translationWords;
    }

    public void setTranslationWords(List<String> translationWords) {
        this.translationWords = translationWords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProblemLanguage() {
        return problemLanguage;
    }

    public void setProblemLanguage(String problemLanguage) {
        this.problemLanguage = problemLanguage;
    }

    public String getTranslationLanguage() {
        return translationLanguage;
    }

    public void setTranslationLanguage(String translationLanguage) {
        this.translationLanguage = translationLanguage;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }
}
