package learning.demo.login;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Testlogindemo implements Runnable {
	Sign sign = null;
	String time=null;
	boolean flag=true;

	public void run() {
		Calendar c = Calendar.getInstance();
		Date d = new Date();
		//int m =new Random().nextInt(18)+40;
		//int s = new Random().nextInt(60);
		int m = 00;
		int h = 17;
		int s =11;
		c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE),h, m, s);
		String ct = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(c.getTime());
		
		while(flag){
			Calendar c2 = Calendar.getInstance();
			String tt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(c2.getTime());
			System.out.println("ct: "+ct);
			System.out.println("tt: " +tt);
			if(tt.compareTo(ct)==0){
				Sign sign = new Sign();
				try {
					boolean result = sign.signMain_signin();
					if(result){
						flag=false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//Thread.currentThread().interrupt();
				//break;
			}
			System.out.println();
			if (sign == null){
				sign = new Sign();
			}
			if(time==null){
				time = "2016-10-17 08:"+String.valueOf(m)+":"+s;
			}
			System.out.println(time);
			
			try {
				Thread.sleep(400);
				//sign.signMain_signin();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		Sign sign = new Sign();

		//sign.signMain_signin();
		//sign.signMain_signout();
		
		new Thread(new Testlogindemo()).start();
	}
}
