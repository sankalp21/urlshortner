package com.sankalp.urlshortner.service;

import com.sankalp.urlshortner.model.UrlRequest;
import com.sankalp.urlshortner.model.UrlResponse;

public interface UrlService {

	public UrlResponse getShortUrl(UrlRequest request, String clientName) throws Exception;
	public UrlResponse getLongUrl(String encryptedId) throws Exception;
}
