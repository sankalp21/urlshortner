package com.sankalp.urlshortner.service;


import com.sankalp.urlshortner.entity.Client;
import com.sankalp.urlshortner.model.ClientRequest;

public interface ClientService {
	public void onBoardClient(ClientRequest request);
	public ClientRequest updateClient(Client client);
	public ClientRequest getClient(Long id);
}
