/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author claudemir
 */
public class MateriaDAO {
    
    private Connection conexao;
    
    public MateriaDAO() throws ClassNotFoundException, SQLException{
        conexao = Conexao.getConexao();
    }
    
    public void insere(Materia m) throws SQLException {
        String sql = "insert into materia values (null, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, m.getPeriodo().getId());
        stmt.setString(2, m.getNome());
        stmt.setInt(3, m.getProfessor().getId());
        stmt.execute();
        stmt.close();
    }
    
    public void atualiza(Materia m) throws SQLException {
        String sql = "update materia set idPeriodo = ?, nome = ?, idProfessor = ?"
                + "where idMateria = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, m.getPeriodo().getId());
        stmt.setString(2, m.getNome());
        stmt.setInt(3, m.getProfessor().getId());
        stmt.setInt(4, m.getId());
        stmt.execute();
        stmt.close();
    }
    
    public void exclui(Materia m) throws SQLException {
        String sql = "delete from materia where idMateria = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, m.getId());
        stmt.execute();
        stmt.close();
    }
    
    public List<Materia> listar() throws SQLException {
        List<Materia> list = new ArrayList<>();
        Materia m;
        String sql = "select * from materia";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            m = new Materia();
            m.setId(rs.getInt("idMateria"));
            m.getPeriodo().setId(rs.getInt("idPeriodo"));
            m.setNome(rs.getString("nome"));
            m.getProfessor().setId(rs.getInt("idProfessor"));
            list.add(m);
        }
        return list;
    }
}
