package pt.isec.gps.lab24.modal.tabuleiro;

import pt.isec.gps.lab24.Commons;

import java.util.Random;

public class TabuleiroDificil extends Tabuleiro {
    public TabuleiroDificil(int numLinhas, int numColunas, int numMaxPessoas) {
        super(numLinhas, numColunas, numMaxPessoas, new Random().nextInt(4)+1, 4, 4, 0.6, 0.25, Commons.DIFICIL);
    }
}
