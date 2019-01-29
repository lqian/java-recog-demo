/**
 * 
 */
package com.xinhuan.examples.web;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.unit.DataSize;
 
 

/**
 * @author xinhuan
 *
 */
@Configuration
@SpringBootApplication
@PropertySource("classpath:/demo.properties")
public class AppBoot {
	
	@Bean
    public MultipartConfigElement configElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxRequestSize(DataSize.ofMegabytes(20));
        factory.setMaxFileSize(DataSize.ofMegabytes(18));

        return factory.createMultipartConfig();
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppBoot.class, args);
	}
}
