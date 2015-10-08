package livros.facade;

import java.sql.SQLException;
import java.util.List;

import autor.Autor;
import autor.dao.AutorDAO;

import livros.Livro;
import livros.dao.LivroDAO;

public class LivroFacade
{
	private LivroDAO livroDao;
	private AutorDAO autorDao;
	
	public LivroFacade()
	{
		livroDao = new LivroDAO();
		autorDao = new AutorDAO();
	}
	
	public void insert(Livro livro) throws SQLException
	{
		livroDao.inserir(livro);
	}
	
	public void atualizar(Livro livro) throws SQLException
	{
		livroDao.atualizar(livro);
	}
	
	public List<Livro> listar() throws SQLException
	{
		List<Livro>livros = livroDao.listar();
		for (Livro livro : livros)
		{
			livro.setAutor(this.buscarAutor(livro.getAutor().getId()));
		}
		return livros;
	}

	
	public Livro pesquisarPorId(int id) throws SQLException
	{
		Livro livro = livroDao.pesquisarPorId(id);
		livro.setAutor(this.buscarAutor(livro.getAutor().getId()));
		return livro;
	}
	
	public List<Livro> pesquisarPorNome(String nome) throws SQLException
	{
		List<Livro> livros = livroDao.pesquisarPorNome(nome);
		for (Livro livro : livros)
		{
			livro.setAutor(this.buscarAutor(livro.getAutor().getId()));
		}
		return livros;
	}
	
	private Autor buscarAutor(int id) throws SQLException
	{
		return autorDao.pesquisarPorId(id);
		
	}
}
