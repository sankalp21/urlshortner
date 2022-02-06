package com.sankalp.urlshortner.model;

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
public class ClientRequest {
	private String name;
	private String host;
	private String port;
	private Long expireAfterMillis;
}
