/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.AlunoDao;
import entidades.Aluno;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josepedro
 */
@WebServlet(name = "ServAtualizarAluno", urlPatterns = {"/ServAtualizarAluno"})
public class ServAtualizarAluno extends HttpServlet {

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
        Aluno a = new Aluno();
        a.setId(Integer.parseInt(request.getParameter("button1id")));
        a.setNome(request.getParameter("nome"));
        a.setCpf(request.getParameter("cpf"));
        String date = request.getParameter("data");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ServInserirAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        a.setDataNascimento(parsedDate);
        a.setEmail(request.getParameter("email"));
        a.setLogin(request.getParameter("login"));
        a.setSenha(request.getParameter("senha"));
         date = request.getParameter("dataadmissao");
        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ServAtualizarAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.setDataAdmissao(parsedDate);
        a.getCurso().setId(Integer.parseInt(request.getParameter("curso")));
        //dateFormat.format(dataadmissao);
        //a.setDataAdmissao(dataadmissao);
        try {
            AlunoDao alunodao = new AlunoDao();
            alunodao.atualiza(a);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServInserirAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher view = request.getRequestDispatcher("Alunos.jsp");
        view.forward(request, response);
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

}
