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
import livrarias.classes.Autores;
import livrarias.classes.Editoras;
import livrarias.classes.Livros;

public class LivrosDAO {

    public void cadastar(Livros x) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO livros (id_editoras,id_autor,titulo,ano) VALUES(?,?,?,?)");
            stmt.setInt(1, x.getEditora().getId());
            stmt.setInt(2, x.getAutor().getId());
            stmt.setString(3, x.getTitulo());
            stmt.setInt(4, x.getAno());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Cadastro Realizado");

    }

    public List<Livros> listar() {
        Connection conn = BancoMySql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Livros> livros = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select livros.id_livros,editoras.nome as nome_editora,autores.nome as nome_autor,livros.titulo,livros.ano from livros\n"
                    + "inner join editoras on (editoras.id_editoras = livros.id_editoras) \n"
                    + "inner join autores  on (autores.id_autor = livros.id_autor) ");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Livros l = new Livros();

                l.setId_livro(rs.getInt("id_livros"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("ano"));

                Editoras e = new Editoras();
                e.setNome(rs.getString("nome_editora"));
                l.setEditora(e);

                Autores a = new Autores();
                a.setNome(rs.getString("nome_autor"));
                l.setAutor(a);

                livros.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }

    public void alterar(Livros e) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement("UPDATE livros set id_editoras = ?, id_autor = ?, titulo = ?, ano = ? where id_livros = ? ");

            stmt.setInt(1, e.getEditora().getId());
            stmt.setInt(2, e.getAutor().getId());

            stmt.setString(3, e.getTitulo());
            stmt.setInt(4, e.getAno());
            stmt.setInt(5, e.getId_livro());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso");

        } catch (SQLException ex) {
        }
    }

    public void exclui(Livros e) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement("DELETE FROM livros where id_livros = (?)");
            stmt.setInt(1, e.getId_livro());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Exlusão feita com sucesso");

        } catch (SQLException ex) {
        }
    }

    public List<Livros> pesquisar(String texto) {
        Connection conn = BancoMySql.conectaBanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Livros> Livros = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select livros.id_livros,editoras.nome as nome_editora,autores.nome as nome_autor,livros.titulo,livros.ano from livros \n"
                    + "inner join editoras on (editoras.id_editoras = livros.id_editoras) \n"
                    + "inner join autores  on (autores.id_autor = livros.id_autor)  \n"
                    + " where titulo like (?) ");
            
            stmt.setString(1, "%" + texto + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Livros l = new Livros();

                l.setId_livro(rs.getInt("id_livros"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("ano"));

                Editoras e = new Editoras();
                e.setNome(rs.getString("nome_editora"));
                l.setEditora(e);

                Autores a = new Autores();
                a.setNome(rs.getString("nome_autor"));
                l.setAutor(a);

               Livros.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Livros ;
    }

}
