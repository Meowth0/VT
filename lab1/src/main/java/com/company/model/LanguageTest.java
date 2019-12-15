package com.company.model;

import java.io.Serializable;
import java.util.Objects;

public class LanguageTest extends Test implements Serializable {
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LanguageTest that = (LanguageTest) o;
        return Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), language);
    }

    @Override
    public String toString() {
        return "LanguageTest{" +
                "language='" + language + '\'' +
                ", questions=" + super.getQuestions() +
                '}';
    }
}
