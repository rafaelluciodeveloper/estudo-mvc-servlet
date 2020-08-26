/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

/**
 *
 * @author Marcos
 */
public class ServletCliente extends HttpServlet {

    private ClienteDAO clienteDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        clienteDAO = new ClienteDAO();

        try {            
            String acao = request.getParameter("acao");

            if (acao.equals("listar")) {
                listar(request, response);
            } else if (acao.equals("adicionar")) {
                adicionar(request, response);
            } else if (acao.equals("salvar")) {
                salvar(request, response);
            } else if (acao.equals("editar")) {
                editar(request, response);
            } else if (acao.equals("atualizar")) {
                atualizar(request, response);
            } else if (acao.equals("excluir")) {
                excluir(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        

        request.setAttribute("clientes", clienteDAO.listar());
        RequestDispatcher view = request.getRequestDispatcher("cliente/index.jsp");
        view.forward(request, response);
    }

    private void adicionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("cliente/adicionar.jsp");

        view.forward(request, response);
    }
    
    private void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {        
        Cliente c = new Cliente();
        
        c.setNome(request.getParameter("nome"));
        c.setTelefone(request.getParameter("telefone"));
                        
        clienteDAO.adicionar(c);
         
        RequestDispatcher view = request.getRequestDispatcher("cliente/salvar.jsp");

        view.forward(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {                
        
        request.setAttribute("clientes", clienteDAO.listarPorId(request.getParameter("id")));        
                                         
        RequestDispatcher view = request.getRequestDispatcher("cliente/editar.jsp");

        view.forward(request, response);
    }
    
    private void atualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {                
        Cliente c = new Cliente();
        
        c.setNome(request.getParameter("nome"));        
        c.setTelefone(request.getParameter("telefone"));        
        c.setId(new Long(request.getParameter("id")));  
        
        clienteDAO.editar(c);
                                         
        RequestDispatcher view = request.getRequestDispatcher("cliente/atualizar.jsp");

        view.forward(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {                
        
        clienteDAO.excluir(request.getParameter("id"));        
                                         
        RequestDispatcher view = request.getRequestDispatcher("cliente/excluir.jsp");

        view.forward(request, response);
    }
}
