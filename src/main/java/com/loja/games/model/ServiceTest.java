package com.loja.games.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.games.repository.UsuarioRepository;

@Service
public class ServiceTest {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	
	
	//@PostConstruct
	//public void execute() {
		
//		Usuario usuario = new Usuario();
//		
//		usuario.setNome("Nome teste");
//		usuario.setApelido("Apelidado");
//		usuario.setEmail("nogueira@gmail.com");
//		usuario.setCPF("12312312310");
//
//		usuarioRepository.save(usuario);
//		
//	}

}
