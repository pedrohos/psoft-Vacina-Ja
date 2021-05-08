package com.ufcg.psoft.vacinaja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacinaja.dto.LoteDTO;
import com.ufcg.psoft.vacinaja.exceptions.LoteInvalidoException;
import com.ufcg.psoft.vacinaja.model.Lote;
import com.ufcg.psoft.vacinaja.model.Vacina;
import com.ufcg.psoft.vacinaja.repository.LoteRepository;
import com.ufcg.psoft.vacinaja.repository.VacinaRepository;

@Service
public class LoteServiceImpl implements LoteService {

	@Autowired
	private LoteRepository loteRepository;
	
	@Autowired
	private VacinaRepository vacinaRepository;
	
	@Override
	public Lote cadastrarLote(LoteDTO loteDTO) {
		validaLoteDTO(loteDTO);
		
		Optional<Vacina> optionalVacina = vacinaRepository.findById(loteDTO.getIdVacina());
		Vacina vacina = optionalVacina.get();
		
		Lote novoLote = new Lote(loteDTO.getDataDeValidade(), loteDTO.getNumeroDeDoses(), vacina);
		
		loteRepository.save(novoLote);
		
		return novoLote;
	}
	
	private void validaLoteDTO(LoteDTO loteDTO) {
		if ((loteDTO == null) ||
			(loteDTO.getDataDeValidade() == null) ||
			(loteDTO.getIdVacina() == null) ||
			(loteDTO.getNumeroDeDoses() == null)) {
			
			throw new LoteInvalidoException("ErroValidaLote: Todos os campos devem ser preenchidos.");
		}
		
		if (loteDTO.getNumeroDeDoses() <= 0) {
			throw new LoteInvalidoException("ErroValidaLote: Número de doses inválido.");
		}
		
		//Não deve ser possível cadastrar um lote vencido.
		
		Optional<Vacina> optionalVacina = vacinaRepository.findById(loteDTO.getIdVacina());
		
		if (!optionalVacina.isPresent()) {
			throw new LoteInvalidoException("ErroValidaLote: Vacina não cadastrada.");
		}
	}
}
