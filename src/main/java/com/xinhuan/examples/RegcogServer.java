/**
 * 
 */
package com.xinhuan.examples;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import com.xinhuan.examples.Recog.Iface;

/**
 * @author link
 * 
 * mvn exec:java -Dexec.mainClass="com.xinhuan.examples.RegcogServer"
 *
 */
public class RegcogServer {

	public static Recog.Processor<Iface> processor;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		IRecog.INSTANCE.coreInitContext();
		
		try {
			processor = new Recog.Processor<Recog.Iface>(new Handler());
			TServerTransport serverTransport = new TServerSocket(9090);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class Handler implements Recog.Iface {

		@Override
		public String single(String res) throws TException {
			PointerByReference bufp = new PointerByReference();
			int len= 0;
			len = IRecog.INSTANCE.recogSingleJson(res, 0, bufp);
			if (len > 0) {
				Pointer p = bufp.getValue();
				byte[] buffer = p.getByteArray(0, len);			
				String content = StringEscapeUtils.unescapeJava(new String(buffer, 0, len));
				return content;
			}
			return "[]";
		}
	}
}
