package com.elegps.tscweb.comm;

/**
 * ������
 * @author Leo
 *
 */
public class Comm {

	public final static boolean isEmpty(String par){
		if(par == null || par.equals("")){
			return true;
		}else{
			return false;
		}
	}
}
