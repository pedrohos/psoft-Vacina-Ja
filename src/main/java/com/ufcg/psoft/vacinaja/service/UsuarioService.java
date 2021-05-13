package com.ufcg.psoft.vacinaja.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacinaja.enums.PermissaoLogin;
import com.ufcg.psoft.vacinaja.exceptions.LoginException;
import com.ufcg.psoft.vacinaja.exceptions.UsuarioInvalidoException;
import com.ufcg.psoft.vacinaja.exceptions.ValidacaoTokenException;
import com.ufcg.psoft.vacinaja.model.Usuario;
import com.ufcg.psoft.vacinaja.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JWTService jwtService;

	public Usuario cadastrarUsuario(Usuario usuario) {
		if (usuarioRepository.findById(usuario.getEmail()).isPresent()) {
			throw new UsuarioInvalidoException("ErroEmailUsuarioJaExistente: Email informado ja em uso.");
		}
		return usuarioRepository.save(usuario);
	}

	public Usuario getUsuario(String email) {
		Optional<Usuario> optUsuario = usuarioRepository.findById(email);
		if (!optUsuario.isPresent()) {
			throw new UsuarioInvalidoException("ErroUsuarioNãoExistente: Usuario informado não cadastrado.");
		}
		return optUsuario.get();
	}

	public Usuario alterarSenha(String email, String novaSenha, String token) {
		Usuario usuario = getUsuario(email);
		if (jwtService.verificaPermissao(token, PermissaoLogin.ADMINISTRADOR)
				|| jwtService.verificaPermissaoEmail(token, email)) {
			usuario.setSenha(novaSenha);
			usuarioRepository.save(usuario);
			return usuario;
		}
		throw new ValidacaoTokenException("ErroValidacaoToken: Usuario nao tem permissao");
	}

	public Usuario removerUsuario(String email, String token) {
		Usuario usuario = getUsuario(email);
		if (jwtService.verificaPermissao(token, PermissaoLogin.ADMINISTRADOR)
				|| jwtService.verificaPermissaoEmail(token, email)) {
			usuarioRepository.delete(usuario);
			return usuario;
		}
		throw new ValidacaoTokenException("ErroValidacaoToken: Usuario nao tem permissao");
	}

	public boolean validarUsuarioSenhaPermissao(Usuario usuario) {
		Usuario usuarioConsulta = getUsuario(usuario.getEmail());
		if (usuarioConsulta.getSenha().equals(usuario.getSenha())) {
			if ((usuario.isPermissaoAdministrador() && !usuarioConsulta.isPermissaoAdministrador())
					|| (usuario.isPermissaoFuncionario() && !usuarioConsulta.isPermissaoFuncionario())
					|| (usuario.isPermissaoCidadao() && !usuarioConsulta.isPermissaoCidadao()))
				throw new LoginException("ErroLogin: Permissão solicitada não autorizada");
			return true;
		}
		throw new LoginException("ErroLogin: Usuario ou senha invalodos");
	}
}
