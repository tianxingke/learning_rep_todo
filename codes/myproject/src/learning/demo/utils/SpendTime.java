package learning.demo.utils;


/**
 * 
 * @author tianxingke
 *
 */
public class SpendTime {
	
	/**
	 * 根据时间转换成易懂的格式
	 * @param startTime
	 * @param endTime
	 */
	public static void getSpendTime(Object startTime ,Object endTime){
		long start= Long.valueOf(startTime.toString());
		long end = Long.valueOf(endTime.toString());
		
		System.out.println("执行所耗费时间 ： " +String.valueOf(end - start));
	}

}
