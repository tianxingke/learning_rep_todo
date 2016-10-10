package com.demo.login;

import java.util.Map;

public class Sign {
	loginMain lm = new loginMain();
	
	
	private void signCheck(){
		
	}
	
	public void sign() throws Exception {
		Map map = lm.loginAndGetCookie();
		String jessionid = map.get("jsessionid").toString();
		String remsg = map.get("remsg").toString();
	}
}
