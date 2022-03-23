package heaps;

import java.util.Comparator;

import priority_queues.AdaptablePriorityQueue;
import priority_queues.Entry;

public class HeapAdaptablePriorityQueue<K, V> extends HeapPriorityQueue<K, V> implements AdaptablePriorityQueue<K, V> {

	protected static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {
		private int index; // entry's current index within the heap

		public AdaptablePQEntry(K key, V value, int index) {
			super(key, value);
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	public HeapAdaptablePriorityQueue() {
		super();
	}

	public HeapAdaptablePriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	/**
	 * Validates entry e to ensure it is location-aware.
	 * 
	 * @param entry
	 * @return
	 * @throws IllegalArgumentException
	 */
	protected AdaptablePQEntry<K, V> validate(Entry<K, V> e) throws IllegalArgumentException {
		if (!(e instanceof AdaptablePQEntry)) {
			throw new IllegalArgumentException("Invalid entry");
		}
		AdaptablePQEntry<K, V> locator = (AdaptablePQEntry<K, V>) e; // safe cast
		int i = locator.getIndex();
		if (i >= heap.size() || heap.get(i) != locator) {
			throw new IllegalArgumentException("Invalid entry");
		}
		return locator;
	}

	/**
	 * Exchanges the entries at indices i and j of the array list.
	 */
	protected void swap(int i, int j) {
		super.swap(i, j); // perform the swap
		((AdaptablePQEntry<K, V>) heap.get(i)).setIndex(i); // reset entry's index
		((AdaptablePQEntry<K, V>) heap.get(j)).setIndex(j); // reset entry's index
	}

	/**
	 * Restores the heap property by moving the entry at index i upward/downward.
	 * 
	 * @param i int
	 */
	protected void bubble(int i) {
		if (i > 0 && compare(heap.get(i), heap.get(parent(i))) < 0) {
			upheap(i);
		} else {
			downheap(i);
		}
	}

	public Entry<K, V> insert(K k, V v) throws IllegalArgumentException {
		checkKey(k);
		Entry<K, V> newest = new AdaptablePQEntry(k, v, heap.size());
		heap.add(newest);
		upheap(heap.size() - 1);
		return newest;
	}

	public void remove(Entry<K, V> e) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(e);
		int i = locator.getIndex();
		if (i == heap.size() - 1) { // entry is at last position
			heap.remove(heap.size() - 1); // so just remove it
		} else {
			swap(i, heap.size() - 1);
			heap.remove(heap.size() - 1);
			bubble(i);
		}
	}

	public void replaceKey(Entry<K, V> e, K k) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(e);
		checkKey(k);
		locator.setKey(k);
		bubble(locator.getIndex());
	}

	public void replaceValue(Entry<K, V> e, V v) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(e);
		locator.setValue(v);
	}

}
