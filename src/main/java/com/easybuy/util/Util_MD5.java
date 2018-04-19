package com.easybuy.util;

/**
 * @author qin
 */

import org.apache.commons.codec.digest.DigestUtils;

public class Util_MD5 {
		
		/**
		 * md5加密
		 * @param value 加密前的值
		 * @return md5加密后的值
		 */
		public static String md5Hex(String value){
			return DigestUtils.md5Hex(value);
		}
		/**
		 * 三次MD5加密
		 * @param value 
		 * @return
		 */
		public static String md5Hex3(String value){
			for (int i = 0; i < 3; i++) {
				value=DigestUtils.md5Hex(value);
			}
			return value;
		}
		/**
		 * sha256加密
		 * @param value  要加密的值
		 * @return 加密后的值
		 */
		public static String sha256Hex(String value){
			return DigestUtils.sha256Hex(value);
		}
		/**
		 * sha512加密
		 * @param value
		 * @return
		 */
		public static String sha512Hex(String value){
			return DigestUtils.sha512Hex(value);
		}
		
		
		// 测试MD5加密
		/* 
		public static void main(String[] args) {
			System.out.println(Util_MD5.md5Hex("123"));
		}

		*/
		
	
	
	
}
