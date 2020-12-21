package pt.isec.gps.lab24.modal;

import pt.isec.gps.lab24.modal.recursos.Direcao;
import pt.isec.gps.lab24.modal.recursos.Posicao;

public class Pessoa {
    private boolean infetada = false;
    private boolean imune = false;
    private boolean quarentena = false;
    private int diasInfetados = -1;
    private int diasEmQuarentena = -1;
    private Posicao posicao;
    private double probInfetar;
    private double probImunidade;
    private int turnoSemInfetar;

    public Pessoa(Posicao posicao, double probInfetar, double probImunidade, int turnoSemInfetar) {
        this.posicao = posicao;
        this.probInfetar = probInfetar;
        this.probImunidade = probImunidade;
        this.turnoSemInfetar = turnoSemInfetar;
    }

    public boolean isInfetada() {
        return infetada;
    }

    public boolean isImune() {
        return imune;
    }

    public boolean isQuarentena() {
        return quarentena;
    }

    public int getDiasInfetados() {
        return diasInfetados;
    }

    public int getDiasEmQuarentena() {
        return diasEmQuarentena;
    }

    public Posicao getPosicao() {
        return new Posicao(posicao.getX(),posicao.getY());
    }

    public void move(Direcao direcao){
        posicao.move(direcao);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "infetada=" + infetada +
                ", imune=" + imune +
                ", quarentena=" + quarentena +
                ", diasInfetados=" + diasInfetados +
                ", diasEmQuarentena=" + diasEmQuarentena +
                ", posicao=" + posicao +
                ", probInfetar=" + probInfetar +
                ", probImunidade=" + probImunidade +
                ", turnoSemInfetar=" + turnoSemInfetar +
                '}';
    }
}
