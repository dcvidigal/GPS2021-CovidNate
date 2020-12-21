package pt.isec.gps.lab24.modal.tabuleiro;

import java.util.Random;

public class TabuleiroDificil extends Tabuleiro {
    public TabuleiroDificil(int numLinhas, int numColunas, int numMaxPessoas, int numInfetadosInicial, int tempoMaxIsolamento, int turnoSemInfetar, double probInfetar, double probImunidade) {
        super(numLinhas, numColunas, numMaxPessoas, new Random().nextInt(4)+1, 1, 1, 0.6, 0.25);
    }
}
