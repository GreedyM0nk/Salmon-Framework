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

public class DatabaseHelper {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String jdbcUrl;
    private static final String jdbcDriver;
    private static final String jdbcUser;
    private static final String jdbcPwd;

    static {
        LoadProperties loadProperties = new LoadProperties();
        jdbcUrl = loadProperties.getProperty("jdbcUrl");
        jdbcDriver = loadProperties.getProperty("jdbcDriver");
        jdbcUser = loadProperties.getProperty("jdbcUser");
        jdbcPwd = loadProperties.getProperty("jdbcPwd");
    }

    /**
     * Executes the sql Query and returns the results in list format
     *
     * @param sqlQuery Specify sql query in String format
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
