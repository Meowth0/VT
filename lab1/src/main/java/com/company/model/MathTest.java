package com.company.model;

import java.io.Serializable;
import java.util.Objects;

public class MathTest extends Test implements Serializable {
    private int levelOfDifficult;

    public int getLevelOfDifficult() {
        return levelOfDifficult;
    }

    public void setLevelOfDifficult(int levelOfDifficult) {
        this.levelOfDifficult = levelOfDifficult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MathTest mathTest = (MathTest) o;
        return levelOfDifficult == mathTest.levelOfDifficult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), levelOfDifficult);
    }

    @Override
    public String toString() {
        return "MathTest{" +
                "levelOfDifficult=" + levelOfDifficult +
                ", questions=" + super.getQuestions() +
                '}';
    }
}
