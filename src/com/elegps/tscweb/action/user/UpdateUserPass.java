/**
 * ²âÊÔĞŞ¸ÄÃÜÂëÀà
 * @author ACER
 * @date 2011-03-12
 */
package com.elegps.tscweb.action.user;

import com.elegps.tscweb.comm.MD5;

/**
 * 
 * @author ACER
 *@date 2011-03-12
 */
public class UpdateUserPass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "sheet";
		MD5 str2md5 = new MD5();
		System.out.println(str2md5.str2MD5(str));
	}

}
