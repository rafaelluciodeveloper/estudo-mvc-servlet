<%-- 
    Document   : adicionar
    Created on : 15/07/2014, 20:33:30
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
        <h1>Editar Cliente</h1>

        <form action="ServletPedido" method="post">
            <table>
                <c:forEach var="pedido" items="${pedidos}">
                    <tr>
                        <td>
                            <label>Código:</label>
                        </td>
                        <td>
                            <input type="text" name="id" value="${pedido.id}" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Cliente</label>
                        </td>
                        <td>
                            <select name="cliente">
                                <c:forEach var="cliente" items="${clientes}">
                                    <c:choose>
                                        <c:when test="${cliente.id eq pedido.cliente.id}">
                                            <option value="${cliente.id}" selected>${cliente.nome}</option>
                                        </c:when>
                                        <c:otherwise> 
                                                <option value="${cliente.id}">${cliente.nome}</option>
                                        </c:otherwise>
                                    </c:choose>    
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Data:</label>
                        </td>
                        <td>
                            <fmt:formatDate value="${pedido.data}" type="date" pattern="dd/MM/yyyy" var="dataFormatada"/>
                            <input type="text" name="data" value="${dataFormatada}"/>                            
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Descrição:</label>
                        </td>
                        <td>
                            <textarea name="descricao">${pedido.descricao}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>                         
                        </td>
                        <td>
                            <input type="hidden" value="atualizar" name="acao"/>
                            <input type="submit" value="Enviar" />
                        </td>
                    </tr>
                </c:forEach>                        
            </table>            
        </form>

        <p>
            <a href="ServletPedido?acao=listar">Voltar</a>
        </p> 
    </body>
</html>
