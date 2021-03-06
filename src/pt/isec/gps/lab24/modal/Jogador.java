package pt.isec.gps.lab24.modal;

public class  Jogador {
    private String nome;
    private int pontos;
    private int turno;

    public Jogador() {
    }

    public Jogador(String nome, int pontos, int turno) {
        this.nome = nome;
        this.pontos = pontos;
        this.turno = turno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
        if(this.pontos < 0) this.pontos = 0;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int incTurnos(){
        this.turno++;
        return this.turno;
    }

    public int incPontos(int pontos){
        this.pontos += pontos;
        return  this.pontos;
    }
    public int decPontos(int pontos){
        this.pontos -= pontos;
        if(this.pontos < 0 ) this.pontos = 0;
        return  this.pontos;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", pontos=" + pontos +
                ", turno=" + turno +
                '}';
    }
}
