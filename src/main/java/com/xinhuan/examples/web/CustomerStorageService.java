/**
 * 
 */
package com.xinhuan.examples.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author xinhuan
 *
 */
 
@Configuration
@Component
@PropertySource("classpath:/demo.properties")
public class CustomerStorageService {
	
	@Value("${cmc.resource}")
	String cmcResource;	 
	
	Set<String> evolutionKeys = new HashSet<String>();

	public CustomerStorageService() {
		super();
		 
		new Thread() {
			public void run() {
				while (true) {
					read();
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) { 
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	

	boolean find(String name) {
		return evolutionKeys.contains(name);
	}
	
	
	void read() {
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get(cmcResource));
			evolutionKeys.clear();
			String line = null;
			while ((line = reader.readLine()) != null) {
				evolutionKeys.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
