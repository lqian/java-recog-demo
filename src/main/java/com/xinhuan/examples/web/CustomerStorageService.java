/**
 * 
 */
package com.xinhuan.examples.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author xinhuan
 *
 */
 
public class CustomerStorageService {
	
	String cmcResource;	 
	
	Map<String, Long> evolutionKeyMap = new HashMap<String, Long>();
	
	long period = 5 * 3600 * 24 * 1000;

	public CustomerStorageService(String cmcResource  ) {
		super();
		this.cmcResource = cmcResource; 
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
		Long start =  evolutionKeyMap.get(name);
		return start == null ? false : System.currentTimeMillis() - start < period;
	}
	
	
	void read() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (cmcResource == null) return ;
			InputStream stream = Files.newInputStream(Paths.get(cmcResource));
			Properties props = new Properties();
			props.load(stream);
			evolutionKeyMap.clear();
			for (Entry<Object, Object> e: props.entrySet()) {
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				Date start = sdf.parse(value);
				Long remain = System.currentTimeMillis() - start.getTime();
				if (remain < period) {
					evolutionKeyMap.put(key, start.getTime());
				}
				//else {
				// 	System.out.println("out of day evolutioin keys: " + key + " at " + value);
				//}
			}
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
