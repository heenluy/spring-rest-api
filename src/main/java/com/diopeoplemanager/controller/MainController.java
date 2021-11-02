package com.diopeoplemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.diopeoplemanager.Repository.PeopleRepository;
import com.diopeoplemanager.model.People;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class MainController {

	@Autowired
	private PeopleRepository peopleRepository;
	
	@GetMapping
	public List<People> listar() {
		
		return peopleRepository.findAll();
	}


	@GetMapping("/{id}")
	public ResponseEntity<People> getById(@PathVariable("id") Long id) {
		
		return peopleRepository.findById(id)
		.map(mapper -> ResponseEntity.ok(mapper))
		.orElse(ResponseEntity.notFound().build());
	}


	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<?> getPeopleByName(@PathVariable String name) {
		
		return new ResponseEntity<>(peopleRepository.findByName(name), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public People inserir(@RequestBody People people) {
		return peopleRepository.save(people);
	}

	@PutMapping("/{id}")
	public ResponseEntity<People> atualizar(@Valid @PathVariable Long id, @RequestBody People people) {
		
		if(!peopleRepository.existsById(id)) {
			ResponseEntity.notFound().build();
		}

		people.setId(id);
		people = peopleRepository.save(people);
		

		return ResponseEntity.ok(people);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		
		if(!peopleRepository.existsById(id)) {
			ResponseEntity.notFound().build();
		}

		peopleRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
