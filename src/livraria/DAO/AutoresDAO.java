/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livraria.DAO;

import java.math.BigDecimal;
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
import java.math.BigInteger;
import livrarias.classes.Autores;
import livrarias.classes.Livros;

/**
 *
 * @author gabriel.ferrandin
 */
public class AutoresDAO {

    public void cadastar(Autores e) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement("INSERT INTO autores (nome,endereco,numero,bairro,cidade,cpf) VALUES(?,?,?,?,?,?)");
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getEndereco());
            stmt.setInt(3, e.getNumero());
            stmt.setString(4, e.getBairro());
            stmt.setString(5, e.getCidade());
            stmt.setBigDecimal(6, new BigDecimal(e.getCpf()));

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastro Realizado");

        } catch (SQLException ex) {
        }

    }

    public void cadastar(Livros x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Autores> listar() {
        Connection conn = BancoMySql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Autores> autores = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM autores");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Autores e = new Autores();

                e.setId(rs.getInt("id_autor"));
                e.setNome(rs.getString("nome"));
                e.setEndereco(rs.getString("endereco"));
                e.setNumero(rs.getInt("numero"));
                e.setBairro(rs.getString("bairro"));
                e.setCidade(rs.getString("cidade"));
                e.setCpf(new BigInteger(rs.getString("cpf")));
                autores.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autores;
    }

    public void alterar(Autores e) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement("UPDATE autores set nome = ?, endereco = ?, numero = ?, bairro = ?, cidade = ?, cpf = ? where id_autor = ?");
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getEndereco());
            stmt.setInt(3, e.getNumero());
            stmt.setString(4, e.getBairro());
            stmt.setString(5, e.getCidade());
            stmt.setBigDecimal(6, new BigDecimal(e.getCpf()));
            stmt.setInt(7, e.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso");

        } catch (SQLException ex) {
        }
    }

    public void excluir(Autores e) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement("DELETE FROM autores where nome = (?)");
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getEndereco());
            stmt.setInt(3, e.getNumero());
            stmt.setString(4, e.getBairro());
            stmt.setString(5, e.getCidade());
            stmt.setBigDecimal(6, new BigDecimal(e.getCpf()));
            stmt.setInt(7, e.getId());

            stmt.executeUpdate();

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Exlusão feita com sucesso");

        } catch (SQLException ex) {
        }
    }
}
