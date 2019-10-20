package com.company.controller;


import com.company.model.Test;
import com.company.service.SAXGetter;
import com.company.service.TestsProcessing;
import com.company.view.ConsoleView;
import com.company.view.View;
import java.util.List;

public class Controller {
    private SAXGetter saxGetter = new SAXGetter();
    private View view = new ConsoleView();
    private TestsProcessing testsProcessing;
    private List<Test> tests;

    public void execute(){
        tests = saxGetter.getData();
        testsProcessing = new TestsProcessing(tests);
        testsProcessing.SortTests();
        view.ShowTests(testsProcessing.SearchByKindOfTest("languageTest"));
    }
}
