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

public class DatabaseHelper {

    private static Connection conn = null;
    private static  final Logger LOG = LoggerFactory.getLogger(DatabaseHelper.class);


    public static List executeQuery(String sqlQuery) throws SQLException {
        conn = setUpConnection();
        QueryRunner run = new QueryRunner();
        return run.query(conn, sqlQuery, new MapListHandler());
    }

    private static Connection setUpConnection() {
        String jdbcUrl = LoadProperties.getRunProps().getProperty("jdbcUrl");
        String jdbcDriver = LoadProperties.getRunProps().getProperty("jdbcDriver");
        String jdbcUser = LoadProperties.getRunProps().getProperty("jdbcUser");
        String jdbcPwd = LoadProperties.getRunProps().getProperty("jdbcPwd");

        try {
            DbUtils.loadDriver(jdbcDriver);
            return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd);
        } catch (SQLException se) {
            LOG.info(se.getMessage());

        } finally {
            DbUtils.closeQuietly(conn);
        }
        return conn;
    }
}
