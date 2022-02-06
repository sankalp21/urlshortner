package com.sankalp.urlshortner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sankalp.urlshortner.entity.CachedUrl;

@Repository
public interface CachedUrlRepository extends CrudRepository<CachedUrl, String>{

}
