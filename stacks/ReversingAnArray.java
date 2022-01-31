package stacks;

import java.util.Arrays;

public class ReversingAnArray {

	public static void main(String[] args) {
		Integer[] numbers = { 1, 3, 5, 7, 9, 11 }; // autoboxing

		System.out.println(Arrays.toString(numbers));
		reverse(numbers);
		System.out.println(Arrays.toString(numbers));
	}

	/**
	 * 
	 * @param arr the array to be reversed
	 * @return void
	 */
	public static <E> void reverse(E[] arr) {
		Stack<E> buffer = new ArrayStack<>(arr.length);
		for (int i = 0; i < arr.length; i++) {
			buffer.push(arr[i]);
			;
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = buffer.pop();
		}
	}

}
