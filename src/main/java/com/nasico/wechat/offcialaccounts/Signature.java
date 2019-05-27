package com.nasico.wechat.offcialaccounts;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.nasico.security.ShaUtils;

public class Signature {
	public static String check(String signature, String timestamp, String nonce, String echostr, String token) {

		Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
		map.put("timestamp", timestamp);
		map.put("nonce", nonce);
		map.put("echostr", echostr);
		map.put("token", token);

		Set<String> keySet = map.keySet();
		Iterator<String> iter = keySet.iterator();
		StringBuilder sb = new StringBuilder();
		while (iter.hasNext()) {
			String key = iter.next();
			sb.append(map.get(key));
			System.out.println(key + ":" + map.get(key));
		}
		
		return ShaUtils.encode(sb.toString());
	}
	
	public static void main(String[] args) {
		System.out.println(Signature.check("444338c4af0fb6ed04b9a455d7c880707c2e9fe5", "1558850933", "735512025", "8678074550903783295", "d78bd3699bc843eba95a2290e0bbe060"));
	}
}
