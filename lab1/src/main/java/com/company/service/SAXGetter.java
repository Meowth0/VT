package com.company.service;

import com.company.dao.FileGetter;
import com.company.model.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SAXGetter implements DataGetter {
    private String directory = System.getProperty("user.dir");
    private String testsPath = directory + "\\tests.xml";
    private String xsdPath = directory + "\\xsdSchema.xsd";

    public List<Test> getData(){

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        XMLHandler handler = new XMLHandler();
        try {
            FileGetter fileGetter = new FileGetter();
            if (new XsdValidator().validateXMLByXSD(fileGetter.getFile(testsPath), fileGetter.getFile(xsdPath)))
                parser.parse(fileGetter.getFile(testsPath), handler);
            else {
                System.out.println("error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return handler.getTests();
    }
}
