package server.responseModels;

public class SubmitResultResponse {
    int wordListId;
    int score;
    int total;
    String email;

    public int getWordListId() {
        return wordListId;
    }

    public void setWordListId(int wordListId) {
        this.wordListId = wordListId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
