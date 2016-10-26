package learning.demo.login;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Testlogindemo implements Runnable {
	Sign sign = null;
	String time = null;
	boolean flag = true;
	String what = "";

	public void run() {

		Calendar c = Calendar.getInstance();
		Calendar c2;
		Date d = new Date();
		int h, m, s;
		m = new Random().nextInt(15) + 30;
		s = new Random().nextInt(60);
		// 上午在8点，下午在18点进行启动,m 值区间[30,45]。但随机时间的开始时间必须大于windows定时任务的设定时间am: 8:29   pm: 18:09
		if (!(c.get(Calendar.HOUR_OF_DAY) > 9)
				&& !(c.get(Calendar.HOUR_OF_DAY) < 8)) {
			h = 8;
			what = "signin";
		} else if (!(c.get(Calendar.HOUR_OF_DAY) > 19)
				&& !(c.get(Calendar.HOUR_OF_DAY) < 18)) {
			h = 18;
			//下午设置为18:10左右
			m=11;
			what = "singout";
		} else {
			what = "error";
			h = 0;
		}


		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DATE), h, m, s);
		// 操作随机时间
		String ct = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(c
				.getTime());
		boolean result = false;
		String tt=null;
		while (flag) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
			}

			 c2 = Calendar.getInstance();

			// 当前时间
			 tt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(c2
					.getTime());
			// System.out.println("ct: " + ct);
			// System.out.println("tt: " + tt);

			// if (tt.compareTo(ct) == 0) {
			if (!"error".equals(what)
					&& c.get(Calendar.MINUTE) == c2.get(Calendar.MINUTE)
					&& (c.get(Calendar.SECOND) == c2.get(Calendar.SECOND))) {
				sign = new Sign();
				try {
					// boolean result =
					// 判断时间段，选择是何种操作
					if ("signin" == what) {
						result = sign.signMain_signin();
					} else {
						result = sign.signMain_signout();
					}

					if (result) {
						flag = false;
						Thread.currentThread().interrupt();
						System.out.println("程序退出。。。。。");
					} else {
						System.out.println("程序出错。。。。。");
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

	}

	public static void main(String[] args) throws Exception {
		new Thread(new Testlogindemo()).start();
	}
}
