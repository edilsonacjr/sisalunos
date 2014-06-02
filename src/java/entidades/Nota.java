package entidades;

public class Nota {
    private int id;
    private AlunoMateria alunoMateria;
    private double n1;
    private double n2;
    private double n3;
    
    public Nota(){
        this.alunoMateria = new AlunoMateria();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AlunoMateria getAlunoMateria() {
        return alunoMateria;
    }

    public void setAlunoMateria(AlunoMateria alunoMateria) {
        this.alunoMateria = alunoMateria;
    }

    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    public double getN3() {
        return n3;
    }

    public void setN3(double n3) {
        this.n3 = n3;
    }
    
}
