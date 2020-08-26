<%-- 
    Document   : adicionar
    Created on : 15/07/2014, 20:33:30
    Author     : Marcos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Adicionar Novo</h1>
        
         <form action="ServletCliente" method="post">
             <table>
                 <tr>
                     <td>
                         <label>Nome:</label>
                     </td>
                     <td>
                         <input type="text" name="nome"/>
                     </td>
                 </tr>
                 <tr>
                     <td>
                         <label>Telefone:</label>
                     </td>
                     <td>
                         <input type="text" name="telefone"/>
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
            <a href="ServletCliente?acao=listar">Voltar</a>
        </p> 
    </body>
</html>
