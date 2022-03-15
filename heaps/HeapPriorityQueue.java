package heaps;

import java.util.ArrayList;
import java.util.Comparator;

import lists.PositionalList;
import priority_queues.AbstractPriorityQueue;
import priority_queues.Entry;
import priority_queues.PriorityQueue;

/**
 * An implementation of a priority queue using as array-based heap. Insertion
 * and removal are O(log n), retrieving the size and element with min key are
 * O(1).
 */
public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	// primary collection of priority queue entries
	protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

	public HeapPriorityQueue() {
		super();
	}

	public HeapPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	/**
	 * Creates a priority queue initialized with the given key-value pairs. Allows
	 * an initially empty queue to be filled in O(n) time rather than repeatedly
	 * calling the insert operation which would run in O(n log n) time.
	 * 
	 * @param keys
	 * @param values
	 */
	public HeapPriorityQueue(K[] keys, V[] values) {
		super();
		for (int i = 0; i < Math.min(keys.length, values.length); i++) {
			heap.add(new PQEntry<>(keys[i], values[i]));
		}
		heapify();
	}

	/**
	 * Performs a bottom-up construction of the heap in linear time. Calls downheap
	 * from each position in the tree, starting with the deepest level and ending
	 * with the root of the tree.
	 */
	protected void heapify() {
		int startIndex = parent(size() - 1); // start at parent of last entry
		for (int i = startIndex; i >= 0; i--) { // loop until processing the root
			downheap(i);
		}
	}

	// utility method
	protected int parent(int i) {
		return i - 1;
	}

	// utility method
	protected int left(int i) {
		return 2 * i + 1;
	}

	// utility method
	protected int right(int i) {
		return 2 * i + 2;
	}

	// utility method
	protected boolean hasLeft(int i) {
		return left(i) < heap.size();
	}

	// utility method
	protected boolean hasRight(int i) {
		return right(i) < heap.size();
	}

	/**
	 * Exchanges the entries at indices i and j of the array list.
	 * 
	 * @param i int
	 * @param j int
	 */
	protected void swap(int i, int j) {
		Entry<K, V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	/**
	 * Moves the entry at index i higher, if necessary, to restore the heap-order
	 * property.
	 * 
	 * @param i int
	 */
	protected void upheap(int i) {
		while (i > 0) { // continue until reaching root (or break statement)
			int parent = parent(i);
			if (compare(heap.get(i), heap.get(parent)) >= 0) {
				break; // heap-order verified
			}
			swap(i, parent);
			i = parent; // continue from parent's position
		}
	}

	/**
	 * Moves the entry at index i low, if necessary, to restore the heap-order
	 * property.
	 * 
	 * @param i int
	 */
	protected void downheap(int i) {
		while (hasLeft(i)) {
			int leftIndex = left(i); // continue to bottom
			int smallChildIndex = leftIndex; // right child key may be smaller
			if (hasRight(i)) {
				int rightIndex = right(i);
				if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
					smallChildIndex = rightIndex; // right child key is smaller
				}
			}
			if (compare(heap.get(smallChildIndex), heap.get(i)) >= 0) {
				break; // heap-order property has been restored
			}
			swap(i, smallChildIndex);
			i = smallChildIndex; // continue from child's position
		}
	}

	public int size() {
		return heap.size();
	}

	public Entry<K, V> min() {
		if (heap.isEmpty()) {
			return null;
		}
		return heap.get(0);
	}

	// If we start with an initially empty heap, n successive calls to the insert
	// operation will run in O(n log n) time worse case.
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K, V> newest = new PQEntry<>(key, value);
		heap.add(newest);
		upheap(heap.size() - 1);
		return newest;
	}

	public Entry<K, V> removeMin() {
		if (heap.isEmpty()) {
			return null;
		}
		Entry<K, V> elementToRemove = heap.get(0);
		swap(0, heap.size() - 1); // put the element to remove at the end
		heap.remove(heap.size() - 1); // and remove it from the list
		downheap(0); // fix the new root
		return elementToRemove;
	}

	/**
	 * Sorts sequence S, using initially empty priority queue P to produce the
	 * order.
	 * 
	 * @param S PositionalList
	 * @param P PriorityQueue
	 */
	public static <E> void pgSort(PositionalList<E> S, PriorityQueue<E, ?> P) {
		int n = S.size();
		for (int i = 0; i < n; i++) {
			E element = S.remove(S.first());
			P.insert(element, null); // key = element, value = null
		}
		for (int i = 0; i < n; i++) {
			E element = P.removeMin().getKey();
			S.addLast(element); // smallest key in P is next placed in S
		}
	}

}
