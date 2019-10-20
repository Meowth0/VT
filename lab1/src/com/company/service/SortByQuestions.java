package com.company.service;

import com.company.model.Test;

import java.util.Comparator;

public class SortByQuestions implements Comparator<Test> {
    @Override
    public int compare(Test o1, Test o2) {
        return o1.getQuestions().size() - o2.getQuestions().size();
    }
}
