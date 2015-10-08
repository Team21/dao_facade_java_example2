package autor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import autor.Autor;
import dao.Dao;

public class AutorDAO extends Dao {
	
	public void inserir(Autor autor) throws SQLException
	{
		sql = "insert into tbAutor (nome) values (?)";
		this.preparedStatement(sql);
		pstm.setString(1, autor.getNome());
		pstm.execute();
	}
	
	public void atualizar(Autor autor) throws SQLException
	{
		sql = "update tbAutor set nome = ? where id = ?";
		this.preparedStatement(sql);
		pstm.setString(1, autor.getNome());
		pstm.setInt(2, autor.getId());
		pstm.execute();
	}
	
	public List<Autor> listar() throws SQLException
	{
		List<Autor> autores = new ArrayList<Autor>();
		sql = "select * from tbAutor";
		this.preparedStatement(sql);
		
		criarLista(autores);
		return autores;
	}
	
	public void remover(Autor autor) throws SQLException
	{
		sql = "delete from tbAutor where id = ?";
		this.preparedStatement(sql);
		pstm.setInt(1, autor.getId());
		pstm.execute();
	}
	
	public Autor pesquisarPorId(int id) throws SQLException
	{
		sql = "select * from tbAutor where id = ?";
		this.preparedStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		rs.next();
		return criarAutor(rs);
	}
	
	public List<Autor> pesquisarPorNome(String nome) throws SQLException
	{
		List<Autor> autores = new ArrayList<Autor>();
		sql = "select * from tbAutor where nome like concat('%', ? , '%')";
		this.preparedStatement(sql);
		
		criarLista(autores);
		return autores;
	}

	private void criarLista(List<Autor> autores) throws SQLException
	{
		ResultSet rs = pstm.executeQuery();
		while ( rs.next() )
		{
			Autor autor = criarAutor(rs);
			
			autores.add(autor);
		}
	}

	private Autor criarAutor(ResultSet rs) throws SQLException
	{
		Autor autor = new Autor();
		autor.setId(rs.getInt("id"));
		autor.setNome(rs.getString("nome"));
		return autor;
	}
	
	
}
