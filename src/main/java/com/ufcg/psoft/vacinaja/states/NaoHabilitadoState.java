package com.ufcg.psoft.vacinaja.states;

import com.ufcg.psoft.vacinaja.model.RegistroVacinacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NaoHabilitadoState extends VacinacaoState {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    public NaoHabilitadoState() {

    }

    @Override
    public void atualizarEstado(RegistroVacinacao registroVacinacao) {
        registroVacinacao.setEstadoVacinacao(new HabilitadoPrimeiraDoseState());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
