/**
 * 
 */
package com.xinhuan.examples;



import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class APITest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			File file = new File(APITest.class.getResource("/test.jpg").getFile());
			MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
			entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
			.addBinaryBody("img", file, ContentType.DEFAULT_BINARY, file.getName());
			 
			HttpEntity httpEntity = entityBuilder.build();
			
			HttpUriRequest request = RequestBuilder
                    .post("http://192.168.1.107:9001/recog")
                    .setEntity(httpEntity)
                    .build();
			
			
			ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(request, responseHandler);
             
            System.out.println(responseBody);
            
		}

	}

}
