/**
 * 
 */
package com.xinhuan.examples;

import java.io.IOException;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;



/**
 * @author xinhuan
 *
 */


enum ContentType {
	FILE, URL, MAT, SERIAL_OBJ
}



public class JRecogDemo {

	//core是库的名称，且能在系统环境变量LD_LIBRARY_PATH或者JVM参数jna.library.path中能搜索到libcore.so文件
	static {
		Native.register("core");
	}

	public static native void coreInitContext() ;
	public static native int recogSingleJson(String res, int contentType, PointerByReference bufp);

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		coreInitContext();  // only invoke one time
		
		PointerByReference bufp = new PointerByReference();
		int len= 0;
		Long start = System.currentTimeMillis();
		String fileName = args.length > 0 ? args[0] : "1.jpg";
		for (int i=0 ; i<10; i++) {			
			len = recogSingleJson(fileName, 0, bufp);
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


