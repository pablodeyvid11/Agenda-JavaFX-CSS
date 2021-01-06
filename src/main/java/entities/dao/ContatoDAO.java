package entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Contato;
import services.db.Conexao;

public class ContatoDAO {
	public Boolean update(Contato c) {
		Connection con = Conexao.getConexao();
		String sql = "UPDATE contato SET email = ?, grupo = ?, nome = ?, numero = ?, operadora = ? WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getEmail());
			ps.setString(2, c.getGrupo());
			ps.setString(3, c.getNome());
			ps.setString(4, c.getNumero());
			ps.setString(5, c.getOperadora());
			ps.setInt(6, c.getId());

			int linhasAfetadas = ps.executeUpdate();
			if (linhasAfetadas > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Contato> getAll() {
		List<Contato> contatos = new ArrayList<>();
		Connection con = Conexao.getConexao();

		String sql = "SELECT * FROM contato";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				contatos.add(new Contato(rs.getInt("id"), rs.getString("nome"), rs.getString("numero"),
						rs.getString("email"), rs.getString("operadora"), rs.getString("grupo")));
			}

			ps.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contatos;
	}

	public Contato findById(Integer id) {
		Contato contato = null;

		Connection con = Conexao.getConexao();

		String sql = "SELECT * FROM contato WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				contato = new Contato(rs.getInt("id"), rs.getString("nome"), rs.getString("numero"),
						rs.getString("email"), rs.getString("operadora"), rs.getString("grupo"));
				break;
			}
			ps.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contato;
	}

	public Contato deleteById(Integer id) {
		Contato c = findById(id);
		Connection con = Conexao.getConexao();

		String sql = "DELETE FROM contato WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public Boolean addContato(Contato c) {
		Connection con = Conexao.getConexao();

		String sql = "INSERT INTO contato(email, grupo, nome, numero, operadora) VALUES(?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getEmail());
			ps.setString(2, c.getGrupo());
			ps.setString(3, c.getNome());
			ps.setString(4, c.getNumero());
			ps.setString(5, c.getOperadora());
			
			ps.execute();
			ps.close();
		} catch (SQLException u) {
			u.printStackTrace();
			return false;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
