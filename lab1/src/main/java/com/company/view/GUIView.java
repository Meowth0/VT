package com.company.view;

import com.company.dao.FileGetter;
import com.company.model.*;
import com.company.service.SAXGetter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static javax.swing.BoxLayout.PAGE_AXIS;

public class GUIView extends JFrame {
    Object[] headers = { "Type", "Questions", "AdditionalInfo" };
    private String directory = System.getProperty("user.dir");
    private String pathToSave = directory + "\\bd.txt";

    public GUIView(String s){
        super(s);
        try(FileWriter writer = new FileWriter(pathToSave, false)){
            writer.write("");
        }
        catch (IOException ex){
        }

        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        final JTable searchResult = new JTable(new DefaultTableModel(null, headers));

        //new GridLayout(3, 1, 0, 2)
        JPanel grid1 = new JPanel();
        grid1.setBackground(new Color(100, 100 ,100));
        grid1.setLayout(new BoxLayout(grid1, PAGE_AXIS));
        final JTable jTable = new JTable(new DefaultTableModel(null, headers));
        JButton butGetTable = new JButton("GetTestsFromFile");
        butGetTable.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        butGetTable.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        butGetTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SAXGetter saxGetter = new SAXGetter();
                List<Test> tests = saxGetter.getData();
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                model.setRowCount(0);
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
                    model.addRow(temp.toArray());
                }
            }
        });

        grid1.add(jTable);
        grid1.add(new JScrollPane(jTable));
        grid1.add(butGetTable);

        //new GridLayout(3, 1, 0 ,3)
        JPanel grid2 = new JPanel();
        grid2.setBackground(new Color(100, 100 ,100));
        grid2.setLayout(new BoxLayout(grid2, PAGE_AXIS));
        String[] actions = {"Create", "Update", "Delete", "Search"};
        final JComboBox action = new JComboBox(actions);
        action.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        action.setPreferredSize(new Dimension(300,30));
        String[] kinds = {"Test", "PsychologicalTest", "MathTest", "LanguageTest", "IQTest"};
        final JComboBox kindOfTest = new JComboBox(kinds);
        kindOfTest.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        kindOfTest.setPreferredSize(new Dimension(300,30));
        final JTextField questionCount = new JTextField();
        questionCount.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        questionCount.setPreferredSize(new Dimension(300,30));
        final JTextField additionallyField = new JTextField();
        additionallyField.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        additionallyField.setPreferredSize(new Dimension(300,30));
        JButton execute = new JButton("Execute");
        execute.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        execute.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                DefaultTableModel modelSearch = (DefaultTableModel) searchResult.getModel();
                switch (action.getSelectedIndex()){
                    case 0:{
                        List<String> temp = new ArrayList<>();
                        if (kindOfTest.getSelectedIndex() != 0){
                            if (!questionCount.getText().equals("") && !additionallyField.getText().equals("")){
                                temp.add(String.valueOf(kindOfTest.getSelectedItem()));
                                temp.add(questionCount.getText());
                                temp.add(additionallyField.getText());
                                model.addRow(temp.toArray());
                            }
                        }
                        else{
                            if (!questionCount.getText().equals("")){
                                temp.add(String.valueOf(kindOfTest.getSelectedItem()));
                                temp.add(questionCount.getText());
                                temp.add("-");
                                model.addRow(temp.toArray());
                            }
                        }
                        try(FileWriter writer = new FileWriter(pathToSave, true)){
                            writer.write(temp.toString() + System.getProperty("line.separator"));
                        }
                        catch (IOException ex){
                        }
                        break;
                    }
                    case 1:{
                        Scanner scan;
                        StringBuilder sb = new StringBuilder();
                        int temp = 0;
                        try {
                            scan = new Scanner(new File(pathToSave));
                            while(scan.hasNext()){
                                if (temp == jTable.getSelectedRow()){
                                    temp++;
                                    String str = scan.nextLine();
                                    String[] strings = str.substring(1, str.length() - 1).split(", ");
                                    strings[0] = kindOfTest.getSelectedItem().toString();
                                    strings[1] = questionCount.getText();
                                    strings[2] = kindOfTest.getSelectedIndex() != 0 ? additionallyField.getText() : "-";
                                    sb.append("[" + strings[0] + ", " + strings[1] +  ", " + strings[2] +"]" + System.getProperty("line.separator"));
                                    continue;
                                }
                                sb.append(scan.nextLine() + System.getProperty("line.separator"));
                                temp++;
                            }

                        } catch (Exception ex) {

                        }
                        try(FileWriter writer = new FileWriter(pathToSave, false)){
                            writer.write(sb.toString());
                        }
                        catch (IOException ex){
                        }

                        model.setValueAt(kindOfTest.getSelectedItem(), jTable.getSelectedRow(), 0);
                        model.setValueAt(questionCount.getText(), jTable.getSelectedRow(), 1);
                        if (kindOfTest.getSelectedIndex() != 0) {
                            model.setValueAt(additionallyField.getText(), jTable.getSelectedRow(), 2);
                        }
                        else
                            model.setValueAt("", jTable.getSelectedRow(), 2);
                        break;
                    }
                    case 2:{
                        Scanner scan;
                        StringBuilder sb = new StringBuilder();
                        int temp = 0;
                        try {
                            scan = new Scanner(new File(pathToSave));
                            while(scan.hasNext()){
                                if (temp == jTable.getSelectedRow()){
                                    temp++;
                                    scan.nextLine();
                                    continue;
                                }
                                sb.append(scan.nextLine() + System.getProperty("line.separator"));
                                temp++;
                            }

                        } catch (Exception ex) {

                        }
                        try(FileWriter writer = new FileWriter(pathToSave, false)){
                            writer.write(sb.toString());
                        }
                        catch (IOException ex){
                        }
                        model.removeRow(jTable.getSelectedRow());
                        break;
                    }
                    case 3:{
                        modelSearch.setRowCount(0);
                        if (!questionCount.getText().equals("") && !additionallyField.getText().equals("")) {
                            int i = 0;
                            while(i < model.getRowCount()){
                                if (model.getValueAt(i, 1).equals(questionCount.getText()) && model.getValueAt(i, 2).equals(additionallyField.getText())){
                                    List<String> temp = new ArrayList<>();
                                    temp.add(String.valueOf(model.getValueAt(i, 0)));
                                    temp.add(String.valueOf(model.getValueAt(i, 1)));
                                    temp.add(String.valueOf(model.getValueAt(i, 2)));
                                    modelSearch.addRow(temp.toArray());
                                }
                                i++;
                            }
                            break;
                        }
                            if (!questionCount.getText().equals("")){
                                int i = 0;
                                while(i < model.getRowCount()){
                                    if (model.getValueAt(i, 1).equals(questionCount.getText())){
                                        List<String> temp = new ArrayList<>();
                                        temp.add(String.valueOf(model.getValueAt(i, 0)));
                                        temp.add(String.valueOf(model.getValueAt(i, 1)));
                                        temp.add(String.valueOf(model.getValueAt(i, 2)));
                                        modelSearch.addRow(temp.toArray());
                                    }
                                    i++;
                                }
                            }
                            if (!additionallyField.getText().equals("")){
                                int i = 0;
                                while(i < model.getRowCount()){
                                    if (model.getValueAt(i, 2).equals(additionallyField.getText())){
                                        List<String> temp = new ArrayList<>();
                                        temp.add(String.valueOf(model.getValueAt(i, 0)));
                                        temp.add(String.valueOf(model.getValueAt(i, 1)));
                                        temp.add(String.valueOf(model.getValueAt(i, 2)));
                                        modelSearch.addRow(temp.toArray());
                                    }
                                    i++;
                                }
                            }
                    }
                }
            }
        });
        JLabel qCount = new JLabel("Questions count");
        qCount.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        qCount.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        JLabel aInfo = new JLabel("Additional info");
        aInfo.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        aInfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);



        grid2.add(action);
        grid2.add(Box.createVerticalStrut(20));
        grid2.add(kindOfTest);
        grid2.add(Box.createVerticalStrut(20));
        grid2.add(qCount);
        grid2.add(questionCount);
        grid2.add(Box.createVerticalStrut(10));
        grid2.add(aInfo);
        grid2.add(additionallyField);
        grid2.add(Box.createVerticalStrut(30));
        grid2.add(execute);


        JPanel grid3 = new JPanel();
        grid3.setBackground(new Color(100, 100 ,100));
        grid3.setLayout(new BoxLayout(grid3, PAGE_AXIS));
        grid3.add(searchResult);
        grid3.add(new JScrollPane(searchResult));

        JPanel flow = new JPanel(new FlowLayout());
        flow.setPreferredSize(new Dimension(1000, 1000));
        flow.setBackground(new Color(100,2,3));
        flow.add(grid1);
        flow.add(Box.createHorizontalStrut(100));
        flow.add(grid2);
        flow.add(grid3);



        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        container.setBackground(new Color(0, 100 ,100));
        container.add(flow);



        this.setVisible(true);
    }

}
