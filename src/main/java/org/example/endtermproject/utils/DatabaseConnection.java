package org.example.endtermproject.utils;

import org.example.endtermproject.patterns.DbConfigSingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        DbConfigSingleton cfg = DbConfigSingleton.getInstance();
        return DriverManager.getConnection(cfg.url(), cfg.user(), cfg.password());
    }
}
