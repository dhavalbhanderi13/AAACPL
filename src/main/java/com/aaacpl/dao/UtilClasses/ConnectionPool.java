package com.aaacpl.dao.UtilClasses;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {
	private Vector<Connection> availableConnections = new Vector<Connection>();
	private Vector<Connection> usedConnections = new Vector<Connection>();
	private String url;
	private String driver;
	private String userName;
	private String password;
	private static ConnectionPool connectionPool = null;

	public static synchronized ConnectionPool getConnectionPool()
			throws IOException, SQLException {
		if (connectionPool == null) {
			connectionPool = new ConnectionPool();
		}
		return connectionPool;
	}

	public ConnectionPool() throws IOException, SQLException {

		// ClassLoader classLoader =
		// Thread.currentThread().getContextClassLoader();
		// Properties properties = new Properties();
		// properties.load(classLoader.getResourceAsStream("classes/com/aaacpl/dao/UtilClasses/db.properties"));

		userName = "root";
		password = "";
		url = "jdbc:mysql://localhost:3306/dummy";
		driver = "com.mysql.jdbc.Driver";

		// userName = "adminJuMUFDf";
		// password = "X3rXpzXchN2W";
		// url =
		// "jdbc:mysql://56b98d757628e1cde30000e4-theuniquemedia.rhcloud.com:44456/aaacplapi";
		// driver = "com.mysql.jdbc.Driver";

		for (int cnt = 0; cnt < 15; cnt++) {
			availableConnections.addElement(getConnection());
		}

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

	public synchronized void releasePoolConnection(Connection c) {
		if (c != null && usedConnections.contains(c)) {
			usedConnections.removeElement(c);
			availableConnections.addElement(c);
		}
	}

	public synchronized Connection getPoolConnection() throws SQLException {
		Connection newConnection = null;
		if (availableConnections.size() > 0) {
			newConnection = availableConnections.lastElement();
			availableConnections.removeElement(newConnection);
			usedConnections.addElement(newConnection);
		} else {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return newConnection;
	}
}
