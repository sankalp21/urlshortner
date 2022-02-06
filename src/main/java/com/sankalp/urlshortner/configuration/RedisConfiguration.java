package com.sankalp.urlshortner.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

	@Bean
	JedisConnectionFactory jedisFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration 
				= new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setDatabase(1);
		
		return new JedisConnectionFactory(redisStandaloneConfiguration, JedisClientConfiguration.builder().build());
	}
	
	@Bean
	RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(jedisFactory());
		
		return redisTemplate;
	}
}
