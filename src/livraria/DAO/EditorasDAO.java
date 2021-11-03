/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Principal.BancoMySql;

import livrarias.classes.Editoras;
import livrarias.classes.Livros;

/**
 *
 * @author gabriel.ferrandin
 */
//DAO OBJETO DE ACESSO A DADOS 
public class EditorasDAO {

    public void cadastar(Editoras e) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement("INSERT INTO editoras (nome) VALUES(?)");
            stmt.setString(1, e.getNome());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastro Realizado");

        } catch (SQLException ex) {
        }
    }

    public List<Editoras> listar() {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Editoras> editoras = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM editoras");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Editoras e = new Editoras();
                e.setId(rs.getInt("id_editoras"));
                e.setNome(rs.getString("nome"));
                editoras.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editoras;
    }

    public void alterar(Editoras e) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement("UPDATE editoras set nome = ? where id_editoras = ?");
            stmt.setString(1, e.getNome());
            stmt.setInt(2, e.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso");

        } catch (SQLException ex) {
        }
    }

    public void excluir(Editoras e) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement("DELETE FROM editoras where id_editoras = (?)");
            stmt.setInt(1, e.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Exlusão feita com sucesso");

        } catch (SQLException ex) {
        }
    }

    public List<Editoras> pesquisar(String texto) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Editoras> editoras = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM editoras where nome like (?)");
            stmt.setString(1, "%" + texto + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Editoras edit = new Editoras();
                edit.setNome(rs.getString("nome"));
                edit.setId(rs.getInt("id_editora"));
                editoras.add(edit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editoras;
    }

    public void excluir(Livros e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
