package com.ufcg.psoft.vacinaja.service;

import com.ufcg.psoft.vacinaja.dto.AgendamentoDTO;
import com.ufcg.psoft.vacinaja.dto.CidadaoDTO;
import com.ufcg.psoft.vacinaja.dto.CidadaoUpdateDTO;
import com.ufcg.psoft.vacinaja.dto.CpfDTO;
import com.ufcg.psoft.vacinaja.model.Cidadao;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

public interface CidadaoService {

    public Optional<Cidadao> buscarCidadaoPeloCpf(String cpf);

    public Cidadao cadastrarCidadao(CidadaoDTO cidadaoDTO);

    public Cidadao atualizarCidadao(CidadaoUpdateDTO cidadaoUpdateDTO);

    public List<Cidadao> listarCidadao();

    public void deletarCidadao(CpfDTO cpfDTO);


	LocalDateTime agendarVacinacao(AgendamentoDTO agendamentoDTO);

}
