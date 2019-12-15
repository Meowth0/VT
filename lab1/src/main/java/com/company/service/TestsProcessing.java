package com.company.service;

import com.company.model.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TestsProcessing implements CRUDable, Searchable{
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

    @Override
    public List<Test> SearchByQuestions(int count) {
        List<Test> temp = new ArrayList<>();
        for (Test test : tests) {
            if (test.getQuestions() == count){
                temp.add(test);
            }
        }
        return temp;
    }

    @Override
    public List<Test> SearchByKindOfTest(String kindOfTest) {
        List<Test> temp = new ArrayList<>();
        for (Test test : tests) {
            if (test.getClass().getSimpleName().equalsIgnoreCase(kindOfTest)){
                temp.add(test);
            }
        }
        return temp;
    }

}
