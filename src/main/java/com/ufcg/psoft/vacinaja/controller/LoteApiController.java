package com.ufcg.psoft.vacinaja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.vacinaja.dto.LoteDTO;
import com.ufcg.psoft.vacinaja.exceptions.LoteInvalidoException;
import com.ufcg.psoft.vacinaja.model.Lote;
import com.ufcg.psoft.vacinaja.service.LoteService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoteApiController {

	@Autowired
	private LoteService loteService;
	
	@RequestMapping(value = "/lote/", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarLote(@RequestBody LoteDTO loteDTO) {
		ResponseEntity<?> response;
		
		try {
			Lote loteCadastrado = loteService.cadastrarLote(loteDTO);
			
			response = new  ResponseEntity<Lote>(loteCadastrado, HttpStatus.CREATED);
			
		} catch (LoteInvalidoException lIE) {
			 response = new ResponseEntity<>(lIE.getMessage(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
}
