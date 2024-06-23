package com.miko.ai.textfileDBApplication.service;

import com.miko.ai.textfileDBApplication.controller.DatabaseController;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class DatabaseServiceIMPL implements DatabaseService {

    private static final String METADATA_FILE = "metadata.txt";

    public void parseCreateTable(String sql) throws IOException {
        String tableName = sql.split("\\s+")[2];
        String columnStructure = sql.substring(sql.indexOf("(") + 1, sql.indexOf(")")).trim();
        createTable(tableName, columnStructure);
    }

    public void parseInsert(String sql) throws IOException {
        String tableName = sql.split("\\s+")[2];
        String values = sql.substring(sql.indexOf("VALUES") + 7).trim();
        if (!values.endsWith(")")) {
            throw new IOException("Values syntax error: missing closing parenthesis");
        }
        values = values.substring(0, values.length() - 1);
        insertIntoTable(tableName, values);
    }

    private void createTable(String tableName, String columnStructure) throws IOException {
        String[] columns = columnStructure.split(",");
        StringBuilder metaData = new StringBuilder(tableName + ": ");
        for (String column : columns) {
            metaData.append(column.trim()).append(" ");
        }
        metaData.append("\n");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(METADATA_FILE, true))) {
            writer.write(metaData.toString());
        }
        Files.createFile(Paths.get(tableName + ".txt"));
    }

    private void insertIntoTable(String tableName, String values) throws IOException {
        String tableFile = tableName + ".txt";
        if (!Files.exists(Paths.get(tableFile))) {
            throw new IOException("Table does not exist");
        }
        if (!values.trim().endsWith(")")) {
            values = values.trim() + ")";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tableFile, true))) {
            writer.write(values + "\n");
        }
    }
}