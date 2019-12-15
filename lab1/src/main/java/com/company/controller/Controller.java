package com.company.controller;


import com.company.model.PsychologicalTest;
import com.company.model.Test;
import com.company.service.SAXGetter;
import com.company.service.TestsProcessing;
import com.company.view.ConsoleView;
import com.company.view.GUIView;
import com.company.view.View;
import com.company.view.WebView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Controller {
    private SAXGetter saxGetter = new SAXGetter();
    private View view = new ConsoleView();
    private TestsProcessing testsProcessing;
    private List<Test> tests;
    private String directory = System.getProperty("user.dir");
    private String pathToSave = directory + "\\bd.txt";

    public void execute(){
        GUIView guiView = new GUIView("Lab1");
        //WebView webView = new WebView();
        tests = saxGetter.getData();
        testsProcessing = new TestsProcessing(tests);
        testsProcessing.SortTests();
        view.ShowTests(tests);
    }
}
