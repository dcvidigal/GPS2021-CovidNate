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

    public Tabuleiro(int numLinhas, int numColunas, int numMaxPessoas, int numInfetadosInicial, int tempoMaxIsolamento, int turnoSemInfetar, double probInfetar, double probImunidade) {
        this.numLinhas = numLinhas;
        this.numColunas = numColunas;
        pessoas = new ArrayList<>();
        this.numInfetadosInicial = numInfetadosInicial;
        this.tempoMaxIsolamento = tempoMaxIsolamento;

        criaPessoas(numMaxPessoas, turnoSemInfetar, probInfetar, probImunidade);
    }

    private void criaPessoas(int numMaxPessoas,int turnoSemInfetar, double probInfetar, double probImunidade){
        for(int i=0; i<numMaxPessoas; i++){
            pessoas.add(new Pessoa(novaPosicao(), probInfetar, probImunidade, turnoSemInfetar));
        }
    }

    private Posicao novaPosicao() {
        Posicao pos;
        boolean repetido = false;

        while(true){
            pos = new Posicao(new Random().nextInt(numColunas), new Random().nextInt(numLinhas));
            for (Pessoa p: pessoas){
                if(p.getPosicao().equals(pos)){
                    repetido = true;
                }
            }
            if(repetido == false){
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
                ", pessoas=" + pessoas +
                ", numInfetadosInicial=" + numInfetadosInicial +
                ", tempoMaxIsolamento=" + tempoMaxIsolamento +
                '}';
    }
}
