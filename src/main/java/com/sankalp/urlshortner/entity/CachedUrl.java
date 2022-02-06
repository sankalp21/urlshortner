package com.sankalp.urlshortner.entity;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@RedisHash(value = "cached_url")
public class CachedUrl {
	
	@Id
	private String id;
	private String url;

}
