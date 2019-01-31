/**
 * 
 */
package com.xinhuan.examples;

import java.nio.file.Path;
import java.nio.file.Paths;

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
		 Path p = Paths.get("tmp/000751706200_20180203120628294_1.jpg");
		 System.out.println(p.toAbsolutePath());
		 String ret =  client.single(p.toAbsolutePath().toString());
		 System.out.println(ret);
		 transport.close();
	}

}
