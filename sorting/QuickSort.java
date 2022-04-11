package sorting;

import java.util.Comparator;

import priority_queues.DefaultComparator;
import queues.LinkedQueue;
import queues.Queue;

/**
 * Sorts a sequence S of size n with an expected running time of O(n log n) and
 * worst-case running time of O(n^2), assuming two elements of S can be compared
 * in O(1) time.
 */
public class QuickSort {

	/**
	 * Cleaner interface for quickSort(Queue<K> S, Comparator<K> comp). Uses the
	 * default comparator.
	 */
	public static <K> void quickSort(Queue<K> S) {
		quickSort(S, new DefaultComparator<K>());
	}

	/**
	 * Quick-sort contents of a queue.
	 */
	public static <K> void quickSort(Queue<K> S, Comparator<K> comp) {
		int n = S.size();
		if (n < 2) {
			return; // queue is already sorted
		}
		// divide
		K pivot = S.first(); // using first as arbitrary pivot (easy to access with queue)
		Queue<K> L = new LinkedQueue<>();
		Queue<K> E = new LinkedQueue<>();
		Queue<K> G = new LinkedQueue<>();
		while (!S.isEmpty()) { // divide S into L, E, and G
			K element = S.dequeue();
			int c = comp.compare(element, pivot);
			if (c < 0) {
				L.enqueue(element); // element is less than pivot
			} else if (c == 0) {
				E.enqueue(element); // element is equal to pivot
			} else {
				G.enqueue(element); // element is greater than pivot
			}
		}
		// conquer
		quickSort(L, comp); // sort elements less than pi
		quickSort(G, comp); // sort elements greater than pivot
		// concatenate results
		while (!L.isEmpty()) {
			S.enqueue(L.dequeue());
		}
		while (!E.isEmpty()) {
			S.enqueue(E.dequeue());
		}
		while (!G.isEmpty()) {
			S.enqueue(G.dequeue());
		}
	}

	/**
	 * Cleaner interface for quickSortInPlac(Queue<K> S, Comparator<K> comp, int a,
	 * int b). Uses the default comparator.
	 */
	public static <K> void quickSortInPlace(K[] S) {
		quickSortInPlace(S, new DefaultComparator<K>(), 0, S.length - 1);
	}

	/**
	 * Sorts the subarray S[a...b], inclusive, in-place.
	 */
	public static <K> void quickSortInPlace(K[] S, Comparator<K> comp, int a, int b) {
		if (a >= b) {
			return; // subarray is already sorted
		}
		int left = a;
		int right = b - 1;
		K pivot = S[b];
		K temp; // temp object used for swapping
		while (left <= right) {
			// scan until reaching value equal to or larger than the pivot (or right marker)
			while (left <= right && comp.compare(S[left], pivot) < 0) {
				left++;
			}
			// scan until reaching value equal to or smaller than the pivot (or left marker)
			while (left <= right && comp.compare(S[right], pivot) > 0) {
				right--;
			}
			if (left <= right) { // indices did not strictly cross
				// swap values and shrink range
				temp = S[left];
				S[left] = S[right];
				S[right] = temp;
				left++;
				right--;
			}
		}
		// put pivot into its final place (currently marked by left index)
		temp = S[left];
		S[left] = S[b];
		S[b] = temp;
		// make recursive calls
		quickSortInPlace(S, comp, a, left - 1);
		quickSortInPlace(S, comp, left + 1, b);
	}

}
