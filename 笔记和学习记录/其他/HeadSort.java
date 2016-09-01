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
	public static void swim(int[] arr, int index) {
		int N = arr.length-1;

		while (index > 0 && index <= N) {
			int left = index*2;
			int right = index*2 +1;
			
			//有左子树而没有右子树
			if(left<=N&&right>N){
				if(arr[left]>arr[index]	)
					Utils.swap(arr, left, index);
			}
			//有左子树也有右子树
			if(left<=N && right<=N){
				int max ;
				max=(arr[left]-arr[right]<0?right:left);
				if(arr[max]>arr[index])
					Utils.swap(arr, index, max);
			}
			
			index=index/2;
		}

	}

	/**
	 * 下沉
	 * 
	 * @param a
	 * @param n
	 */
	public static void sink(int[] a, int n) {
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
	 * 
	 * @param a
	 */
	public static void bulidMaxHeap(int[] arr) {
		int length = arr.length;
		// 先从最后一个节点元素的父节点开始
		for(int i=length/2;i>0;i--){
			swim(arr,i);
		}

	}

	public static void mainHeapSort(int[] arr) {
		int length = arr.length;
		bulidMaxHeap(arr);

	}

	public static void main(String[] args) {

		int[] arrtemp = { 3, 5, 8, 4, 2, 7, 6, 1, 9, 13, 12, 11 };//12个
		int[] arr =new int[arrtemp.length+1];
		for(int i=0;i<arrtemp.length;i++){
			arr[i+1]=arrtemp[i];
					
		}
		System.out.println("排序前:");
		Utils.printNums(arr);
		mainHeapSort(arr);
		System.out.println("排序后:");
		Utils.printNums(arr);
	}

}
