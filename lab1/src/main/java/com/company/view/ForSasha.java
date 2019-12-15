package com.company.view;

import com.company.model.*;
import com.company.service.SAXGetter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ForSasha {
    Object[] headers = { "Type", "Questions", "AdditionalInfo" };
    private String directory = System.getProperty("user.dir");
    private String pathToSave = directory + "\\bd.txt";
    SAXGetter saxGetter = new SAXGetter();
    List<Test> tests = saxGetter.getData();
    public void writeFiles() {
        for (Test test : tests){
            List<String> temp = new ArrayList();
            temp.add(test.getClass().getSimpleName());
            temp.add(String.valueOf(test.getQuestions()));
            String value;
            value = test instanceof MathTest ? String.valueOf(((MathTest) test).getLevelOfDifficult()) : "-";
            if (value.equals("-")) {
                value = test instanceof PsychologicalTest ? String.valueOf(((PsychologicalTest) test).getPsychotype()) : "-";
            }
            if (value.equals("-")) {
                value = test instanceof IQTest ? String.valueOf(((IQTest) test).getIQ()) : "-";
            }
            if (value.equals("-")) {
                value = test instanceof LanguageTest ? String.valueOf(((LanguageTest) test).getLanguage()) : "-";
            }
            temp.add(value);

            try(FileWriter writer = new FileWriter(pathToSave, true)){
                writer.write(temp.toString() + System.getProperty("line.separator"));
            }
            catch (IOException ex){
            }
        }
    }


}
