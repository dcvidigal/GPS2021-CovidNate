package pt.isec.gps.lab24.modal;

import pt.isec.gps.lab24.modal.recursos.Dificuldade;
import pt.isec.gps.lab24.modal.tabuleiro.Tabuleiro;

public class Sistema {
    private final int NUMERO_LINHA = 10;
    private final int MUMERO_COLUNA = 10;
    private final int NUMERO_MAXIMO_PESSOAS = 30;

    private Tabuleiro tabuleiro;
    private Dificuldade dificuldade;

    public Sistema() {
        
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }
}
