/**
 * 
 */
package com.xinhuan.examples.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import com.xinhuan.examples.IRecog;
import com.xinhuan.examples.JRecogDemo;
import com.xinhuan.examples.RecogResult;

/**
 * @author xinhuan
 *
 */
@Controller
public class VehicleRecogAPI {
	
	@Autowired
	CustomerStorageService customerStorageService;
	
	@Autowired
	RecogService recogService;
	
	@RequestMapping( value = "/recog",  method = { RequestMethod.POST })
	@ResponseBody	
	@CrossOrigin
	public List<RecogResult> recog(@RequestParam("img") MultipartFile img) {
		try {
			Path tmp = Paths.get("tmp");
			if (Files.notExists(tmp)) Files.createDirectories(tmp);
			byte[] buffer = new byte[8192];
			InputStream is = img.getInputStream();	
			Path pi = tmp.resolve(img.getOriginalFilename());
			
			OutputStream out = Files.newOutputStream(pi);
			int c = 0;
			while ((c = is.read(buffer)) != -1) {
				out.write(buffer, 0, c);
			}
			is.close();
			out.flush();
			out.close();			
			return recogService.single(pi.toAbsolutePath().toString());
		} catch (Exception e) {
			 System.err.println(e);
		}
		return Arrays.asList();
	}
	
	@RequestMapping(value = "/cmc/{cmcKey}")
	public String  cmc(@PathVariable(name = "cmcKey") String cmcKey, Model model) {
		return (customerStorageService.find(cmcKey)) ?"i0n2d3e4x6": "info";		
	}
	 
	 @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
	public  void handleBadRequests(HttpServletResponse response) throws IOException {
	     response.sendError(HttpStatus.BAD_REQUEST.value(), "please contact us first!");
	 }
}
