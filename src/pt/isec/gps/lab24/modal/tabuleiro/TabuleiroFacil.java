package pt.isec.gps.lab24.modal.tabuleiro;

import java.util.Random;

public class TabuleiroFacil extends Tabuleiro {
    public TabuleiroFacil(int numLinhas, int numColunas, int numMaxPessoas) {
        super(numLinhas, numColunas, numMaxPessoas, new Random().nextInt(4)+8, 7, 7, 0.15, 0.5);
    }
}
