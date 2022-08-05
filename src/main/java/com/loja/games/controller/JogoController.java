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

import com.loja.games.model.Jogo;
import com.loja.games.model.Usuario;
import com.loja.games.repository.JogoRepository;

@RestController
@RequestMapping("/jogo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JogoController {

	@Autowired
	private JogoRepository jogoRepository;
	
	@GetMapping
	public ResponseEntity<List<Jogo>> getAll(){
		return ResponseEntity.ok(jogoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Jogo> getById(@PathVariable long id){
		return jogoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}	
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Jogo>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(jogoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Jogo> post(@Valid @RequestBody Jogo jogo){
		return ResponseEntity.status(HttpStatus.CREATED).body(jogoRepository.save(jogo));
	}
	
	@PutMapping
	public ResponseEntity<Jogo> put(@Valid @RequestBody Jogo jogo){
		return jogoRepository.findById(jogo.getId()).map(resposta -> ResponseEntity.status(HttpStatus.OK)
				.body(jogoRepository.save(jogo))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Optional<Jogo> jogo = jogoRepository.findById(id);
		
		if(jogo.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		jogoRepository.deleteById(id);
	}
	
}
