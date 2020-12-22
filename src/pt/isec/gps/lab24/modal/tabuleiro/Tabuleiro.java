package pt.isec.gps.lab24.modal.tabuleiro;

import pt.isec.gps.lab24.modal.Pessoa;
import pt.isec.gps.lab24.modal.recursos.Posicao;
import pt.isec.gps.lab24.modal.recursos.TabuleiroEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Tabuleiro {
    private int numLinhas;
    private int numColunas;
    private List<Pessoa> pessoas;
    private int numInfetadosInicial;
    private int tempoMaxIsolamento;
    private TabuleiroEnum tabuleiro [][];

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
        tabuleiro = new TabuleiroEnum[numColunas][numLinhas];
        for(int i=0;i<numColunas;i++){
            for(int j=0;j<numLinhas;j++){
                tabuleiro[i][j]=TabuleiroEnum.VAZIO;
            }
        }
    }

    private void criaPessoas(int numMaxPessoas,int turnoSemInfetar, double probInfetar, double probImunidade){
        for(int i=0; i<numMaxPessoas; i++){
            pessoas.add(new Pessoa(novaPosicao(), probInfetar, probImunidade, turnoSemInfetar));
        }
    }

    private Posicao novaPosicao() {
        Posicao pos;

        while(true){
            pos = new Posicao(new Random().nextInt(numColunas), new Random().nextInt(numLinhas));
            if(tabuleiro[pos.getX()][pos.getY()] == TabuleiroEnum.VAZIO){
                tabuleiro[pos.getX()][pos.getY()] = TabuleiroEnum.PESSOA;
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

    public TabuleiroEnum[][] getTabuleiro() {
        return tabuleiro.clone();
    }
}
