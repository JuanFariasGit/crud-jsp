package br.com.dominio.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class CriptografiaUtil {
	public String md5(String senha) {
		String strMd5 = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			strMd5 = hash.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strMd5;
	}
}
