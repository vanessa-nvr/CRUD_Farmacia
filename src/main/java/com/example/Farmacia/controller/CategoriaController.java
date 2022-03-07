package com.example.Farmacia.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.Farmacia.model.CategoriaModel;
import com.example.Farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<CategoriaModel>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> PegaPeloId(@PathVariable long id){
		return repository.findById(id) 
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}") 
	public ResponseEntity<List<CategoriaModel>> PegaPeloNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<CategoriaModel> post(@RequestBody CategoriaModel categoria){ //No corpo da requisição o objeto categoria é do tipo CategoriaModel
		return ResponseEntity.status(HttpStatus.CREATED) //Devolve um status
				.body(repository.save(categoria)); //Salva na base de dados e devolve no body
	}
	
	@PutMapping
	public ResponseEntity<CategoriaModel> put(@RequestBody CategoriaModel categoria){
		return ResponseEntity.status(HttpStatus.OK)
				.body(repository.save(categoria));
	}
	
	@DeleteMapping("/{id}") 
	public void delete(@PathVariable long id){
		repository.deleteById(id);
	}

}
