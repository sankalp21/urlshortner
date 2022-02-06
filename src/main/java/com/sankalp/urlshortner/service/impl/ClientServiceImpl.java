package com.sankalp.urlshortner.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankalp.urlshortner.entity.Client;
import com.sankalp.urlshortner.model.ClientRequest;
import com.sankalp.urlshortner.repository.ClientRepository;
import com.sankalp.urlshortner.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void onBoardClient(ClientRequest request) {
		Client client = Client.builder()
				.name(request.getName())
				.host(request.getName())
				.expireAfterMillis(request.getExpireAfterMillis())
				.port(request.getPort()).build();
		
		clientRepository.save(client);
	}

	@Override
	public ClientRequest updateClient(Client client) {
		
		Client updatedClient = clientRepository.save(client);
		
		return ClientRequest.builder()
				.host(updatedClient.getHost())
				.name(updatedClient.getName())
				.port(updatedClient.getPort())
				.expireAfterMillis(updatedClient.getExpireAfterMillis())
				.build();
	}

	@Override
	public ClientRequest getClient(Long id) {
		Optional<Client> clientOptional = clientRepository.findById(id);
		
		if(clientOptional.isPresent()) {
			return ClientRequest.builder()
				.host(clientOptional.get().getHost())
				.name(clientOptional.get().getName())
				.port(clientOptional.get().getPort())
				.expireAfterMillis(clientOptional.get().getExpireAfterMillis())
				.build();
		}
		
		return null;
	}

}
