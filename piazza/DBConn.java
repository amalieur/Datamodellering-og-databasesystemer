package piazza;

import java.sql.*;
import java.util.Properties;

/**
 * The class that connects our code to our database
 */
public abstract class DBConn{

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/prosjekt?autoReconnect=true&useSSL=false";


    // Database credentials
    static final String USER = "root";
    static final String PASS = "";
    // We will not display the password here

    protected Connection conn;
    public DBConn(){
    }
    public void connect(){
        try{
	        Class.forName(JDBC_DRIVER); 
	        // Properties for user and password.
            Properties p = new Properties();
            p.put("user", USER);
            p.put("password", PASS);           
            conn = DriverManager.getConnection(DB_URL, p);
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
        }
    }
}