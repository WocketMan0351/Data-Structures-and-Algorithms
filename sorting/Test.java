package sorting;

import java.util.Arrays;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Integer[] arr = new Integer[10];

		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(100);
		}

		Integer[] arr2 = Arrays.copyOf(arr, arr.length); // copy of unsorted arr

		// testing our merge-sort implementation...
		System.out.println("*** Merge Sort ***");
		System.out.println(Arrays.toString(arr));
		MergeSort.mergeSort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println();

		// testing our quick-sort implementation...
		System.out.println("*** Quick Sort ***");
		System.out.println(Arrays.toString(arr2));
		QuickSort.quickSortInPlace(arr2);
		System.out.println(Arrays.toString(arr2));
	}

}
