package com.ufcg.psoft.vacinaja.service;

import com.ufcg.psoft.vacinaja.dto.LoteDTO;
import com.ufcg.psoft.vacinaja.model.Lote;

public interface LoteService {
	
	public Lote cadastrarLote(LoteDTO loteDTO);

}