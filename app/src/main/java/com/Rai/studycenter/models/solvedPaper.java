package com.Rai.studycenter.models;

public class solvedPaper {
    String question,answers;

    public solvedPaper() {
    }

    public solvedPaper(String question, String answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
