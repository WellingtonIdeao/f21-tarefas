package br.com.wellington.tarefas.filter;
/*
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.wellington.tarefas.jdbc.ConnectionFactory;

@WebFilter("/*")
public class FiltroDeConexao implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		Connection connection = ConnectionFactory.getConection();
		req.setAttribute("connection", connection);
		chain.doFilter(req, res);
		try {
			connection.close();
		} catch (SQLException e) {
			throw new ServletException("Erro no fechamento da conexão",e);
		}
	}

}*/
