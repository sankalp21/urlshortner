package com.sankalp.urlshortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sankalp.urlshortner.entity.Client;
import com.sankalp.urlshortner.model.ClientRequest;
import com.sankalp.urlshortner.service.ClientService;

@RestController
@RequestMapping("api/v1")
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@PostMapping("client")
	public ResponseEntity onBoard(@RequestBody ClientRequest request) {
		clientService.onBoardClient(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("client")
	public ResponseEntity update(@RequestBody Client client) {
		ClientRequest UpdatedClient = clientService.updateClient(client);
		return ResponseEntity.ok().body(UpdatedClient);
	}
	
	@GetMapping("client/{id}")
	public ResponseEntity update(@PathVariable Long id) {
		ClientRequest Client = clientService.getClient(id);
		if(Client == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok().body(Client);
	}
}
