package tg.dataBase;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class oracleJBDC {
   private Connection connection = null;
   
   public Connection getConnection()
   {
	   return connection;
   }

public void connect()
{
	 try {

         Class.forName("oracle.jdbc.driver.OracleDriver");

     } catch (ClassNotFoundException e) {

         System.out.println("Where is your Oracle JDBC Driver?");
         e.printStackTrace();
         return;

     }

     System.out.println("Oracle JDBC Driver Registered!");

    // Connection connection = null;
     connection = null;

     try {

         connection = DriverManager.getConnection(
                 "jdbc:oracle:thin:@localhost:1522:xe", "hr", "hr");

     } catch (SQLException e) {

         System.out.println("Connection Failed! Check output console");
         e.printStackTrace();
         return;

     }

     if (connection != null) {
         System.out.println("You made it, take control your database now!");
     } else {
         System.out.println("Failed to make connection!");
     }
}

public void disconnect() 
{
	if(connection!=null) {
		try {
			connection.close();
			System.out.println("Connection closed!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}}

}
