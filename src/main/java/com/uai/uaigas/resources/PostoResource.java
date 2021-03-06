package com.uai.uaigas.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uai.uaigas.dto.EnderecoDTO;
import com.uai.uaigas.dto.PostoDTO;
import com.uai.uaigas.services.EnderecoService;
import com.uai.uaigas.services.PostoService;

@RestController
@RequestMapping(value = "/posto")
public class PostoResource {
	
	@Autowired
	private PostoService service;
	
	@Autowired
	private EnderecoService serviceEndereco;

	@GetMapping
	public ResponseEntity<List<PostoDTO>> findAll() {
	    List<PostoDTO> list = service.findAll();
	    return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PostoDTO> findById(@PathVariable Long id) {
		PostoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/endereco/{city}")
	public ResponseEntity<List<PostoDTO>> findGasStationByCidade(@PathVariable String city) {
		List<EnderecoDTO> listEndereco = serviceEndereco.findGasStationByCidade(city);
		List<PostoDTO> listPosto = new ArrayList<PostoDTO>();
		listEndereco.forEach(enderecodto -> {
			Long id = enderecodto.getPosto().getId();
			listPosto.add(service.findById(id));
		});
		return ResponseEntity.ok().body(listPosto);
	}
	
	@PostMapping
	public ResponseEntity<PostoDTO> insert(@Valid @RequestBody PostoDTO dto) {
		PostoDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PostoDTO> update(@PathVariable Long id, @Valid @RequestBody PostoDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
}
