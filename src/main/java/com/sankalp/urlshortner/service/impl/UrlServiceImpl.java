package com.sankalp.urlshortner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankalp.urlshortner.entity.CachedUrl;
import com.sankalp.urlshortner.entity.Client;
import com.sankalp.urlshortner.entity.Url;
import com.sankalp.urlshortner.model.UrlRequest;
import com.sankalp.urlshortner.model.UrlResponse;
import com.sankalp.urlshortner.repository.CachedUrlRepository;
import com.sankalp.urlshortner.repository.ClientRepository;
import com.sankalp.urlshortner.repository.UrlRepository;
import com.sankalp.urlshortner.service.UrlService;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UrlRepository urlRepository;
	
	@Autowired
	private CachedUrlRepository cachedUrlRepository;

	@Override
	public UrlResponse getShortUrl(UrlRequest request, String clientName) throws Exception{
		
		Optional<CachedUrl> cachedUrlOptional = cachedUrlRepository.findById(request.getLongUrl()+clientName);
		
		if(cachedUrlOptional.isPresent()) {
			return UrlResponse.builder().url(cachedUrlOptional.get().getUrl()).build();
		}
		
		Optional<Client> clientOptional = clientRepository.findByName(clientName);
		
		if(!clientOptional.isPresent()) {
			throw new NoSuchElementException("Client does not exists!!");
		}
		
		Client client = clientOptional.get();
		
		List<Url> urlList = client.getUrlList();
		
		Url longUrl;
		
		if(urlList.contains(request.getLongUrl())) {
			int index = urlList.indexOf(request.getLongUrl());
			longUrl = urlList.get(index);			
		}else {
			Url url = Url.builder().longUrl(request.getLongUrl()).build();
			longUrl = urlRepository.save(url);
			
			urlList.add(longUrl);
			client.setUrlList(urlList);
			client = clientRepository.save(client);
		}
		
		Long urlId = longUrl.getId();
		String encodedId = Base64.getEncoder().encodeToString(String.valueOf(urlId).getBytes());
		String shortUrl = "http://" + client.getHost() + ":" + client.getPort() +"/"+encodedId;
		
		cachedUrlRepository.save(
					CachedUrl.builder()
						.id(request.getLongUrl()+clientName)
						.url(shortUrl)
						.build()
				);
		
		return UrlResponse.builder()
				.url(shortUrl).build();
	}

	@Override
	public UrlResponse getLongUrl(String encryptedId) throws Exception {
		Optional<CachedUrl> cachedUrlOptional = cachedUrlRepository.findById(encryptedId);
		
		if(cachedUrlOptional.isPresent()) {
			return UrlResponse.builder().url(cachedUrlOptional.get().getUrl()).build();
		}
		
		String stringId = new String(Base64.getDecoder().decode(encryptedId), "UTF-8");
		Long id = Long.decode(stringId);
		
		Optional<Url> urlOptional = urlRepository.findById(id);
		
		if(!urlOptional.isPresent()) {
			throw new NoSuchElementException("Url does not exists!!");
		}
		
		Url url = urlOptional.get();
		
		cachedUrlRepository.save(
				CachedUrl.builder()
					.id(encryptedId)
					.url(url.getLongUrl())
					.build()
			);
		
		return UrlResponse.builder().url(url.getLongUrl()).build();
	}

}
