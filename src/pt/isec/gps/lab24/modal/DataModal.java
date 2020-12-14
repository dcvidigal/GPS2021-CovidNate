package pt.isec.gps.lab24.modal;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class DataModel {

    private final ObservableList<Jogador> jogadores = FXCollections.observableArrayList();

    private final ObjectProperty<Jogador> jogador = new SimpleObjectProperty<>(null);

    public ObjectProperty<Jogador> jogadorProperty() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador.set(jogador);
    }

    public ObjectProperty<Jogador> currentPersonProperty() {
        return jogador ;
    }

    public final Jogador getCurrentPerson() {
        return jogador().get();
    }




    public final void setCurrentPerson(Jogador person) {
        jogador().set(person);
    }
    public ObservableList<Jogador> getPersonList() {
        return jogadores ;
    }
}