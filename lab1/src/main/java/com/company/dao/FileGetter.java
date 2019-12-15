package com.company.dao;

import java.io.File;

public class FileGetter {
    public File getFile(String path){
        return new File(path);
    }
}
