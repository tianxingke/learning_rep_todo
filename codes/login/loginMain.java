package com.demo.login;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class loginMain {

	String parms = "loginSignal=0&username=lixingfu&password=lt0000&login.x=17&login.y=23";

	// 请求头参数
	public void setHeader(HttpURLConnection conn) {
		conn.setRequestProperty(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E)");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty(
				"Accept",
				"application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
		conn.setRequestProperty("Accept-Language", "zh-CN");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Host", "124.207.8.234:8887");
	}

	// 请求返回的msg信息
	public String getResponseMsg(InputStream cin) throws IOException {
		InputStreamReader in = new InputStreamReader(cin);
		BufferedReader reader = new BufferedReader(in);
		StringBuffer buf = new StringBuffer();
		String t;
		while ((t = reader.readLine()) != null) {
			buf.append(t);
		}
		return buf.toString();
	}

	Map<String, Object> loginAndGetCookie() throws Exception {
		Map map = new HashMap<String, Object>();
		URL url2 = new URL(Constant.loginaction);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		setHeader(conn);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		// conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes(parms);
		out.flush();
		out.close();

		int recode = conn.getResponseCode();
		String remsg = conn.getResponseMessage();
		String cookie = conn.getHeaderField("Set-Cookie");

		System.out.println("---------start login--------");
		System.out.println("code: " + recode);
		System.out.println("msg: " + remsg);
		System.out.println("JSESSIONID: " + cookie);

		String buf = getResponseMsg(conn.getInputStream());

		System.out.println("buf:" + buf);
		String jessionid = cookie.split(";")[0].split("=")[1].toString();
		System.out.println("---------login end--------------");

		map.put("jsessionid", jessionid);
		map.put("remsg", buf);
		return map;
	}

}
