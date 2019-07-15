/**
 * 
 */
package com.xinhuan.examples;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author link
 *
 */
public interface IRecog  extends Library {
	
	IRecog INSTANCE = Native.load("mlpdr", IRecog.class);
	
	public void coreInitContext() ;
	public int recogSingleJson(String res, int contentType, PointerByReference bufp);
}
