package pt.isec.gps.lab24.modal.tabuleiro;

import pt.isec.gps.lab24.modal.Pessoa;
import pt.isec.gps.lab24.modal.recursos.Direcao;
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
        criaPessoas(numMaxPessoas, turnoSemInfetar, probInfetar, probImunidade, tempoMaxIsolamento);
        //incializar X pessoas infetadas aleatoriamente
        while(numInfetadosInicial-- > 0){
            Random r = new Random();
            int pos = r.nextInt(numMaxPessoas);
            if(!pessoas.get(pos).isInfetada()) pessoas.get(pos).infetar();
            else numInfetadosInicial++;
        }
    }

    private void iniciaTabuleiro() {
        tabuleiro = new int[numColunas][numLinhas];
        for(int i=0;i<numColunas;i++){
            for(int j=0;j<numLinhas;j++){
                tabuleiro[i][j]=-1;
            }
        }
    }

    private void criaPessoas(int numMaxPessoas,int turnoSemInfetar, double probInfetar, double probImunidade, int turnosEmQuarentena){
        for(int i=0; i<numMaxPessoas; i++){
            pessoas.add(new Pessoa(novaPosicao(i), probInfetar, probImunidade, turnoSemInfetar,turnosEmQuarentena));
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

    public void proximoTurno() {
        boolean podeMover;
        iniciaContagendoTempo();
        validaFimJogo();

        //mover as pessoas
        for (Pessoa pessoa : pessoas) {
            if (pessoa.isQuarentena()) {
                pessoa.setTurnosEmQuarentena(pessoa.getTurnosEmQuarentena() - 1);
                if (pessoa.getTurnosEmQuarentena() == 0) {
                    pessoa.setQuarentena(false);
                    pessoa.tentaFicarImune();
                    pessoa.setInfetada(false);
                }
            }
            //interação entre pessoas
            pessoaInterage(pessoa);
            podeMover = !pessoa.isQuarentena();
            while (podeMover) {
                switch (new Random().nextInt(4)) {
                    case 0:
                        if (pessoaPodeMover(pessoa, Direcao.BAIXO)) {
                            pessoa.move(Direcao.BAIXO);
                            podeMover = false;
                        }
                        break;
                    case 1:
                        if (pessoaPodeMover(pessoa, Direcao.CIMA)) {
                            pessoa.move(Direcao.CIMA);
                            podeMover = false;
                        }
                        break;
                    case 2:
                        if (pessoaPodeMover(pessoa, Direcao.ESQUERDA)) {
                            pessoa.move(Direcao.ESQUERDA);
                            podeMover = false;
                        }
                        break;
                    case 3:
                        if (pessoaPodeMover(pessoa, Direcao.DIREITA)) {
                            pessoa.move(Direcao.DIREITA);
                            podeMover = false;
                        }
                        break;
                }
            }
        }
    }

    private void pessoaInterage(Pessoa pessoa) {
        Pessoa pessoaAux;
        for(int i = pessoa.getPosicao().getY()-1; i<pessoa.getPosicao().getY()+2;i++){
            for(int j = pessoa.getPosicao().getX()-1; j<pessoa.getPosicao().getX()+2;j++){
                if(i!=pessoa.getPosicao().getY() && j!=pessoa.getPosicao().getX()){
                    if(tabuleiro[i][j] == -1){
                        pessoaAux = getPessoa(new Posicao(j,i));
                        if(pessoaAux.isInfetada()){
                            pessoa.contactoPessoaInfetada();
                        }
                    }
                }
            }
        }
    }

    private boolean pessoaPodeMover(Pessoa pessoa, Direcao direcao) {
        Posicao posicao = new Posicao(pessoa.getPosicao().getX(),pessoa.getPosicao().getY());
        posicao.move(direcao);
        if(tabuleiro[posicao.getY()][posicao.getX()] != -1)
            return true;
        return false;
    }

    public boolean infetarPessoa(Posicao posPessoa){
        if(tabuleiro[posPessoa.getY()][posPessoa.getX()]!=-1) {
            getPessoa(posPessoa).infetar();
            return true;
        }
        return false;
    }

    protected void validaFimJogo(){
        System.out.println("validaFimJogo: Falta implementar");
    }

    private void iniciaContagendoTempo() {
        System.out.println("iniciaContagendoTempo: Falta implementar");
    }

    public Pessoa getPessoa(Posicao posPessoa){
        int i = tabuleiro[posPessoa.getX()][posPessoa.getY()];
        return pessoas.get(i);
    }

}
