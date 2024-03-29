package br.edu.unoesc.spring.progII.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.spring.progII.model.Usuario;
import br.edu.unoesc.spring.progII.repository.UsuarioRepository;

@RestController
public class GreetingsController {
//	@GetMapping("/")
//	public String index() {
//		return "Hello World!";
//	}

	@GetMapping("/mensagem/")
	public String index2() {
		return "One piece no";
	}

	@RequestMapping(value = "/Mensagem2/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String texto(@PathVariable String name) {
		return "Hello, " + name + " !";
	}

	@Autowired // injeção de dependência
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "testeGravar/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String testeGravar(@PathVariable String nome) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuarioRepository.save(usuario);
		return "Gravado";
	}

	@GetMapping(value = "listatodos")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuario() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		Usuario user = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long iduser) {
		usuarioRepository.deleteById(iduser);
		return new ResponseEntity<String>("Usuario excluido com sucesso", HttpStatus.OK);
	}

	@GetMapping(value = "buscaruserid")
	@ResponseBody
	public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser) {
		Usuario usuario = usuarioRepository.findById(iduser).get();
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não informado para atualizar", HttpStatus.OK);
		}
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@GetMapping(value = "buscarpornome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> bucarpornome(@RequestParam(name = "nome") String nome) {
		List<Usuario> usuario = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}

}
