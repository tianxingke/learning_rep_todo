package learning.demo.login;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Sign {
	boolean flag = false; // 成功标志
	boolean hasSign = false; // 已签标志
	HttpURLConnection conn = null;
	loginMain lm = new loginMain();

	public boolean signMain_signin() throws Exception {
		Thread.sleep(2000);
		Map map = lm.loginAndGetCookie(null);
		if (map == null) {
			signlog(Constant.logerror);
			return false;
		}
		String jessionid = map.get("jsessionid").toString();
		//String remsg = map.get("remsg").toString();
		String msg = signCheck(jessionid, Constant.signincheck);
		String temp = msg.replace("{", "").replace("}", "").replace("'", "");
		String s = temp.split(":")[1].toString();
		String restr = "";
		if (s.contains("您已经签到过了")) {
			// System.out.println(s);
			return true;
		} else {
			restr = sign(jessionid, Constant.signin);
		}
		if (restr.contains("欢迎使用龙图日常管理系统")) {
			flag = true;
		}

		return flag;
	}

	public boolean signMain_signout() throws Exception {
		Map map = lm.loginAndGetCookie(null);
		if (map == null) {
			signlog(Constant.logerror);
			return false;
		}
		String jessionid = map.get("jsessionid").toString();
		//String remsg = map.get("remsg").toString();

		// signCheck(jessionid, Constant.signin);
		String restr = sign(jessionid, Constant.signout);
		//System.out.println("return : " + restr);
		if (restr.contains("签退操作成功")) {
			System.out.println("签退操作成功");
			flag = true;
		}
		return flag;
	}

	private void signlog(String logstr) {
		System.out.println(logstr);
	}

	/**
	 * 进行签到/退
	 * 
	 * @throws MalformedURLException
	 */
	public String sign(String jessionid, String urlstr) throws Exception {
		String buf = null;
		try {
			//mac地址：B0%3a56%3a20%3a52%3a41%3a53
			String parms = "cnwsignoutVO.macaddress=&cnwsignoutVO.province=%B1%B1%BE%A9%CA%D0&cnwsignoutVO.city=%CA%D0%CF%BD%C7%F8&cnwsignoutVO.county=%B6%AB%B3%C7%C7%F8&cnwsignoutVO.workAddress=%B0%EC%B9%AB%B5%D8&cnwsignoutVO.tel=&cnwsignoutVO.signremark=";
			URL url = new URL(urlstr);
			conn = (HttpURLConnection) url.openConnection();
			lm.setHeader(conn);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Cookie", "JSESSIONID=" + jessionid
					+ "; LANG=cn");
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

			buf = lm.getResponseMsg(conn.getInputStream());
		} catch (Exception e) {
		} finally {
			conn.disconnect();
		}
		return buf;
	}

	/**
	 * get 方式检查是否已经签到/退
	 * 
	 * @throws Exception
	 */
	private String signCheck(String jessionid, String urlstr) {
		String msg = null;
		try {
			URL url = new URL(urlstr);
			conn = (HttpURLConnection) url.openConnection();
			lm.setHeader(conn);
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5 * 1000);
			conn.setRequestProperty("Cookie", "JSESSIONID=" + jessionid
					+ "; LANG=cn");

			// conn.connect();
			msg = lm.getResponseMsg(conn.getInputStream());

			//System.out.println(msg);

		} catch (Exception e) {

		} finally {
			conn.disconnect();
		}
		return msg;
	}

}
