package com.company.model;

import java.io.Serializable;
import java.util.Objects;

public class PsychologicalTest extends Test implements Serializable {
    private String psychotype;

    public String getPsychotype() {
        return psychotype;
    }

    public void setPsychotype(String psychotype) {
        this.psychotype = psychotype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PsychologicalTest that = (PsychologicalTest) o;
        return Objects.equals(psychotype, that.psychotype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), psychotype);
    }

    @Override
    public String toString() {
        return "PsychologicalTest{" +
                "psychotype='" + psychotype + '\'' +
                ", questions=" + super.getQuestions() +
                '}';
    }
}
