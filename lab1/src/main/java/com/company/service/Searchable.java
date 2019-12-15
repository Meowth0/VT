package com.company.service;

import com.company.model.Test;

import java.util.List;

public interface Searchable {
    List<Test> SearchByQuestions(int count);
    List<Test> SearchByKindOfTest(String kindOfTest);
}
