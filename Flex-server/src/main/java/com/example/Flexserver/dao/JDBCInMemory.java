package com.example.Flexserver.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;

@Component
@ConfigurationProperties(prefix = "key.datasource")
@Slf4j
public class JDBCInMemory {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver"; //org.h2.Driver

    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    @PostConstruct
    public void createTable() {
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER );

            //STEP 3: Open a connection
            log.info("Connecting to a selected database... : {}");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            log.info("Connected database successfully... : {}");

            //STEP 4: Execute a query
            log.info("Creating table in given database... : {}");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS USER_APP_KEY " +
                    "(app_id VARCHAR(255) not NULL, " +
                    " public_key VARCHAR(MAX), " +
                    " private_key VARCHAR(MAX)) ";

            stmt.executeUpdate(sql);
            log.info("Created table in given database... : {}");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt!=null)
                    conn.close();
            } catch (SQLException se) {
            } // do nothing
            try {
                if (conn!= null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        //this.insertData();
    } // end main

    public void insertData (String appId, String publicKey, String privateKey) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            log.info("Connecting to a selected database... : {}");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            log.info("Connected database successfully... : {}");

            //STEP 4: Execute a query
            log.info("Inserting records into the table... : {}");
            stmt = conn.createStatement();
            //if appid exist
            String sql = "SELECT APP_ID FROM USER_APP_KEY WHERE APP_ID='"+appId+"'";
            ResultSet resultSet = stmt.executeQuery(sql);
            if(!resultSet.isBeforeFirst())
            {
                stmt.executeUpdate("INSERT INTO USER_APP_KEY " + "VALUES ('"+appId+"', '"+publicKey+"', '"+privateKey+"')");
                resultSet.close();
            }
            else
            {
                stmt.executeUpdate("UPDATE USER_APP_KEY SET PUBLIC_KEY='"+publicKey+"', PRIVATE_KEY='"+privateKey+"' WHERE APP_ID='"+appId+"' ");
                resultSet.close();
            }
            log.info("Inserted records into the table... : {}");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt!=null)
                    conn.close();
            } catch (SQLException se) {
            } // do nothing
            try {
                if (conn!=null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try

    } //end main


    public String getPrivateKeyForAppId (String appId) {
        Connection conn = null;
        Statement stmt = null;
        String privateKey = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            log.info("Connecting to a selected database... : {}");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            log.info("Connected database successfully... : {}");

            //STEP 4: Execute a query
            log.info("Reading record from the table... : {}");
            stmt = conn.createStatement();
            //if appid exist
            String sql = "SELECT PRIVATE_KEY FROM USER_APP_KEY WHERE APP_ID='"+appId+"'";
            ResultSet resultSet = stmt.executeQuery(sql);

            while( resultSet.next()){
                privateKey = resultSet.getString("PRIVATE_KEY");

            }
            resultSet.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt!=null)
                    conn.close();
            } catch (SQLException se) {
            } // do nothing
            try {
                if (conn!=null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try

        return privateKey;

    } //end main
}