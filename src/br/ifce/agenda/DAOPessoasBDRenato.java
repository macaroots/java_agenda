package br.ifce.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOPessoasBDRenato implements DAOPessoas {

	public Connection conecta() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda2?" +
		                                   "user=root&password=admin");
			return conn;
		} catch (SQLException e) {
			System.out.println("Erro ao conectar: " + e.getMessage());
			return null;
		}
	}
	
	@Override
	public void criarBD() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apagarBD() {
		String sql = "TRUNCATE contatos";
		try {
			Connection conexao = conecta();
			PreparedStatement consulta = conexao.prepareStatement(sql);
			consulta.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer inserir(Pessoa bean) {
		String sql = "INSERT INTO contatos (nome, telefone) VALUES (?, ?)";
		try {
			Connection conexao = conecta();
			PreparedStatement consulta = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			consulta.setString(1, bean.getNome());
			consulta.setString(2, bean.getTelefone());
			consulta.execute();
			ResultSet linhas = consulta.getGeneratedKeys();
			if (linhas.next()) {
				int id = linhas.getInt(1);
				bean.setId(id);
				return id;
			}
			conexao.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Pessoa> listar() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM contatos2";
		try {
			Connection conexao = conecta();
			PreparedStatement consulta = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet linhas = consulta.executeQuery();
			while (linhas.next()) {
				Pessoa p = new Pessoa(linhas.getInt("id"), linhas.getString("nome"), linhas.getString("telefone"));
				pessoas.add(p);
			}
			consulta.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}

	@Override
	public void editar(Pessoa bean, Integer chave) {
		String sql = "UPDATE contatos SET nome=?, telefone=? WHERE id=?";
		try {
			Connection conexao = conecta();
			PreparedStatement consulta = conexao.prepareStatement(sql);
			consulta.setString(1, bean.getNome());
			consulta.setString(2, bean.getTelefone());
			consulta.setInt(3, chave);
			consulta.execute();
			
			consulta.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void apagar(Integer chave) {
		String sql = "DELETE FROM contatos WHERE id=?";
		try {
			Connection conexao = conecta();
			PreparedStatement consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, chave);
			consulta.execute();
			
			consulta.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Pessoa ver(Integer chave) {
		Pessoa p = null;
		String sql = "SELECT * FROM contatos WHERE id=?";
		try {
			Connection conexao = conecta();
			PreparedStatement consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, chave);
			ResultSet linhas = consulta.executeQuery();
			if (linhas.next()) {
				p = new Pessoa(linhas.getInt("id"), linhas.getString("nome"), linhas.getString("telefone"));
			}
			consulta.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public List<Pessoa> listarPorNome(String nome) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM contatos WHERE nome LIKE ?";
		try {
			Connection conexao = conecta();
			PreparedStatement consulta = conexao.prepareStatement(sql);
			consulta.setString(1, nome);
			ResultSet linhas = consulta.executeQuery();
			while (linhas.next()) {
				Pessoa p = new Pessoa(linhas.getInt("id"), linhas.getString("nome"), linhas.getString("telefone"));
				pessoas.add(p);
			}
			consulta.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}

}
