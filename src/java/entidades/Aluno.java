package entidades;

import java.util.Date;

public class Aluno extends Usuario {

    private Curso curso;
    private Date dataAdmissao;
    
    public Aluno() {
            this.curso = new Curso();
            this.dataAdmissao = new Date();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
 
}
