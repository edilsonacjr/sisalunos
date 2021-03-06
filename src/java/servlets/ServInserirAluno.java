/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.AlunoDao;
import dao.ProfessorDao;
import entidades.Aluno;
import entidades.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
public class ServInserirAluno extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public String erro;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = request.getRequestDispatcher("novoAluno.jsp?erro=" + erro);
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
        Aluno a = new Aluno();
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
        a.getCurso().setId(Integer.parseInt(request.getParameter("curso")));
        Date dataadmissao = new Date(System.currentTimeMillis());
        Professor p = new Professor();
        try {
            ProfessorDao pdao = new ProfessorDao();
            List<Professor> listp = pdao.listar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServInserirAluno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServInserirAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        //dateFormat.format(dataadmissao);
        date = request.getParameter("dataadm");
        erro = "<div class=\"alert alert-success\" >\n"
                + "                            Aluno: Salvo com sucesso!!!\n"
                + "                      </div>";
        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ServInserirAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.setDataAdmissao(parsedDate);

        AlunoDao alunodao = null;
        try {
            alunodao = new AlunoDao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServInserirAluno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            erro = "<div class=\"alert alert-danger\" >\n"
                    + "                            Aluno: Login invalido!!!!\n"
                    + "                      </div>";
            //Logger.getLogger(ServInserirAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            alunodao.insere(a);
        } catch (SQLException ex) {
            erro = "<div class=\"alert alert-danger\" >\n"
                    + "                            Aluno: Login invalido!!!!\n"
                    + "                      </div>";
            //Logger.getLogger(ServInserirAluno.class.getName()).log(Level.SEVERE, null, ex);          
        }
        request.setAttribute("erro", erro);
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
