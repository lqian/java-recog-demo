/**
 * 
 */
package com.xinhuan.examples.web;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import com.xinhuan.examples.JRecogDemo;
import com.xinhuan.examples.RecogResult;

/**
 * @author xinhuan
 *
 */
@Controller
public class VehicleRecogAPI {
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	CustomerStorageService customerStorageService;
	
	static {
		JRecogDemo.coreInitContext();
	}
	
	@RequestMapping( value = "/recog",  method = { RequestMethod.POST })
	@ResponseBody	
	public List<RecogResult> recog(@RequestParam("img") MultipartFile img) {
		try {
			String res = "tmp_"+ System.currentTimeMillis() + ".jpg";
			img.transferTo(Paths.get(res));
			PointerByReference bufp = new PointerByReference();
			int len = JRecogDemo.recogSingleJson(res, 0, bufp);
			if (len > 0) {
				Pointer p = bufp.getValue();
				byte[] buffer = p.getByteArray(0, len);			
				String content = StringEscapeUtils.unescapeJava(new String(buffer, 0, len));
				return  Arrays.asList(mapper.readValue(content,  RecogResult[].class));
			}
		} catch (Exception e) {
			 System.err.println(e);
		}
		return Arrays.asList();
	}
	
	 @GetMapping(value = "/cmc/{cmcKey}")
	public String  cmc(@PathVariable(name = "cmcKey") String cmcKey) {
		if (customerStorageService.find(cmcKey)) {
			return "jsp";
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	 
	 @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
	public  void handleBadRequests(HttpServletResponse response) throws IOException {
	     response.sendError(HttpStatus.BAD_REQUEST.value(), "please contact us first!");
	 }
	 
}
