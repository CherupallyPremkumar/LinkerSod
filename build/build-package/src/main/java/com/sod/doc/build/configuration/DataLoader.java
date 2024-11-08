//package com.sod.doc.build.configuration;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.init.ScriptUtils;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//@Component
//public class DataLoader {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @PostConstruct
//    public void init() {
//        try {
//            // Load and execute the SQL script
//            ScriptUtils.executeSqlScript(dataSource.getConnection(),
//                    new ClassPathResource("data/data.sql"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
