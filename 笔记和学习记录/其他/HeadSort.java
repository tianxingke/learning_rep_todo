package com.demo.algorithm;

import com.utils.Utils;

/**
 * 堆排序，不使用下标0，数组从1开始
 * 
 * @author tianxingke 2016/9/2
 * 
 */
@SuppressWarnings("all")
public class HeapSort {

	public HeapSort() {

	}

	/**
	 * 上浮
	 * 
	 * @param a
	 */
	public static void swim(int[] arr, int index) {
		int N = arr.length - 1;
		int target = 1;
		while (index > 0 && index <= N) {
			int left = index * 2;
			int right = index * 2 + 1;

			// 有左子树而没有右子树
			if (left <= N && right > N) {
				if (arr[left] > arr[index])
					Utils.swap(arr, left, index);
				else
					break;
				target = left;
			}
			// 有左子树也有右子树
			if (left <= N && right <= N) {
				int max;
				max = (arr[left] - arr[right] < 0 ? right : left);
				if (arr[max] > arr[index])
					Utils.swap(arr, index, max);
				else
					break;
				target = max;
			}
			sink(arr, target, arr.length - 1);
			index = index / 2;
		}

	}

	/**
	 * 下沉
	 * 
	 * @param a
	 * @param n
	 */
	public static void sink(int[] arr, int index, int len) {
		int N = len;
		int max;
		while ((index * 2 <= N || index * 2 + 1 <= N) && index >= 1) {
			int left = index * 2;
			int right = index * 2 + 1;
			// 有左子树而没有右子树
			if (left <= N && right > N) {
				if (arr[left] > arr[index])
					Utils.swap(arr, left, index);
				else
					break;
				sink(arr, left, len);
			}

			// 有左子树也有右子树
			if (left <= N && right <= N) {
				if (arr[index] < arr[left] || arr[index] < arr[right]) {
					max = (arr[left] - arr[right] < 0 ? right : left);
					Utils.swap(arr, index, max);
					sink(arr, max, len);
				} else {
					break;
				}
			}

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
		for (int i = length / 2; i > 0; i--) {
			swim(arr, i);
		}

	}

	public static void mainHeapSort(int[] arr) {
		int length = arr.length;
		bulidMaxHeap(arr);
		swapLastIndex(arr);
	}

	/**
	 * 构建完成最大堆后循环进行最后一个元素和第一个元素的交换，并进行下沉操作
	 * 
	 * @param arr
	 */
	private static void swapLastIndex(int[] arr) {
		int N = arr.length;
		for (int i = N - 1; i > 1; i--) {

			Utils.swap(arr, 1, i);
			sink(arr, 1, i - 1);
			if (arr[1] < arr[i - 1])
				return;
			System.out.println("当前N： " + i);
			Utils.printNums(arr);
		}
	}

	public static void main(String[] args) {

		int[] arrtemp = { 3, 5, 8, 4, 2, 7, 6, 1, 9, 13, 12, 11 };// 12个
		int[] arr = new int[arrtemp.length + 1];
		for (int i = 0; i < arrtemp.length; i++) {
			arr[i + 1] = arrtemp[i];

		}
		System.out.println("排序前:");
		Utils.printNums(arr);
		mainHeapSort(arr);
		System.out.println("排序后:");
		Utils.printNums(arr);
	}

}
