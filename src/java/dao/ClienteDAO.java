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

/**
 *
 * @author Marcos
 */
public class ClienteDAO {

    private Conexao c;
    private Statement stmt;
    private PreparedStatement ps;

    public ClienteDAO() {
        c = new Conexao();
    }

    public List listar() {
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            String SQL = "Select * from cliente";

            stmt = c.getConexao().createStatement();

            rs = stmt.executeQuery(SQL);
            
            while (rs.next()){
                Cliente c = new Cliente();
                
                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                
                lista.add(c);
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
            String SQL = "Select * from cliente where id = " + id;

            stmt = c.getConexao().createStatement();

            rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
                Cliente c = new Cliente();
                
                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                
                lista.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void adicionar(Cliente cliente) throws SQLException {
        try {
            String SQL = "INSERT INTO cliente(nome, telefone) "
                    + "VALUES (?, ?)";

            ps = c.getConexao().prepareStatement(SQL);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ps.close();
        }
    }

    public void editar(Cliente cliente) throws SQLException {
        try {
            String SQL = "UPDATE cliente SET "
                    + "nome= ?, telefone= ? "
                    + "WHERE id=?";

            ps = c.getConexao().prepareStatement(SQL);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setLong(3, cliente.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ps.close();
        }
    }

    public void excluir(String id) {
        try {
            String SQL = "DELETE FROM cliente WHERE id=" + id;

            stmt = c.getConexao().createStatement();           

            stmt.executeUpdate(SQL);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
