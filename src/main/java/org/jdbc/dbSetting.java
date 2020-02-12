package org.jdbc;

public interface dbSetting {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/bookdb?autoReconnect=true&useSSL=false";
    public  static final String USER = "admin";
    public static final String PASS = "admin";
}
