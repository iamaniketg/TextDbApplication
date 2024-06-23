package com.miko.ai.textfileDBApplication.controller;

import com.miko.ai.textfileDBApplication.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DatabaseController {
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @PostMapping("/execute")
    public String executeSql(@RequestBody String sql) {
        log.info("Received SQL: {}", sql);
        String result;
        try {
            if (sql.trim().startsWith("CREATE TABLE")) {
                databaseService.parseCreateTable(sql.trim());
                redisTemplate.opsForValue().increment("SUCCESS");
                result = "Table created successfully";
            } else if (sql.trim().startsWith("INSERT INTO")) {
                databaseService.parseInsert(sql.trim());
                redisTemplate.opsForValue().increment("SUCCESS");
                result = "Data inserted successfully";
            } else {
                redisTemplate.opsForValue().increment("FAILURE");
                result = "Invalid SQL statement";
            }
        } catch (Exception e) {
            redisTemplate.opsForValue().increment("FAILURE");
            result = "Error: " + e.getMessage();
            log.error("Error executing SQL: {}", e.getMessage());
        }
        return result;
    }



}
