package com.aaacpl.dao.UtilClasses;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private String url;
    private String driver;
    private String userName;
    private String password;

    public ConnectionPool() {
		/*userName = "root";
        password = "root";
		url = "jdbc:mysql://localhost:3306/newaaacpl";
		driver = "com.mysql.jdbc.Driver";*/
        userName = "adminJuMUFDf";
        password = "X3rXpzXchN2W";
        url = "jdbc:mysql://56b98d757628e1cde30000e4-theuniquemedia.rhcloud.com:44456/aaacplapi";
        driver = "com.mysql.jdbc.Driver";
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
