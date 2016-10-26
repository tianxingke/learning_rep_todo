package learning.demo.login;

import java.util.Date;

public class Constant {
	//主地址
	public static final String http = "http://124.207.8.234:8887";
	
	
	//登录页面
	public static final String loginpage = "http://124.207.8.234:8887/ltrc/login.jsp";
	
	//登录地址
	public static final String loginaction = "http://124.207.8.234:8887/ltrc/login.do?method=login";

	//签到检查地址
	public static final String signincheck = http
			+ "/ltrc/dailyAction.do?method=checkDoneSignAndBack&flag=sign"; // 'flag':'sign'
	
	//签到地址
	public static final String signin = http
			+ "/ltrc/dailyAction.do?method=sign";

	//签退检查地址
	public static final String signoutcheck = http
			+ "/ltrc/dailyAction.do?method=checkDoneSignAndBack&flag=signback"; // 'flag':'signback'
	
	//签退地址
	public static final String signout = http
			+ "/ltrc/dailyAction.do?method=signback";
	
	//操作出错
	public static final String logerror="操作出错！-------时间：" +new Date();
	
	//操作成功
	public static final String logsuccess="操作成功！-------时间：" +new Date();
 
}
