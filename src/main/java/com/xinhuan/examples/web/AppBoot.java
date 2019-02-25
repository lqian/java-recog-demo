/**
 * 
 */
package com.xinhuan.examples.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
 
 

/**
 * @author xinhuan
 *
 */
@Configuration
@SpringBootApplication
@PropertySource("classpath:/demo.properties")
public class AppBoot {
	
	@Value("${cmc.resource}")
	String cmcResource;	 
	
	@Value("${thrift.port:9090}")
	int port;
	
	@Value("${thrift.host:localhost}")
	String host;
	
	@Bean
	public RecogService recogService() throws Exception {
		return new RecogService(host, port);
	}
	
	@Bean 
	public CustomerStorageService createCustomerStorageService() {
		return new CustomerStorageService(cmcResource);
	}
	
//	@Bean
//    public MultipartConfigElement configElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxRequestSize(DataSize.ofMegabytes(20));
//        factory.setMaxFileSize(DataSize.ofMegabytes(18));
//
//        return factory.createMultipartConfig();
//    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppBoot.class, args);
	}
}
