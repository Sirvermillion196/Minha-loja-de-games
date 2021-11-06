package br.com.Game.MinhaLoja.controller;

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

import br.com.Game.MinhaLoja.model.CategoriaModel;
import br.com.Game.MinhaLoja.repository.CategoriaRepository;

//camada dedicada para criação de métodos para comunicação com o servidor

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*") // origem da requisição
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<CategoriaModel>> getAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> getById(@PathVariable long id) {
		return categoriaRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/genero/{genero}")
	public ResponseEntity<List<CategoriaModel>> GetByCategoria(@PathVariable String genero) {
		return ResponseEntity.ok(categoriaRepository.findAllByGeneroContainingIgnoreCase(genero));

	}

	@PostMapping
	public ResponseEntity<CategoriaModel> postCategoria(@RequestBody CategoriaModel categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria)); // HTTP 201

	}

	@PutMapping
	public ResponseEntity<CategoriaModel> putCategoria(@RequestBody CategoriaModel categoria) {
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria)); // HTTP 201

	}

	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable long id) {
		categoriaRepository.deleteById(id);

	}
}
