package com.DFS.MasterNode.Services.GlobalStateService;

import com.DFS.MasterNode.DBMock.FileDBMock;
import com.DFS.MasterNode.Models.File;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class FileStateService {
//    keep track of all files and their chunks
    private  HashSet<File> fileList = new HashSet<>();
    private int totalFiles = 0; //max expected to be 100k files
    private final FileDBMock fileDBMock;

    @Autowired
    public FileStateService(FileDBMock fileDBMock){
        this.fileDBMock = fileDBMock;
    }

    @PostConstruct
    private void  init(){
        restoreKnownInfo();
    }

    private void restoreKnownInfo(){
        this.fileList.addAll(fileDBMock.getAllKnownFiles());
        totalFiles = fileList.size();
    }






}
