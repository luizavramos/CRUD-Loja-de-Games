package com.loja.games.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.loja.games.model.Usuario;
import com.loja.games.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

		@Autowired
		private UsuarioRepository usuarioRepository;
		
		@GetMapping
		public ResponseEntity<List<Usuario>> getAll(){
			return ResponseEntity.ok(usuarioRepository.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Usuario> getById(@PathVariable long id){
			return usuarioRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			
		}	
		
		@GetMapping("/CPF/{CPF}")
		public ResponseEntity<List<Usuario>> getByCPF(@PathVariable String CPF){
			return ResponseEntity.ok(usuarioRepository.findAllByCPFContainingIgnoreCase(CPF));
		}
		
		@PostMapping
		public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario usuario){
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
		}
		
		@PutMapping
		public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario){
			return usuarioRepository.findById(usuario.getId()).map(resposta -> ResponseEntity.status(HttpStatus.OK)
					.body(usuarioRepository.save(usuario))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("/{id}")
		public void delete(@PathVariable long id) {
			Optional<Usuario> usuario = usuarioRepository.findById(id);
			
			if(usuario.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
			usuarioRepository.deleteById(id);
		}
		
	}


