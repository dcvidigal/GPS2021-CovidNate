package pt.isec.gps.lab24.modal;

import java.util.Comparator;

public class Classificacao implements Comparable<Classificacao> {
    String NomeJogador;
    int NumberRecuperados;
    int NumberInfetados;
    int Turno;
    String TempJogo;
    int pontos;
    String Dificuldade;
    public Classificacao(String nomeJogador,int numberRecuperados, int numberInfetados, int turno, String tempJogo, int pontos, String dificuldade){
        NomeJogador = nomeJogador;
        NumberRecuperados = numberRecuperados;
        NumberInfetados = numberInfetados;
        Turno = turno;
        TempJogo = tempJogo;
        this.pontos = pontos;
        Dificuldade = dificuldade;
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

    public int getNumberRecuperados() {
        return NumberRecuperados;
    }

    public void setNumberRecuperados(int numberRecuperados) {
        NumberRecuperados = numberRecuperados;
    }

    public String getDificuldade() {
        return Dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        Dificuldade = dificuldade;
    }



    @Override
    public int compareTo(Classificacao o) {
        int result = Integer.compare(getTurno(), o.getTurno());
        if(result == 0 ) result = Integer.compare(getPontos(), o.getPontos())*-1;
        return result;
    }
}
