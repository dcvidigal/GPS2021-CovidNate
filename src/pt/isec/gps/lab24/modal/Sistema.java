package pt.isec.gps.lab24.modal;

import pt.isec.gps.lab24.modal.recursos.Dificuldade;
import pt.isec.gps.lab24.modal.tabuleiro.Tabuleiro;
import pt.isec.gps.lab24.modal.tabuleiro.TabuleiroDificil;
import pt.isec.gps.lab24.modal.tabuleiro.TabuleiroFacil;
import pt.isec.gps.lab24.modal.tabuleiro.TabuleiroNormal;

public class Sistema {
    private final int NUMERO_LINHA = 10;
    private final int MUMERO_COLUNA = 10;
    private final int NUMERO_MAXIMO_PESSOAS = 30;

    private Jogador jogador;
    private Tabuleiro tabuleiro;
    private Dificuldade dificuldade;

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public void iniciarTabuleiro(){
        switch (dificuldade){
            case FACIL:
                tabuleiro = new TabuleiroFacil(NUMERO_LINHA, MUMERO_COLUNA, NUMERO_MAXIMO_PESSOAS);
                break;
            case NORMAL:
                tabuleiro = new TabuleiroNormal(NUMERO_LINHA, MUMERO_COLUNA, NUMERO_MAXIMO_PESSOAS);
                break;
            case DIFICIL:
                tabuleiro = new TabuleiroDificil(NUMERO_LINHA, MUMERO_COLUNA, NUMERO_MAXIMO_PESSOAS);
                break;
        }
    }
}
