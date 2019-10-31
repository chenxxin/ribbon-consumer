package com.example.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

	@Autowired
	RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
	public String helloService() {
		String url = "http://hello-service/hello";

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String s1 = responseEntity.getBody();

		return s1;
	}

	public String helloFallback() {
    	return "error";
	}
}
