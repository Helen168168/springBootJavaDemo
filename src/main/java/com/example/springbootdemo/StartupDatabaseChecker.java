package com.example.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class StartupDatabaseChecker implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            if (conn != null) {
                System.out.println("collect successfully");
            } else {
                System.err.println("connection fail");
            }
        } catch (SQLException e) {
            System.err.println("connection error：" + e.getMessage());
        }
    }
}
