<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script	type="text/javascript" src="resources/js/jquery.js"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Tarefas</title>
</head>
	<body>
		<script	type="text/javascript">
			function finalizaAgora(id){
				$.post("finalizaTarefa", {'id':id},function(res) {
				// selecionando o elemento html	através	da ID e alterando o HTML dele
				$("#tarefa_"+id).html(res);
				});
			}
			function removeAgora(id){
				$.post("removeTarefa", {'id':id},function() {
				// selecionando o elemento html	através	da ID e alterando o HTML dele
				$("#tarefa_"+id).closest("tr").hide();
				});
			}
		</script>
		
		<a href="novaTarefa">Criar nova tarefa</a><br/><br/>
			<table id="tabela">
					<tr>
						<th>Id</th>
						<th>Descrição</th>
						<th>Finalizado?</th>
						<th>Data de	finalização</th>
					</tr>
						<c:forEach items="${tarefas}" var="tarefa">
							<tr id="tarefa_${tarefa.id}">
							
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
									<!--  <td><a href="removeTarefa?id=${tarefa.id}">Remover</a></td> -->
									<td><a	href="mostraTarefa?id=${tarefa.id}">Alterar</a></td>
							</tr>
						</c:forEach>
			</table>
	</body>
</html>