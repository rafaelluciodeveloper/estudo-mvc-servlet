<%-- 
    Document   : adicionar
    Created on : 15/07/2014, 20:33:30
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
        <h1>Editar Cliente</h1>

        <form action="ServletCliente" method="post">
            <table>
                <c:forEach var="cliente" items="${clientes}">
                    <tr>
                        <td>
                            <label>Código:</label>
                        </td>
                        <td>
                            <input type="text" name="id" value="${cliente.id}" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Nome:</label>
                        </td>
                        <td>
                            <input type="text" name="nome" value="${cliente.nome}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Telefone:</label>
                        </td>
                        <td>
                            <input type="text" name="telefone" value="${cliente.telefone}"/>
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
            <a href="ServletCliente?acao=listar">Voltar</a>
        </p> 
    </body>
</html>
