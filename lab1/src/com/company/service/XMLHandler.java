package com.company.service;

import com.company.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XMLHandler extends DefaultHandler {
    private List<Question> questions = new ArrayList<>();
    private String value;
    private List<Test> tests = new ArrayList<>();

    public List<Test> getTests() {
        return tests;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("iq") || qName.equals("language") || qName.equals("levelOfDifficult") || qName.equals("psychotype")){
            value = attributes.getValue("value");
        }
        if (qName.equals("question")) {
            Question question = new Question();
            question.setPoints(Integer.parseInt(attributes.getValue("points")));
            question.setRightAnswer(attributes.getValue("rightAnswer"));
            question.setAnswers(Arrays.asList(attributes.getValue("answers").split(" ")));
            questions.add(question);
        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("normalTest")) {
            Test test = new Test();
            test.setQuestions(questions);
            tests.add(test);
        }
        if (qName.equals("psychologicalTest")) {
            PsychologicalTest test = new PsychologicalTest();
            test.setQuestions(questions);
            test.setPsychotype(value);
            tests.add(test);
        }
        if (qName.equals("iqTest")) {
            IQTest test = new IQTest();
            test.setQuestions(questions);
            test.setIQ(Integer.parseInt(value));
            tests.add(test);
        }
        if (qName.equals("mathTest")) {
            MathTest test = new MathTest();
            test.setQuestions(questions);
            test.setLevelOfDifficult(Integer.parseInt(value));
            tests.add(test);
        }
        if (qName.equals("languageTest")) {
            LanguageTest test = new LanguageTest();
            test.setQuestions(questions);
            test.setLanguage(value);
            tests.add(test);
        }
        if (qName.equals("normalTest") || qName.equals("mathTest") || qName.equals("languageTest") || qName.equals("psychologicalTest") || qName.equals("iqTest")) {
            questions = new ArrayList<>();
            value = "";
        }
    }
}
