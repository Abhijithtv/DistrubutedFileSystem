package com.DFS.MasterNode.DBMock;

import com.DFS.MasterNode.Models.File;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileDBMock {

    List<File> fileList;

    public FileDBMock(){
        fileList = new ArrayList<>();
    }

    public List<File> getAllKnownFiles(){
        return fileList;
    }
}
