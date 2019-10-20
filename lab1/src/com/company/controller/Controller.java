package com.company.controller;


import com.company.model.Test;
import com.company.service.SAXGetter;
import com.company.service.TestsProcessing;
import com.company.view.ConsoleView;
import com.company.view.View;
import java.util.List;

public class Controller {
    private SAXGetter saxProcessing = new SAXGetter();
    private View view = new ConsoleView();
    private TestsProcessing testsProcessing;

    public void execute(){
        List<Test> tests = saxProcessing.getData();
        testsProcessing = new TestsProcessing(tests);
        testsProcessing.SortTests();
        view.ShowTests(tests);
    }
}
