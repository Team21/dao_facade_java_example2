package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoMysql {
	
	private static Connection conn;
	
	private ConexaoMysql()
	{
	}
	
	public synchronized static Connection getConnection()
	{
		if ( conn == null )
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbBiblioteca" , "root" , "root" );
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public synchronized static void encerrarConexao(){
		try
		{
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
