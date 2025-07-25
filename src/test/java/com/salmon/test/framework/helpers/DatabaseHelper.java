package com.salmon.test.framework.helpers;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Helper class for executing SQL queries using JDBC and Apache DbUtils.
 * Loads database connection properties from configuration.
 */
public class DatabaseHelper {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseHelper.class);

    // Database connection properties loaded from configuration
    private static final String jdbcUrl;
    private static final String jdbcDriver;
    private static final String jdbcUser;
    private static final String jdbcPwd;

    // Static block to initialize database properties
    static {
        jdbcUrl = LoadProperties.getProperty("jdbcUrl", null);
        jdbcDriver = LoadProperties.getProperty("jdbcDriver", null);
        jdbcUser = LoadProperties.getProperty("jdbcUser", null);
        jdbcPwd = LoadProperties.getProperty("jdbcPwd", null);

        // Validate that all required properties are present
        if (jdbcUrl == null || jdbcDriver == null || jdbcUser == null || jdbcPwd == null) {
            throw new IllegalStateException("Database connection properties are missing in configuration.");
        }
    }

    /**
     * Executes the given SQL query and returns the results as a list of maps.
     *
     * @param sqlQuery SQL query string to execute
     * @return List of result rows as maps (column name -> value)
     * @throws SQLException if query execution fails
     */
    public static List<Map<String, Object>> executeQuery(String sqlQuery) throws SQLException {
        try (Connection conn = setUpConnection()) {
            QueryRunner run = new QueryRunner();
            return run.query(conn, sqlQuery, new MapListHandler());
        } catch (SQLException e) {
            LOG.error("Error executing query: {}", sqlQuery, e);
            throw e;
        }
    }

    /**
     * Sets up and returns a JDBC connection using loaded properties.
     *
     * @return JDBC Connection
     * @throws SQLException if connection fails
     */
    private static Connection setUpConnection() throws SQLException {
        try {
            // Load the JDBC driver class
            DbUtils.loadDriver(jdbcDriver);
            // Create and return the connection
            return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd);
        } catch (SQLException se) {
            LOG.error("Error setting up database connection", se);
            throw se;
        }
    }
}
