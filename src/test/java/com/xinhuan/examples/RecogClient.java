/**
 * 
 */
package com.xinhuan.examples;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author xinhuan
 *
 */
public class RecogClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		TTransport transport = new TSocket("localhost", 9090);
		 transport.open();
		 TProtocol protocol = new  TBinaryProtocol(transport);
		 Recog.Client client = new Recog.Client(protocol);
		String ret =  client.single("a big news");
		 System.out.println("return: " + ret);
		 transport.close();
		 
		 
	}

}
