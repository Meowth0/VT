package com.company.view;

import com.company.model.Test;

import java.util.List;

public class ConsoleView implements View{

    @Override
    public void ShowTests(List<Test> tests) {
        System.out.println(tests);
    }
}
