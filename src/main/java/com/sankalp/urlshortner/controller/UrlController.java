package com.sankalp.urlshortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sankalp.urlshortner.model.UrlRequest;
import com.sankalp.urlshortner.model.UrlResponse;
import com.sankalp.urlshortner.service.UrlService;

@RestController
@RequestMapping("api/v1/")
public class UrlController {
	
	@Autowired
	private UrlService urlService;

	@PostMapping("shortUrl")
	public ResponseEntity getShortUrl(@RequestBody(required = true) UrlRequest request, @RequestHeader(value="client", required = true) String clientName) {
		try {
			UrlResponse response = urlService.getShortUrl(request, clientName);
			return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("{encryptedId}")
	public ResponseEntity getLongUrl(@PathVariable(required = true) String encryptedId) {
		try {
			UrlResponse response = urlService.getLongUrl(encryptedId);
			return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
