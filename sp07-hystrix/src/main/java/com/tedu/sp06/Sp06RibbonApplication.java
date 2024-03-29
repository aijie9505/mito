package com.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication 三者集成与@SpringCloudApplication

@SpringCloudApplication
public class Sp06RibbonApplication {
	
	//创建 RestTemplate 实例，并存入 spring 容器
	//LoadBalanced 负载均衡注解，会对 RestTemplate 实例进行封装,
	//创建动态代理对象，并切入（AOP）负载均衡代码，把请求分散分发到集群中的服务器
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		SimpleClientHttpRequestFactory cf = new SimpleClientHttpRequestFactory();
		cf.setConnectTimeout(1000);
		cf.setReadTimeout(1000);
		return new RestTemplate(cf);
		//RestTemplate 中默认的 Factory 实例中，两个超时属性默认是 -1，
		//未启用超时，也不会触发重试
	}

	public static void main(String[] args) {
		SpringApplication.run(Sp06RibbonApplication.class, args);
	}

}