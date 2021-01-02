package pt.isec.gps.lab24.modal.tabuleiro;

import pt.isec.gps.lab24.modal.Pessoa;
import pt.isec.gps.lab24.modal.recursos.Posicao;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Tabuleiro {
    private int numLinhas;
    private int numColunas;
    private List<Pessoa> pessoas;
    private int numInfetadosInicial;
    private int tempoMaxIsolamento;
    private int tabuleiro [][];

    public Tabuleiro(int numLinhas, int numColunas, int numMaxPessoas, int numInfetadosInicial, int tempoMaxIsolamento, int turnoSemInfetar, double probInfetar, double probImunidade) {
        this.numLinhas = numLinhas;
        this.numColunas = numColunas;
        pessoas = new ArrayList<>();
        this.numInfetadosInicial = numInfetadosInicial;
        this.tempoMaxIsolamento = tempoMaxIsolamento;

        iniciaTabuleiro();
        criaPessoas(numMaxPessoas, turnoSemInfetar, probInfetar, probImunidade);
    }

    private void iniciaTabuleiro() {
        tabuleiro = new int[numColunas][numLinhas];
        for(int i=0;i<numColunas;i++){
            for(int j=0;j<numLinhas;j++){
                tabuleiro[i][j]=-1;
            }
        }
    }

    private void criaPessoas(int numMaxPessoas,int turnoSemInfetar, double probInfetar, double probImunidade){
        for(int i=0; i<numMaxPessoas; i++){
            pessoas.add(new Pessoa(novaPosicao(i), probInfetar, probImunidade, turnoSemInfetar));
        }
    }

    private Posicao novaPosicao(int indicePessoa) {
        Posicao pos;

        while(true){
            pos = new Posicao(new Random().nextInt(numColunas), new Random().nextInt(numLinhas));
            if(tabuleiro[pos.getX()][pos.getY()] == -1){
                tabuleiro[pos.getX()][pos.getY()] = indicePessoa;
                return pos;
            }
        }
    }

    public int getNumLinhas() {
        return numLinhas;
    }

    public int getNumColunas() {
        return numColunas;
    }

    @Override
    public String toString() {
        return "Tabuleiro{" +
                "numLinhas=" + numLinhas +
                ", numColunas=" + numColunas +
                ", numInfetadosInicial=" + numInfetadosInicial +
                ", tempoMaxIsolamento=" + tempoMaxIsolamento +
                "\n, pessoas=" + pessoas +
                '}';
    }

    public int[][] getTabuleiro() {
        return tabuleiro.clone();
    }

    public void proximoTurno(){
        iniciaContagendoTempo();
        validaFimJogo();


    }

    public boolean infetarPessoa(Posicao posPessoa){
        if(tabuleiro[posPessoa.getY()][posPessoa.getX()]!=-1) {
            getPessoa(posPessoa).infetar();
            return true;
        }
        return false;
    }

    protected void validaFimJogo(){
        System.out.println("Falta implementar");
    }

    private void iniciaContagendoTempo() {
        System.out.println("Falta implementar");
    }

    private Pessoa getPessoa(Posicao posPessoa){
        int i = tabuleiro[posPessoa.getY()][posPessoa.getX()];
        return pessoas.get(i);
    }
}
