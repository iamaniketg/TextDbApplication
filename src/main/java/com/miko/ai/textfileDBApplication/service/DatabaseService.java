package com.miko.ai.textfileDBApplication.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface DatabaseService {



    void parseCreateTable(String trim) throws IOException;

    void parseInsert(String trim) throws IOException;
}
