package Aplicaci�n.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexi�nBD {

	Connection con=null;
	String url="jdbc:mysql://localhost:3306/empresa";
	String driver="com.mysql.jdbc.Driver"; 
	String user="root";
	String pwd="";
	
	
	public Connection ObtenerConexi�n() throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		con=DriverManager.getConnection(url,user,pwd);
		return con;
		
	}
}
