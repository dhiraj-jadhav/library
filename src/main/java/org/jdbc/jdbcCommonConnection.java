package org.jdbc;

import java.sql.*;

public class jdbcCommonConnection implements  dbSetting{
    private static Connection con;
    private static Statement stmt;

    public static Connection getJdbcConnect(){

        try{
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL,USER,PASS);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return  con;
    }
    public static  Statement getStmt(){
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

}
