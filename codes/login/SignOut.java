package com.demo.login;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class SignOut {
	loginMain lm = new loginMain();

	public void signout() throws Exception {
		Map map = lm.loginAndGetCookie();
		String jessionid = map.get("jsessionid").toString();
		String remsg = map.get("remsg").toString();
		// System.out.println(jessionid);
		// System.out.println(remsg);
		signOutCheck(jessionid);
		//signDo(jessionid);
	}
	
	/**
	 * 进行签到
	 * @throws MalformedURLException 
	 */
	private void signDo(String jessionid) throws Exception{
		URL url = new URL(Constant.signincheck);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		lm.setHeader(conn);
		
	}
	
	/**
	 * get 方式检查是否已经签退
	 * @throws Exception
	 */
	private void signOutCheck(String jessionid) throws Exception{
		URL url = new URL(Constant.signincheck);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//lm.setHeader(conn);
		//
		conn.setRequestProperty("Accept", "application/json, text/javascript, */*");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
		conn.setRequestProperty("Accept-Language", "zh-cn");
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E)");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		//conn.setRequestProperty("", "");
		//
		conn.setRequestProperty("x-requested-with", "XMLHttpRequest");
		conn.setRequestMethod("GET");
		//conn.setRequestProperty("Cookie", "JSESSIONID="+jessionid+"; LANG=cn; VERSION-AF-%E7%8E%B0%E5%9C%BA%E9%97%AE%E9%A2%98%E9%9B%86%E9%94%A6=%E6%99%AE%E9%80%9A%E7%89%88; TUI=expert_fields=0&attachment_text_field=0&history_query=0&people_query=0&information_query=1&custom_search_query=0&custom_search_advanced=0; Bugzilla_login=708; Bugzilla_logincookie=6xlv8mUQBE; DEFAULTFORMAT=specific; LASTORDER=bug_status%2Cpriority%2Cassigned_to%2Cbug_id");
		conn.setRequestProperty("Cookie", "JSESSIONID="+jessionid+"; LANG=cn");

		conn.setReadTimeout(5*1000);
		//conn.connect();
		String msg = lm.getResponseMsg(conn.getInputStream());
		System.out.println(msg);
		
	}
	
	
	
}
