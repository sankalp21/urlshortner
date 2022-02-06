package com.sankalp.urlshortner.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sankalp.urlshortner.entity.Client;
import com.sankalp.urlshortner.entity.Url;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long>{
	
}
