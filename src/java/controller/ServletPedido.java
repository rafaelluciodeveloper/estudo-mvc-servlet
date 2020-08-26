/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDAO;
import dao.PedidoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Pedido;

/**
 *
 * @author Marcos
 */
public class ServletPedido extends HttpServlet {

    private PedidoDAO pedidoDAO;
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
        pedidoDAO = new PedidoDAO();
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

        request.setAttribute("pedidos", pedidoDAO.listar());
        RequestDispatcher view = request.getRequestDispatcher("pedido/index.jsp");
        view.forward(request, response);
    }

    private void adicionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("clientes", clienteDAO.listar());
        
        RequestDispatcher view = request.getRequestDispatcher("pedido/adicionar.jsp");

        view.forward(request, response);
    }

    private void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        Pedido p = new Pedido();

        for(Object o : clienteDAO.listarPorId(request.getParameter("cliente"))){
            p.setCliente((Cliente)o);
        }
        
        p.setData(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("data")));
        p.setDescricao(request.getParameter("descricao"));
        
        pedidoDAO.adicionar(p);

        RequestDispatcher view = request.getRequestDispatcher("pedido/salvar.jsp");

        view.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.setAttribute("pedidos", pedidoDAO.listarPorId(request.getParameter("id")));
        request.setAttribute("clientes", clienteDAO.listar());

        RequestDispatcher view = request.getRequestDispatcher("pedido/editar.jsp");

        view.forward(request, response);
    }

    private void atualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        Pedido p = new Pedido();

        for(Object o : clienteDAO.listarPorId(request.getParameter("cliente"))){
            p.setCliente((Cliente)o);
        }
        
        p.setData(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("data")));
        p.setDescricao(request.getParameter("descricao"));
        p.setId(new Long(request.getParameter("id")));

        pedidoDAO.editar(p);

        RequestDispatcher view = request.getRequestDispatcher("pedido/atualizar.jsp");

        view.forward(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        pedidoDAO.excluir(request.getParameter("id"));

        RequestDispatcher view = request.getRequestDispatcher("pedido/excluir.jsp");

        view.forward(request, response);
    }
}
