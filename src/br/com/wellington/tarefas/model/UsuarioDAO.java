package br.com.wellington.tarefas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO {
	
	private Connection connection;
	
	@Autowired
	public UsuarioDAO(DataSource dataSource){
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	 
	
	public boolean existeUsuario(Usuario usuario){
		String sql = "select from usuarios where login=? and senha=?";
	
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}	
		rs.close();
		stmt.close();
		return false;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/*public Usuario populaUsuario(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setLogin(rs.getString("login"));
		usuario.setSenha(rs.getString("senha"));
	return usuario;
	}*/
	
}	