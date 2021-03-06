package com.uai.uaigas.resources;

import java.net.URI;
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

import com.uai.uaigas.dto.EmailDTO;
import com.uai.uaigas.dto.LoginDTO;
import com.uai.uaigas.dto.UsuarioDTO;
import com.uai.uaigas.dto.UsuarioInsertDTO;
import com.uai.uaigas.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
	List<UsuarioDTO> list = service.findAll();
	return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
	UsuarioDTO dto = service.findById(id);
	return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody UsuarioInsertDTO dto) {
	UsuarioDTO newDto = service.insert(dto);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
	return ResponseEntity.created(uri).body(newDto);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UsuarioDTO> login(@Valid @RequestBody LoginDTO dto) {
	UsuarioDTO newDto = service.findByEmailAndPassword(dto.getEmail(), dto.getSenha());
	return ResponseEntity.ok().body(newDto);
    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO dto) {
	service.sendNewPassword(dto.getEmail());
	return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id) {
	service.delete(id);
	return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> update(@Valid @RequestBody UsuarioInsertDTO dto) {
	UsuarioDTO usuario = service.findByEmailAndPassword(dto.getEmail(), dto.getSenha());
	dto.setId(usuario.getId());
	usuario = service.update(dto);
	return ResponseEntity.ok().body(usuario);
    }

}
