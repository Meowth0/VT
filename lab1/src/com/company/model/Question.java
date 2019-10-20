package com.company.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Question implements Serializable {
    private int points;
    private List<String> answers;
    private String rightAnswer;

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return points == question.points &&
                Objects.equals(rightAnswer, question.rightAnswer) &&
                Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rightAnswer, answers, points);
    }

    @Override
    public String toString() {
        return "Question{" +
                "rightAnswer='" + rightAnswer + '\'' +
                ", answers=" + answers +
                ", points=" + points +
                '}';
    }
}
