package Aplicación.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexiónBD {

	Connection con=null;
	String url="jdbc:mysql://localhost:3306/empresa";
	String driver="com.mysql.jdbc.Driver"; 
	String user="root";
	String pwd="";
	
	
	public Connection ObtenerConexión() throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		con=DriverManager.getConnection(url,user,pwd);
		return con;
		
	}
}
