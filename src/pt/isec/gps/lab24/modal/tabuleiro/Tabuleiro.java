package pt.isec.gps.lab24.modal.tabuleiro;

import pt.isec.gps.lab24.Commons;
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
    private int numRecuperados;


    public Tabuleiro(int numLinhas, int numColunas, int numMaxPessoas, int numInfetadosInicial, int tempoMaxIsolamento, int turnoSemInfetar, double probInfetar, double probImunidade) {
        this.numLinhas = numLinhas;
        this.numColunas = numColunas;
        pessoas = new ArrayList<>();
        this.numInfetadosInicial = numInfetadosInicial;
        this.tempoMaxIsolamento = tempoMaxIsolamento;
        this.numRecuperados = 0;

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
        //mover as pessoas
        int pos = 0;
        for (Pessoa pessoa : pessoas) {
            if(pessoa.isImune()) pessoa.setInfetada(false);
            if (pessoa.isQuarentena()) {
                if (pessoa.decTurnoEmIsolamento() == 0) {
                    pessoa.setQuarentena(false);
                    pessoa.tentaFicarImune();
                    pessoa.setInfetada(false);
                    pessoa.setTurnosEmQuarentena(tempoMaxIsolamento);
                    this.numRecuperados++;
                }
            }
            if(!pessoa.getPodeSerInfetado()){
                if(pessoa.getTurnoSemInfetarCount() == pessoa.getTurnoSemInfetar()){
                    pessoa.setPodeSerInfetado(true);
                    pessoa.setTurnoSemInfetarCount(0);
                }
                else {
                    pessoa.setTurnoSemInfetarCount(pessoa.getTurnoSemInfetarCount() + 1);
                }
            }
            pessoa.resetContacto(); // retoma cor original antes de validar se esteve em contacto com uma pessoa infetada no turno atual
            //interação entre pessoas
            pessoaInterage(pessoa);
            podeMover = !pessoa.isQuarentena();
            List<Integer> direcoesTestadas = new ArrayList<>();

            while (podeMover) {
                int dir = new Random().nextInt(4);
                if(direcoesTestadas.size() >= 4  ) break; //tentou todas as direções sai do ciclo
                if(direcoesTestadas.contains(dir)) continue; //se já testou essa direção, tenta outra
                if(!direcoesTestadas.contains(dir)) direcoesTestadas.add(dir); //adiciona esta direção ás direçoes testadas
                switch (dir) {
                    case 0:
                        if (pessoaPodeMover(pessoa, Direcao.BAIXO)) {
                            tabuleiro[pessoa.getPosicao().getX()][pessoa.getPosicao().getY()] = -1;
                            pessoa.move(Direcao.BAIXO);
                            tabuleiro[pessoa.getPosicao().getX()][pessoa.getPosicao().getY()] = pos;
                            podeMover = false;
                        }
                        break;
                    case 1:
                        if (pessoaPodeMover(pessoa, Direcao.CIMA)) {
                            tabuleiro[pessoa.getPosicao().getX()][pessoa.getPosicao().getY()] = -1;
                            pessoa.move(Direcao.CIMA);
                            tabuleiro[pessoa.getPosicao().getX()][pessoa.getPosicao().getY()] = pos;
                            podeMover = false;
                        }
                        break;
                    case 2:
                        if (pessoaPodeMover(pessoa, Direcao.ESQUERDA)) {
                            tabuleiro[pessoa.getPosicao().getX()][pessoa.getPosicao().getY()] = -1;
                            pessoa.move(Direcao.ESQUERDA);
                            tabuleiro[pessoa.getPosicao().getX()][pessoa.getPosicao().getY()] = pos;
                            podeMover = false;
                        }
                        break;
                    case 3:
                        if (pessoaPodeMover(pessoa, Direcao.DIREITA)) {
                            tabuleiro[pessoa.getPosicao().getX()][pessoa.getPosicao().getY()] = -1;
                            pessoa.move(Direcao.DIREITA);
                            tabuleiro[pessoa.getPosicao().getX()][pessoa.getPosicao().getY()] = pos;
                            podeMover = false;
                        }
                        break;
                    default:
                         break;
                }
            }
            pos++;
        }
    }

    private void pessoaInterage(Pessoa pessoa) {
        Pessoa pessoaAux;
        for(int i = pessoa.getPosicao().getY()-1; i<pessoa.getPosicao().getY()+2;i++){
            if(i<0 || i>=numLinhas ) continue;

            for(int j = pessoa.getPosicao().getX()-1; j<pessoa.getPosicao().getX()+2;j++){
                if(j<0 || j>=numColunas) continue;

                if(i!=pessoa.getPosicao().getY() && j!=pessoa.getPosicao().getX()){
                    if(tabuleiro[i][j] != -1){
                        pessoaAux = getPessoa(new Posicao(i,j));
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
        if(posicao.getX() < 0 || posicao.getX() > 9) return false;
        if(posicao.getY() < 0 || posicao.getY() > 9) return false;
        if(tabuleiro[posicao.getX()][posicao.getY()] == -1)
            return true;
        return false;
    }

    public boolean infetarPessoa(Posicao posPessoa){
        if(tabuleiro[posPessoa.getX()][posPessoa.getY()]!=-1) {
            getPessoa(posPessoa).infetar();
            return true;
        }
        return false;
    }

    public String isFimJogo(){
        //valida se  todas as pessoas estao infetadas
        String fimJogo = Commons.FIM_DE_JOGO_PERDEU;
        for (Pessoa p: pessoas){
            if(!p.isInfetada()) fimJogo = Commons.FIM_DE_JOGO_VITORIA;
        }
        //valida se  todas as pessoas não estao infetadas
        for (Pessoa p: pessoas){
            if(p.isInfetada()) fimJogo = "";
        }

        return fimJogo;
    }

    public Pessoa getPessoa(Posicao posPessoa){
        int i = tabuleiro[posPessoa.getX()][posPessoa.getY()];
        return pessoas.get(i);
    }

    public int getNumRecuperados() {
        return numRecuperados;
    }
}
