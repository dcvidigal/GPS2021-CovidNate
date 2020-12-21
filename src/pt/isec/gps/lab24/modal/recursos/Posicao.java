package pt.isec.gps.lab24.modal.recursos;

import java.util.Objects;

public class Posicao {
    private int x;
    private int y;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(Direcao direcao){
        switch (direcao){
            case ESQUERDA:
                --x;
                break;
            case DIREITA:
                ++x;
                break;
            case CIMA:
                --y;
                break;
            case BAIXO:
                y++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicao posicao = (Posicao) o;
        return x == posicao.x && y == posicao.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
