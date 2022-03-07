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

import com.example.Farmacia.model.ProdutoModel;
import com.example.Farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> PegaPeloId(@PathVariable long id){
		return repository.findById(id) 
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/produto/{produto}") 
	public ResponseEntity<List<ProdutoModel>> PegaPeloProduto(@PathVariable String produto){
		return ResponseEntity.ok(repository.findAllByProdutoContainingIgnoreCase(produto));
	}
	
	@PostMapping
	public ResponseEntity<ProdutoModel> post(@RequestBody ProdutoModel produto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<ProdutoModel> put(@RequestBody ProdutoModel produto){
		return ResponseEntity.status(HttpStatus.OK)
				.body(repository.save(produto));
	}
	
	@DeleteMapping("/{id}") 
	public void delete(@PathVariable long id){
		repository.deleteById(id);
	}

}
