package com.Rai.studycenter.models;

public class mockTestModel {
    String[] question,answer,options;

    public mockTestModel() {

    }

    public mockTestModel(String[] question, String[] answer, String[] options) {
        this.question = question;
        this.answer = answer;
        this.options = options;
    }

    public String[] getQuestion() {
        return question;
    }

    public void setQuestion(String[] question) {
        this.question = question;
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
