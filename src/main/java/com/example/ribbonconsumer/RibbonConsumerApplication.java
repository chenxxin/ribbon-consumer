package com.example.ribbonconsumer;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableHystrix
//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication
@SpringCloudApplication
public class RibbonConsumerApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean getServlet(){

		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();

		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);

		registrationBean.setLoadOnStartup(1);

		registrationBean.addUrlMappings("/actuator/hystrix.stream");

		registrationBean.setName("HystrixMetricsStreamServlet");

		return registrationBean;
	}

}
