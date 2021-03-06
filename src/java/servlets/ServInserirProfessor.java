/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ProfessorDao;
import entidades.Professor;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dênis
 */
public class ServInserirProfessor extends HttpServlet {

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
        //doPost(request, response);
        RequestDispatcher view = request.getRequestDispatcher("novoProfessor.jsp");
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
        Professor p = new Professor();
        p.setNome(request.getParameter("nome"));
        p.setCpf(request.getParameter("cpf"));
        p.setLogin(request.getParameter("login"));
        p.setSenha(request.getParameter("senha"));
        p.setEmail(request.getParameter("email"));
        String date = request.getParameter("datanasc");
        //String dateAdm = request.getParameter("dataadm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(date);
            date = dateFormat.format(parsedDate);
        } catch (ParseException ex) {
            Logger.getLogger(ServInserirProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Date dataadmissao = new Date(System.currentTimeMillis());
        //dateFormat.format(dataadmissao);
        p.setDataNascimento(parsedDate);
        date = request.getParameter("dataadm");
        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ServInserirProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setDataAdmissao(parsedDate);
        String erro = "<div class=\"alert alert-success\" >\n"
                + "                            Usuario: Salvo com sucesso!!!\n"
                + "                      </div>";
        try {
            ProfessorDao professordao = new ProfessorDao();
            professordao.inserir(p);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServInserirProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {            
            erro = "<div class=\"alert alert-danger\" >\n"
                    + "                            Professor: Login invalido!!!!\n"
                    + "                      </div>";
            request.setAttribute("erro", erro);
        }
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
