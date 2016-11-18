package learning.demo.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Testlogindemo implements Runnable {
	Sign sign = null;
	String time = null;
	boolean flag = true;
	String what = "";
	String info = "";

	public void run() {

		Calendar c = Calendar.getInstance();
		Calendar c2;
		Date d = new Date();
		int h, m, s;
		m = new Random().nextInt(10) + 35;
		s = new Random().nextInt(60);
		// 上午在8点，下午在18点进行启动,m 值区间[30,45]。但随机时间的开始时间必须大于windows定时任务的设定时间am: 8:30
		// pm: 18:09
		if ((c.get(Calendar.HOUR_OF_DAY) > 7) && (c.get(Calendar.HOUR_OF_DAY) < 9)) {
			h = 8;
			what = "signin";
		} else if ((c.get(Calendar.HOUR_OF_DAY) > 16) && (c.get(Calendar.HOUR_OF_DAY) < 19)) {
			h = 18;
			// 下午设置为18:01左右
			m = 1;
			what = "singout";
		} else {
			what = "error";
			h = 0;
		}

		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), h, m, s);
		// 操作随机时间
		String ct = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(c.getTime());
		boolean result = false;
		String tt = null;
		info += "程序启动>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + "\r";
		info += "约定时间 ： " + ct + "\r";
		System.out.println(info);
		Calendar clong = null;
		while (flag) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
			}

			c2 = Calendar.getInstance();

			
			if (clong == null)
				clong = c2;
			// 当前时间
			tt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(c2.getTime());

			int longtime = (c2.get(Calendar.HOUR_OF_DAY) - clong.get(Calendar.HOUR_OF_DAY));
			if (longtime > 1) {
				String tlong = "";
				tlong = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(clong.getTime());
				info += "超过运行规定时间区间：2小时,区间[" + tlong + "--->" + tt + "]\r";
				flag = false;
				Thread.currentThread().interrupt();
				break;
			}

			if (!"error".equals(what) && c.get(Calendar.MINUTE) == c2.get(Calendar.MINUTE) && (c.get(Calendar.SECOND) == c2.get(Calendar.SECOND))) {
				sign = new Sign();
				try {
					// boolean result =
					// 判断时间段，选择是何种操作
					info += "进入操作步骤...." + "\r";
					if ("signin" == what) {

						for (int i = 0; i < 5 && result == false; i++) {
							info += "signin,请求开始：i=" + i + ",result=" + result + "\r";

							result = true;// ceshi
							System.out.println("in " + tt);

							// result = sign.signMain_signin();
							info += "signin,请求结束：i=" + i + ",result=" + result + "\r";

						}
					} else {
						for (int i = 0; i < 5 && result == false; i++) {
							info += "signout,请求开始：i=" + i + ",result=" + result + "\r";

							System.out.println("out " + tt);
							result = true;

							// result = sign.signMain_signout();
							info += "signout,请求结束：i=" + i + ",result=" + result + "\r";

						}
					}

					if (result) {
						flag = false;
						Thread.currentThread().interrupt();
						info += "操作成功!" + "\r";
					} else {
						flag = false;
						Thread.currentThread().interrupt();
						info += "操作失败!";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 释放资源
			result = false;
			sign = null;
			tt = null;
			c2 = null;
		}
		info += "程序结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + "\r\r";
		File file = null;
		try {
			file = new File("D:/sign/signlog_" + c.get(Calendar.YEAR) + (c.get(Calendar.MONTH) + 1) + ".txt");
			Utlis.writeLog(info, file);
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) throws Exception {
		boolean open = new Testlogindemo().judgeAction(null);
		if (open) {
			new Thread(new Testlogindemo()).start();
		}
	}

	/**
	 * 从码云路径中读取判定是否需要执行动作
	 * https://git.oschina.net/tianxingke/demo_sign/blob/master
	 * /signflag.txt?dir=
	 * 0&filepath=signflag.txt&oid=35ca610df9d79fffd18adb27ebd0a24467c0fce2
	 * &sha=45be1e20586b618b1afe776c16775b92d15f62f2
	 */
	private boolean judgeAction(String url) {
		boolean action = false;
		// url =
		// "https://git.oschina.net/tianxingke/demo_sign/blob/master/signflag.txt";
		//url = "https://git.oschina.net/tianxingke/demo_sign/raw/master/signflag.txt";// 只显示元数据
		url = "https://git.oschina.net/tianxingke/demo_sign/raw/master/sign_test";
		try {
			URL reurl = new URL(url);
			InputStreamReader in = new InputStreamReader(reurl.openStream());
			BufferedReader bf = new BufferedReader(in);
			StringBuffer sb = new StringBuffer();
			String t;
			while ((t = bf.readLine()) != null) {
				sb.append(t);
			}
			System.out.println(sb);
			// yes 代表执行，no代表不执行
			if (sb.toString().contains("action=yes")) {
				action = true;
			} else if (sb.toString().contains("action=no")) {
				action = false;
			}
		} catch (Exception e) {
		}
		return action;
	}

}
