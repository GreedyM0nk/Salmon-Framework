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
 */
public class DatabaseHelper {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String jdbcUrl;
    private static final String jdbcDriver;
    private static final String jdbcUser;
    private static final String jdbcPwd;

    static {
        LoadProperties loadProperties = new LoadProperties();
        jdbcUrl = loadProperties.getProperty("jdbcUrl", null);
        jdbcDriver = loadProperties.getProperty("jdbcDriver", null);
        jdbcUser = loadProperties.getProperty("jdbcUser", null);
        jdbcPwd = loadProperties.getProperty("jdbcPwd", null);

        if (jdbcUrl == null || jdbcDriver == null || jdbcUser == null || jdbcPwd == null) {
            throw new IllegalStateException("Database connection properties are missing in configuration.");
        }
    }

    /**
     * Executes the SQL query and returns the results in list format.
     *
     * @param sqlQuery SQL query string
     * @return List of result rows as maps
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

    private static Connection setUpConnection() throws SQLException {
        try {
            DbUtils.loadDriver(jdbcDriver);
            return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd);
        } catch (SQLException se) {
            LOG.error("Error setting up database connection", se);
            throw se;
        }
    }
}
