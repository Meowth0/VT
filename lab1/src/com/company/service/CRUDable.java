package com.company.service;

import com.company.model.Test;

import java.util.List;

public interface CRUDable {
    Test CreateTest();
    void UpdateTest(Test test);
    void DeleteTest(Test test);
    void SortTests();
}
