package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.ConexaoMysql;

public abstract class Dao
{
	protected Connection conn;
	protected PreparedStatement pstm;
	protected String sql;
	
	public Dao()
	{
		conn = ConexaoMysql.getConnection();
	}
	
	protected void preparedStatement(String sql)
	{
		try
		{
			pstm = conn.prepareStatement(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
