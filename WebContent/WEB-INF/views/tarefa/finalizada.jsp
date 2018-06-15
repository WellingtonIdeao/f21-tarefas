<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
			<td>${tarefa.id}</td>
			<td>${tarefa.descricao}</td>
			<c:choose>
				<c:when test="${tarefa.finalizado eq false}">
					<td	>
						<a	href="#" onClick="finalizaAgora(${tarefa.id})">Finalizar</a>
					</td>
				</c:when>
				<c:otherwise>
						<td>Finalizado</td>
				</c:otherwise>
			</c:choose>
			<td>
				<fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy" />
			<td>
				<a href="#" onClick="removeAgora(${tarefa.id})">Remover</a>
			</td>
			<td><a	href="mostraTarefa?id=${tarefa.id}">Alterar</a></td>
		
		
</body>
</html>