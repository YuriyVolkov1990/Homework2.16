package com.example.homework2_16;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

//		SpringApplication.run(Application.class, args);
		long start1 = System.currentTimeMillis();

		sortSelection(generateRandomArray());
		System.out.println("sortSelection");
		System.out.println(System.currentTimeMillis() - start1);

		long start2 = System.currentTimeMillis();

		sortBubble(generateRandomArray());
		System.out.println("sortBubble");
		System.out.println(System.currentTimeMillis() - start2);


		long start = System.currentTimeMillis();

		sortInsertion(generateRandomArray());
		System.out.println("sortInsertion");
		System.out.println(System.currentTimeMillis() - start);
	}
	public static int[] generateRandomArray() {
		java.util.Random random = new java.util.Random();
		int[] arr = new int[1000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(100_000) + 200_000;
		}
		return arr;
	}
	private static void swapElements(int[] arr, int indexA, int indexB) {
		int tmp = arr[indexA];
		arr[indexA] = arr[indexB];
		arr[indexB] = tmp;
	}
	public static void sortBubble(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					swapElements(arr, j, j + 1);
				}
			}
		}
	}
	public static void sortSelection(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minElementIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minElementIndex]) {
					minElementIndex = j;
				}
			}
			swapElements(arr, i, minElementIndex);
		}
	}
	public static void sortInsertion(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int j = i;
			while (j > 0 && arr[j - 1] >= temp) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = temp;
		}
	}
}
