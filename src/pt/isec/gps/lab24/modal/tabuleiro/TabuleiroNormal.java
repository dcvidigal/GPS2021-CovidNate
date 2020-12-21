package pt.isec.gps.lab24.modal.tabuleiro;

import java.util.Random;

public class TabuleiroNormal extends Tabuleiro {
    public TabuleiroNormal(int numLinhas, int numColunas, int numMaxPessoas) {
        super(numLinhas, numColunas, numMaxPessoas, new Random().nextInt(4)+4, 2, 3, 0.35, 0.35);
    }

}
