package com.company.service;

import com.company.model.Test;

import java.util.Collections;
import java.util.List;


public class TestsProcessing implements CRUDable{
    private List<Test> tests;

    public TestsProcessing(List<Test> tests){
        this.tests = tests;
    }

    @Override
    public Test CreateTest() {
        return new Test();
    }

    @Override
    public void UpdateTest(Test test) {

    }

    @Override
    public void DeleteTest(Test test) {
        tests.remove(test);
    }

    @Override
    public void SortTests() {
        Collections.sort(tests, new SortByQuestions());
    }

}
