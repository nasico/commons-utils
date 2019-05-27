package utils;

import java.util.List;

import com.nasico.wechat.offcialaccounts.ApiUtils;

import junit.framework.TestCase;

public class Test extends TestCase {
	public void testToken() {
		String token = ApiUtils.getToken();
		assertNotNull(token);
		List<String> ipList = ApiUtils.getCallbackip(token);
		assertNotNull(ipList);
	}
	
}
