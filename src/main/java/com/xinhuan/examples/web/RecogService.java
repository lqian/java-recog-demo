/**
 * 
 */
package com.xinhuan.examples.web;

import java.util.Arrays;
import java.util.List;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinhuan.examples.Recog;
import com.xinhuan.examples.RecogResult;

/**
 * @author link
 *
 */
public class RecogService {

	TTransport transport;

	TProtocol protocol ;
	Recog.Client client ;

	ObjectMapper mapper = new ObjectMapper();

	public RecogService(String host, int port) throws Exception {
		transport =  new TSocket(host, port);
		transport.open();
		protocol = new  TBinaryProtocol(transport);
		client = new Recog.Client(protocol);
	}

	public List<RecogResult> single(String fullname) {
		try {
			String ret =  client.single(fullname);
			return Arrays.asList(mapper.readValue(ret, RecogResult[].class));
		} catch (Exception e) {
		}
		return Arrays.asList();
	}	 
}
