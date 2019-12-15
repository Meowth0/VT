package com.company.model;

import java.io.Serializable;
import java.util.Objects;

public class IQTest extends Test implements Serializable {
    private int IQ;

    public int getIQ() {
        return IQ;
    }

    public void setIQ(int IQ) {
        this.IQ = IQ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IQTest iqTest = (IQTest) o;
        return IQ == iqTest.IQ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), IQ);
    }

    @Override
    public String toString() {
        return "IQTest{" +
                "IQ=" + IQ +
                ", questions=" + super.getQuestions() +
                '}';
    }
}
