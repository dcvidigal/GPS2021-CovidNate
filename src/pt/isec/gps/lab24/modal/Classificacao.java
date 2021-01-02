package pt.isec.gps.lab24.modal;

public class Classificacao {
    String NomeJogador;
    int NumberInfetados;
    int Turno;
    String TempJogo;
    int pontos;

    public Classificacao(String nomeJogador, int numberInfetados, int turno, String tempJogo, int pontos) {
        NomeJogador = nomeJogador;
        NumberInfetados = numberInfetados;
        Turno = turno;
        TempJogo = tempJogo;
        this.pontos = pontos;
    }

    public String getNomeJogador() {
        return NomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        NomeJogador = nomeJogador;
    }

    public int getNumberInfetados() {
        return NumberInfetados;
    }

    public void setNumberInfetados(int numberInfetados) {
        NumberInfetados = numberInfetados;
    }

    public int getTurno() {
        return Turno;
    }

    public void setTurno(int turno) {
        Turno = turno;
    }

    public String getTempJogo() {
        return TempJogo;
    }

    public void setTempJogo(String tempJogo) {
        TempJogo = tempJogo;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
