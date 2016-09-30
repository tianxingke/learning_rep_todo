package learning.demo.utils;

import java.util.List;

/**
 * list 转换成数组
 * @author tianxingke
 *
 */
public class ListToArray {
	
	
	public static int[] listToIntegerArray(List list){
		if(list.size()<=0){
			return null;
		}
		int[] number = new int[list.size()];
		for(int i =0; i< list.size();i++){
			number[i] = (int) list.get(i);
		}
		
		return number;
	}

}
