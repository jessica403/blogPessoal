package org.generation.blogPessoal.controller;

import java.util.List;
import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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

@RestController
@RequestMapping ("/Postagens")
@CrossOrigin ("*") //ACEITA REQUISIÇÃO DE QQ ORIGEM
public class PostagemController {
	@Autowired
	private PostagemRepository Repository; //AQUI É A INTERFACE DO MEU REPOSITORIO
	
	//****************AQUI COMEÇO USAR TODOS OS SERVIÇOS DA INTERFACE*****************
	@GetMapping
	public ResponseEntity <List<Postagem>> GetAll (){
		return ResponseEntity.ok(Repository.findAll());
	}
	@GetMapping("/{id}") // AQUI ENTRA O AUTO-INCREMENT DO ID
	public ResponseEntity<Postagem> GetById(@PathVariable long id) /*AQUI PODE SE USAR () É OPCIONAL*/{
		return Repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/Titulo/{/Titulo}")
	public ResponseEntity<List<Postagem>> GetByNome(@PathVariable String Titulo) {
		return ResponseEntity.ok(Repository.findAllByTituloContainingIgnoreCase(Titulo));
	}
	@PostMapping
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(Repository.save(postagem));
	}
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(Repository.save(postagem));
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Repository.deleteById(id);
	}//OK
}