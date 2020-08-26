/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conf.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Pedido;

/**
 *
 * @author Marcos
 */
public class PedidoDAO {

    private Conexao c;
    private Statement stmt;
    private PreparedStatement ps;
    
    private ClienteDAO clienteDAO;

    public PedidoDAO() {
        clienteDAO = new ClienteDAO();
        c = new Conexao();
    }

    public List listar() {
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            String SQL = "Select * from pedido";

            stmt = c.getConexao().createStatement();

            rs = stmt.executeQuery(SQL);
            
            while (rs.next()){
                Pedido p = new Pedido();
                
                p.setId(rs.getLong("id"));
                
                for(Object o : clienteDAO.listarPorId(rs.getString("cliente_id"))){
                    Cliente cliente = (Cliente)o;
                    p.setCliente(cliente);
                }
                                
                p.setData(rs.getDate("data"));
                p.setDescricao(rs.getString("descricao"));
                
                lista.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    public List listarPorId(String id) {
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            String SQL = "Select * from pedido where id = " + id;

            stmt = c.getConexao().createStatement();

            rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
                Pedido p = new Pedido();
                
                p.setId(rs.getLong("id"));
                
                for(Object o : clienteDAO.listarPorId(rs.getString("cliente_id"))){
                    Cliente cliente = (Cliente)o;
                    p.setCliente(cliente);
                }
                                
                p.setData(rs.getDate("data"));
                p.setDescricao(rs.getString("descricao"));
                
                lista.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void adicionar(Pedido pedido) throws SQLException {
        try {
            String SQL = "INSERT INTO pedido(cliente_id, data, descricao) "
                    + "VALUES (?, ?, ?)";

            ps = c.getConexao().prepareStatement(SQL);

            ps.setLong(1, pedido.getCliente().getId());
            ps.setDate(2, new java.sql.Date(pedido.getData().getTime()));
            ps.setString(3, pedido.getDescricao());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Pedido pedido) throws SQLException {
        try {
            String SQL = "UPDATE pedido SET "
                    + "cliente_id= ?, data= ?, descricao=? "
                    + "WHERE id=?";

            ps = c.getConexao().prepareStatement(SQL);

            ps.setLong(1, pedido.getCliente().getId());
            ps.setDate(2, new java.sql.Date(pedido.getData().getTime()));
            ps.setString(3, pedido.getDescricao());
            ps.setLong(4, pedido.getId());

          //  ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void excluir(String id) {
        try {
            String SQL = "DELETE FROM pedido WHERE id=" + id;

            stmt = c.getConexao().createStatement();           

            stmt.executeUpdate(SQL);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
