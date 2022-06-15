package com.study.studyprojects.codenamesserver.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcConnection {
    private static Connection connection = null;

    static {

        try(InputStream is = JdbcConnection.class.getResourceAsStream("/application.properties")) {

            Properties props = new Properties();
            props.load(is);

            String datasourceUrl = props.getProperty("datasource.url");
            String datasourceUsername = props.getProperty("datasource.username");
            String datasourcePassword = props.getProperty("datasource.password");

            connection = DriverManager.getConnection(datasourceUrl, datasourceUsername, datasourcePassword);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return connection;
    }

}
