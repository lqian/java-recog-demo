/**
 * 
 */
package com.xinhuan.examples;

import java.io.IOException;

import org.apache.commons.text.StringEscapeUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author link
 *
 */
public class IRecogTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws Exception {
		IRecog.INSTANCE.coreInitContext();

		PointerByReference bufp = new PointerByReference();
		int len= 0;
		Long start = System.currentTimeMillis();
		for (int i=0 ; i<10; i++) {
			len = IRecog.INSTANCE.recogSingleJson("1.jpg", 0, bufp);
		}
		System.out.println("average recognize cost: " + ( System.currentTimeMillis() - start) / 10 + " ms");
		if (len > 0) {
			Pointer p = bufp.getValue();
			byte[] buffer = p.getByteArray(0, len);			
			String content = StringEscapeUtils.unescapeJava(new String(buffer, 0, len));
			System.out.println(content);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, false);
			RecogResult[] results = mapper.readValue(content,  RecogResult[].class);
			System.out.println(results[0].plateNo);
		}
		else {
			System.out.println("recognize not successfully!");
		}
		
	}

}
