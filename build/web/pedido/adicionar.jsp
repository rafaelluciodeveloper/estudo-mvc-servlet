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
        <h1>Adicionar Novo</h1>
        
         <form action="ServletPedido" method="post">
             <table>
                 <tr>
                     <td>
                         <label>Cliente:</label>
                     </td>
                     <td>
                         <select name="cliente">
                             <c:forEach var="cliente" items="${clientes}">
                                 <option value="${cliente.id}">${cliente.nome}</option>
                             </c:forEach>
                         </select>
                     </td>
                 </tr>
                 <tr>
                     <td>
                         <label>Data:</label>
                     </td>
                     <td>
                         <input type="text" name="data"/>
                     </td>
                 </tr>
                 <tr>
                     <td>
                         <label>Descrição:</label>
                     </td>
                     <td>
                         <textarea name="descricao"></textarea>
                     </td>
                 </tr>
                 <tr>
                     <td>                         
                     </td>
                     <td>
                         <input type="hidden" value="salvar" name="acao"/>
                         <input type="submit" value="Enviar" />
                     </td>
                 </tr>
             </table>            
        </form>
        
        <p>
            <a href="ServletPedido?acao=listar">Voltar</a>
        </p> 
    </body>
</html>
