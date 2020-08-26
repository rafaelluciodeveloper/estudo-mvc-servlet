<%-- 
    Document   : index
    Created on : 15/07/2014, 20:24:42
    Author     : Marcos
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de Pedidos</h1>
        <p>
            <a href="ServletPedido?acao=adicionar">Adicionar</a>
        </p>        

        <table border="1" cellpadding="5">
            <tr>
                <td>Id</td>
                <td>Cliente</td>
                <td>Data</td>
                <td>Descrição</td>
                <td>Editar</td>
                <td>Excluir</td>
            </tr>
            <c:forEach var="pedido" items="${pedidos}">
                <tr>
                    <td>${pedido.id}</td>
                    <td>${pedido.cliente.nome}</td>
                    <td><fmt:formatDate value="${pedido.data}" type="date" pattern="dd/MM/yyyy"/></td>
                    <td>${pedido.descricao}</td>
                    <td><a href="ServletPedido?acao=editar&id=${pedido.id}">Editar</a></td>
                    <td><a href="ServletPedido?acao=excluir&id=${pedido.id}">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
