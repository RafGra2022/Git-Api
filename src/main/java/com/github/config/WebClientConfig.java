package com.github.config;


import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

	@Value("${webclient.github.url}")
	private String url;
	@Value("${webclient.timeout}")
	private Integer timeout;
	
	@Bean
	public WebClient gitHubApi() {
		HttpClient client = HttpClient.create()
				  .responseTimeout(Duration.ofSeconds(timeout));
		
		return WebClient.builder()
				.baseUrl(url)
				.clientConnector(new ReactorClientHttpConnector(client))
				.build();
				
	}
}
