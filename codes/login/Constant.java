package com.demo.login;

public class Constant {
	//主地址
	public static final String http = "http://124.207.8.234:8887";
	
	//签到检查地址
	public static final String signincheck = http
			+ "/ltrc/dailyAction.do?method=checkDoneSignAndBack"; // 'flag':'sign'
	
	//登录页面
	public static final String loginpage = "http://124.207.8.234:8887/ltrc/login.jsp";
	
	//登录地址
	public static final String loginaction = "http://124.207.8.234:8887/ltrc/login.do?method=login";

	//签退检查地址
	public static final String signoutcheck = http
			+ "/ltrc/dailyAction.do?method=checkDoneSignAndBack&flag=signback"; // 'flag':'signback'
	
	//签退地址
	public static final String signout = http
			+ "/ltrc/dailyAction.do?method=signback";
}
