package br.com.wellington.tarefas.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TarefaDAO {
	
	private Connection connection;
	
	@Autowired
	public TarefaDAO(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//Create
	public void adicionar(Tarefa tarefa) {
		String  sql = "insert into tarefas (descricao,finalizado) values (?,?)";
		
			try {
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				stmt.setString(1,tarefa.getDescricao());
				stmt.setBoolean(2, tarefa.isFinalizado());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}	
	}
	
	//read
	public List<Tarefa> getLista(){
		List<Tarefa> listTarefas = new ArrayList<Tarefa>();
		String  sql = "select * from tarefas";
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				listTarefas.add(populaTarefa(rs));
			}
		
		rs.close();
		stmt.close();		
		return listTarefas;	
		
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
	}
	
	//update
	public void alterar(Tarefa tarefa) {
		String sql = "update tarefas set descricao=?, finalizado=?, \"dataFinalizacao\"=? where id=?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2,tarefa.isFinalizado());
			
			stmt.setDate(3,tarefa.getDataFinalizacao()!=null?new Date(tarefa.getDataFinalizacao().getTimeInMillis()):null);
			stmt.setLong(4,tarefa.getId());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//delete
	public void remove(Long id) {
		String sql = "delete from tarefas where id=?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	public Tarefa buscarPorId(Long id) {
		String sql = "select* from tarefas where id=?";
	
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return populaTarefa(rs);
			}	
		rs.close();
		stmt.close();
		return null;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public Tarefa populaTarefa(ResultSet rs) throws SQLException {
			Tarefa tarefa = new Tarefa();
			
			tarefa.setId(rs.getLong("id"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setFinalizado(rs.getBoolean("finalizado"));
			Date data = null;
			data = rs.getDate("dataFinalizacao");
			
			if(data != null) {
				Calendar dataFinalizacao = Calendar.getInstance();
				dataFinalizacao.setTime(data);
				tarefa.setDataFinalizacao(dataFinalizacao);
			}
		return tarefa;
	}
	public void finaliza(Long id) {
		Tarefa tarefa = buscarPorId(id);
		tarefa.setFinalizado(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		alterar(tarefa);
	}
}
