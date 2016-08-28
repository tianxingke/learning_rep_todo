package com.test.paixu;

import com.test.utils.Utils;

/**
 * 堆排序，不使用下标0，数组从1开始
 * 
 * @author tianxingke
 * 
 */
@SuppressWarnings("all")
public class HeadSort {

	public HeadSort() {

	}

	/**
	 * 上浮
	 * 
	 * @param a
	 */
	public void swim(int[] a, int n) {
		int N = a.length;
		while (a[n] > a[n / 2] && n <= N && n / 2 > 1) {
			Utils.swap(a, n, n / 2);
		}
	}

	/**
	 * 下沉
	 * 
	 * @param a
	 * @param n
	 */
	public void sink(int[] a, int n) {
		int N = a.length;
		while ((a[n] < a[2 * n] || a[n] < a[2 * n + 1]) && n <= N) {
			if (a[2 * n] > a[2 * n + 1])
				Utils.swap(a, n, 2 * n);
			else
				Utils.swap(a, n, 2 * n + 1);
		}
	}

	/**
	 * 构建最大堆
	 * @param a
	 */
	public void bulidMaxHeap(int[] a ){
		
		
	}
	
	
	public static void main(String[] args) {

		int[] arr = { 3, 5, 8, 4, 2, 7, 6, 1, 9, 13, 12, 11 };

	}

}
