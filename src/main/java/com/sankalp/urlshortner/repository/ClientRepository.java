package com.sankalp.urlshortner.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sankalp.urlshortner.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
	Optional<Client> findByName(String name);
}
