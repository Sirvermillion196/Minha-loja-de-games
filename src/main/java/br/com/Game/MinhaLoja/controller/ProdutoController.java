package br.com.Game.MinhaLoja.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.Game.MinhaLoja.model.ProdutoModel;
import br.com.Game.MinhaLoja.repository.ProdutoRepository;

@RestController // explicando para o spring que esta é nossa camada controller
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*") // origem da requisição
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> getById(@PathVariable long id) {
		return produtoRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ProdutoModel>> GetByNome(@PathVariable String titulo) {
		return ResponseEntity.ok(produtoRepository.findAllByTituloContainingIgnoreCase(titulo));

	}

	@PostMapping
	public ResponseEntity<ProdutoModel> postProduto(@Valid @RequestBody ProdutoModel produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)); // HTTP 201

	}

	@PutMapping
	public ResponseEntity<ProdutoModel> putProduto(@Valid @RequestBody ProdutoModel produto) {
		return produtoRepository.findById(produto.getIdProduto())
				.map(resp -> ResponseEntity.ok().body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		return produtoRepository.findById(id).map(resp -> {
			produtoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
