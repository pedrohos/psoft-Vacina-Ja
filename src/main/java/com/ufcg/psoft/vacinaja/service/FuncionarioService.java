package com.ufcg.psoft.vacinaja.service;

import com.ufcg.psoft.vacinaja.dto.FuncionarioDTO;
import com.ufcg.psoft.vacinaja.model.Funcionario;

public interface FuncionarioService {

	public Funcionario cadastrarFuncionario(FuncionarioDTO funcionarioDTO);

}