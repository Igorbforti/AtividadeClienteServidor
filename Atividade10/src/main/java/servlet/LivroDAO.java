package servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
	private Connection connection;
	public LivroDAO() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void adiciona(Livro livro) {
		String sql = "insert into tblivros" + 
					 "(codlivro, titulo, autor, categoria, valor)" + 
					 " values (?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, livro.getCodlivro());
			stmt.setString(2, livro.getTitulo());
			stmt.setString(3, livro.getAutor());
			stmt.setString(4, livro.getCategoria());
			stmt.setFloat(5, livro.getValor());
			stmt.execute();
			stmt.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	public List<Livro> getListaByCod(int codlivro){
		try {
			List<Livro> livros = new ArrayList<Livro>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from tblivros where codlivro = ?");
			stmt.setInt(1, codlivro);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setCodlivro(rs.getInt("codlivro"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setValor(rs.getFloat("valor"));
				livros.add(livro);
			}
			rs.close();
			stmt.close();
			return livros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Livro> getListaByCat(String categoria){
		try {
			List<Livro> livros = new ArrayList<Livro>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from tblivros where categoria = ?");
			stmt.setString(1, categoria);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setCodlivro(rs.getInt("codlivro"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setValor(rs.getFloat("valor"));
				livros.add(livro);
			}
			rs.close();
			stmt.close();
			return livros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void alterar(Livro livro) {
		String sql = "update tblivros set titulo=?, autor=?, categoria=?, valor=? where codlivro=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getAutor());
			stmt.setString(3, livro.getCategoria());
			stmt.setFloat(4, livro.getValor());
			stmt.setInt(5, livro.getCodlivro());
			stmt.execute();
			stmt.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	public void remover(Livro livro) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from tblivros where codlivro=?");
			stmt.setInt(1, livro.getCodlivro());
			stmt.execute();
			stmt.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}