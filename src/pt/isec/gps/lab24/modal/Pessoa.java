package pt.isec.gps.lab24.modal;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import pt.isec.gps.lab24.modal.recursos.Direcao;
import pt.isec.gps.lab24.modal.recursos.Posicao;

import java.util.Random;

public class Pessoa {
    private boolean infetada = false;
    private boolean imune = false;
    private boolean quarentena = false;
    private int diasInfetados = -1;
    private int turnosEmQuarentena = -1;
    private Posicao posicao;
    private double probInfetar;
    private double probImunidade;
    private int turnoSemInfetar;
    private StackPane panePessoa;
    private SVGPath svgPessoa;
    private boolean contactoInfetada = false;
    private int turnoSemInfetarCount=0;
    private boolean podeSerInfetado = true;

    public Pessoa(Posicao posicao, double probInfetar, double probImunidade, int turnoSemInfetar, int turnosEmQuarentena) {
        this.posicao = posicao;
        this.probInfetar = probInfetar;
        this.probImunidade = probImunidade;
        this.turnoSemInfetar = turnoSemInfetar;
        this.turnosEmQuarentena = turnosEmQuarentena;
        //icon


        svgPessoa = new SVGPath();
        svgPessoa.setContent("M117.49326,98.27951a11.41528,11.41528,0,0,0-6.1519-8.2847c-10.697-5.33411-34.17046-13.212-34.17046-13.212V69.03229l.65343-.49313a22.42566,22.42566,0,0,0,8.51484-14.25174l.13151-.826h.637a8.66287,8.66287,0,0,0,8.01764-5.39167A9.43369,9.43369,0,0,0,96.30471,43.5a8.67979,8.67979,0,0,0-.61644-3.21363A4.48227,4.48227,0,0,0,93.95,37.49191l-2.16572-1.315.53834-2.35062c3.91226-17.0544-9.29979-32.41574-27.04046-32.839C64.85067.979,64.42327.975,64,.98315,63.57669.975,63.14933.979,62.71783.98726c-17.74068.42329-30.95269,15.78463-27.04046,32.839l.53834,2.35062L34.05,37.49191a4.48232,4.48232,0,0,0-1.73833,2.79448A8.67976,8.67976,0,0,0,31.69529,43.5a9.4333,9.4333,0,0,0,1.17942,4.56973,8.66286,8.66286,0,0,0,8.0176,5.39167h.637l.13151.826a22.42566,22.42566,0,0,0,8.51484,14.25174l.65343.49313v7.75047s-23.4734,7.87794-34.17042,13.212a11.4151,11.4151,0,0,0-6.1519,8.2847c-1.83285,10.697-2.15747,28.7418-2.15747,28.7418H119.65073S119.32607,108.97654,117.49326,98.27951Z");
        svgPessoa.setScaleX(0.7);

        panePessoa = new StackPane();
        panePessoa.getChildren().add(svgPessoa);
        panePessoa.setScaleX(0.4);
        panePessoa.setScaleY(0.3);
    }

    public boolean isInfetada() {
        return infetada;
    }

    public boolean isImune() {
        return imune;
    }

    public boolean tentaFicarImune(){
        Random r = new Random();
        if(r.nextDouble() < probImunidade ) imune = true;
        if(isImune()) svgPessoa.setFill(Color.DARKGREEN);
        return isImune();
    }

    public boolean isQuarentena() {
        return quarentena;
    }

    public void setQuarentena(boolean quarentena) {
        this.quarentena = quarentena;
        if(quarentena){
            panePessoa.setStyle("-fx-border-color: darkorange;-fx-border-width: 6;-fx-border-insets: 5;");

            svgPessoa.setFill(Color.DARKRED);
            this.infetada = false;

        }else{
            panePessoa.setStyle("-fx-border-color: none;");
            svgPessoa.setFill(Color.BLACK);
        }
    }

    public int getDiasInfetados() {
        return diasInfetados;
    }

    public int getTurnosEmQuarentena() {
        return turnosEmQuarentena;
    }

    public void setTurnosEmQuarentena(int turnosEmQuarentena) {
        this.turnosEmQuarentena = turnosEmQuarentena;
        if(this.turnosEmQuarentena < 0 ) this.turnosEmQuarentena = 0;
    }

    public int decTurnoEmIsolamento(){
        this.turnosEmQuarentena--;
        if(this.turnosEmQuarentena < 0 ) this.turnosEmQuarentena = 0;
        return this.turnosEmQuarentena;
    }

    public Posicao getPosicao() {
        return new Posicao(posicao.getX(),posicao.getY());
    }

    public void move(Direcao direcao){
        posicao.move(direcao);
    }

    public void infetar(){
        this.setInfetada(true);
    }

    public void setInfetada(boolean infetada) {
        this.infetada = infetada;
    }

    public StackPane getPanePessoa() {
        return panePessoa;
    }



    @Override
    public String toString() {
        return "Pessoa{" +
                "infetada=" + infetada +
                ", imune=" + imune +
                ", quarentena=" + quarentena +
                ", diasInfetados=" + diasInfetados +
                ", diasEmQuarentena=" + turnosEmQuarentena +
                ", posicao=" + posicao +
                ", probInfetar=" + probInfetar +
                ", probImunidade=" + probImunidade +
                ", turnoSemInfetar=" + turnoSemInfetar + "\n" +
                '}';
    }

    public void setPosicao(Posicao pos) {
        this.posicao = pos;
    }

    public void contactoPessoaInfetada() {
        contactoInfetada = true;
        if(podeSerInfetado){
            if((new Random().nextDouble()) < probInfetar){
                infetada = true;
                podeSerInfetado=false;
            }
        }
        svgPessoa.setFill(Color.YELLOW);
    }

    public void resetContacto() {
        contactoInfetada = false;
        if(!this.isQuarentena())svgPessoa.setFill(Color.BLACK);
    }

    public boolean isContactoInfetada() {
        return contactoInfetada;
    }

    public int getTurnoSemInfetarCount() {
        return turnoSemInfetarCount;
    }

    public void setTurnoSemInfetarCount(int turnoSemInfetarCount) {
        this.turnoSemInfetarCount = turnoSemInfetarCount;
    }

    public boolean getPodeSerInfetado() {
        return podeSerInfetado;
    }

    public int getTurnoSemInfetar() {
        return turnoSemInfetar;
    }

    public void setPodeSerInfetado(boolean podeSerInfetado) {
        this.podeSerInfetado = podeSerInfetado;
    }
}
