package com.ufcg.psoft.vacinaja.controller;

import com.ufcg.psoft.vacinaja.dto.PerfilVacinacaoDTO;
import com.ufcg.psoft.vacinaja.exceptions.PerfilVacinacaoInvalidoException;
import com.ufcg.psoft.vacinaja.model.PerfilVacinacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.vacinaja.dto.FuncionarioDTO;
import com.ufcg.psoft.vacinaja.exceptions.FuncionarioInvalidoException;
import com.ufcg.psoft.vacinaja.model.Funcionario;
import com.ufcg.psoft.vacinaja.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FuncionarioApiController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	/**
     * Cadastra uma funcionário a partir de:
     *   Cpf do funcionário;
     *   Cargo do funcionário;
     *   Local de trabalho do funcionário.
     *   
     * @param funcionarioDTO carrega as informações de cadastro do funcionário.
     * 
     * @return É retornado o funcionário cadastrado no banco de dados e o status da requisição.
     */
	@RequestMapping(value = "/funcionario/", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
		ResponseEntity<?> response;
		
		try {
			Funcionario funcionarioCadastrado = funcionarioService.cadastrarFuncionario(funcionarioDTO);
			
			response = new ResponseEntity<Funcionario>(funcionarioCadastrado, HttpStatus.CREATED);
		
		} catch (FuncionarioInvalidoException fIE){
            response = new ResponseEntity<>(fIE.getMessage(), HttpStatus.BAD_REQUEST);
        
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

	@RequestMapping(value = "/funcionario/definir-perfil-vacinacao", method = RequestMethod.POST)
	public ResponseEntity<?> definirPerfilVacinacao(@RequestBody PerfilVacinacaoDTO perfilVacinacaoDTO){
		ResponseEntity<?> response;
		try {
			PerfilVacinacao perfilVacinacaoCadastrado = funcionarioService.definirPerfilVacinacao(perfilVacinacaoDTO);
			response = new ResponseEntity<PerfilVacinacao>(perfilVacinacaoCadastrado, HttpStatus.CREATED);
		} catch (PerfilVacinacaoInvalidoException pvie){
			response = new ResponseEntity<>(pvie.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(value = "/funcionario/get-perfil-vacinacao", method = RequestMethod.GET)
	public ResponseEntity<?> listarPerfilVacinacao(){
		ResponseEntity<?> response;
		try {
			List<PerfilVacinacao> perfilVacinacaoCadastrado = funcionarioService.listarPerfilVacinacao();
			response = new ResponseEntity<List<PerfilVacinacao>>(perfilVacinacaoCadastrado, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}