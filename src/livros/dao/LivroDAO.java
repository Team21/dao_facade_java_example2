package livros.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import livros.Livro;
import autor.Autor;
import dao.Dao;

public class LivroDAO extends Dao {
	
	public void inserir(Livro livro) throws SQLException
	{
		sql = "insert into tbLivro (nome, editora, edicao, qtdExemplares, isbn, resumo, ano, id_autor) values (?,?,?,?,?,?,?,?)";
		this.preparedStatement(sql);
		configurarPstmComLivro(livro, false);
		pstm.execute();
	}
	
	public void atualizar(Livro livro) throws SQLException
	{
		sql = "update tbLivro set nome = ?, editora = ?, " +
				"edicao = ?, qtdExemplares = ?, isbn = ?, " +
				"resumo = ?, ano = ?, id_autor = ? where id = ?";
		this.preparedStatement(sql);
		configurarPstmComLivro(livro, true);
		pstm.execute();
	}

	private void configurarPstmComLivro(Livro livro, boolean atualizar) throws SQLException
	{
		pstm.setString(1, livro.getNomeLivro());
		pstm.setString(2, livro.getEditora());
		pstm.setInt(3, livro.getEdicao());
		pstm.setInt(4, livro.getQuantidadeExemplares());
		pstm.setInt(5, livro.getIsbn());
		pstm.setString(6, livro.getResumo());
		pstm.setInt(7, livro.getAno());
		pstm.setInt(8, livro.getAutor().getId());
		if ( atualizar )
		{
			pstm.setInt(9, livro.getId());
		}
		
	}
	
	public List<Livro> listar() throws SQLException
	{
		List<Livro> livros = new ArrayList<Livro>();
		sql = "select * from tbLivro";
		this.preparedStatement(sql);
		
		criarLista(livros);
		return livros;
	}
	
	public void remover(Livro livro) throws SQLException
	{
		sql = "delete from tbLivro where id = ?";
		this.preparedStatement(sql);
		pstm.setInt(1, livro.getId());
		pstm.execute();
	}
	
	public Livro pesquisarPorId(int id) throws SQLException
	{
		sql = "select * from tbLivro where id = ?";
		this.preparedStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		rs.next();
		return criarLivro(rs);
	}
	
	public List<Livro> pesquisarPorNome(String nome) throws SQLException
	{
		List<Livro> livros = new ArrayList<Livro>();
		sql = "select * from tbLivro where nome like concat('%', ? , '%')";
		this.preparedStatement(sql);
		
		criarLista(livros);
		return livros;
	}

	private void criarLista(List<Livro> livros) throws SQLException
	{
		ResultSet rs = pstm.executeQuery();
		while ( rs.next() )
		{
			Livro livro = criarLivro(rs);
			
			livros.add(livro);
		}
	}

	private Livro criarLivro(ResultSet rs) throws SQLException
	{
		Livro livro = new Livro();
		livro.setId(rs.getInt("id"));
		livro.setNomeLivro(rs.getString("nome"));
		livro.setAno(rs.getInt("ano"));
		livro.setEdicao(rs.getInt("edicao"));
		livro.setIsbn(rs.getInt("isbn"));
		livro.setQuantidadeExemplares(rs.getInt("qtdExemplares"));
		livro.setResumo(rs.getString("resumo"));
		
		Autor autor = new Autor();
		autor.setId(rs.getInt("id_autor"));
		
		livro.setAutor(autor);
		return livro;
	}
}
