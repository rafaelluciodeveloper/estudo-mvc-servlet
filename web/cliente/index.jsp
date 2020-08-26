<%-- 
    Document   : index
    Created on : 15/07/2014, 20:24:42
    Author     : Marcos
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de Clientes</h1>
        <p>
            <a href="ServletCliente?acao=adicionar">Adicionar</a>
        </p>        

        <table border="1" cellpadding="5">
            <tr>
                <td>Id</td>
                <td>Nome</td>
                <td>Telefone</td>
                <td>Editar</td>
                <td>Excluir</td>
            </tr>
            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.id}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.telefone}</td>
                    <td><a href="ServletCliente?acao=editar&id=${cliente.id}">Editar</a></td>
                    <td><a href="ServletCliente?acao=excluir&id=${cliente.id}">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
