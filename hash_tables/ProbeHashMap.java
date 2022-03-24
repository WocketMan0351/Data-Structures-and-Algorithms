package hash_tables;

import java.util.ArrayList;

import priority_queues.Entry;

/**
 * Implements a hash table that handles collisions with linear probing (and
 * thus, open addressing).
 */
public class ProbeHashMap<V, K> extends AbstractHashMap<K, V> {
	private MapEntry<K, V>[] table;
	private MapEntry<K, V> AVAILABLE = new MapEntry<>(null, null); // sentinel

	public ProbeHashMap() {
		super();
	}

	public ProbeHashMap(int capacity) {
		super(capacity);
	}

	public ProbeHashMap(int capacity, int prime) {
		super(capacity, prime);
	}

	/**
	 * Creates an empty table having length equal to current capacity.
	 */
	protected void createTable() {
		table = (MapEntry<K, V>[]) new MapEntry[capacity];
	}

	/**
	 * Returns true if the location is either empty or is the "AVAILABLE" sentinel.
	 * 
	 * @param i
	 * @return
	 */
	private boolean isAvailable(int i) {
		return (table[i] == null || table[i] == AVAILABLE);
	}

	/**
	 * Returns index with key k, or -(a + 1) such that k could be added at index a.
	 * 
	 * @param h
	 * @param k
	 * @return
	 */
	private int findSlot(int h, K k) {
		int available = -1; // no slot available so far
		int i = h; // index while scanning table
		do {
			if (isAvailable(i)) { // may be either empty or AVAILABLE
				if (available == -1) {
					available = i; // this is the first available slot
				}
				if (table[i] == null) {
					break; // if empty, search fails completely
				}
			} else if (table[i].getKey().equals(k)) {
				return i; // successful match
			}
			i = (i + 1) % capacity; // keep looking cyclically
		} while (i != h); // stop if we return to the start
		return -(available + 1); // search has failed;
	}

	/**
	 * Returns value associated with key k in bucket with hash value h, or else
	 * null.
	 */
	protected V bucketGet(int h, K k) {
		int i = findSlot(h, k);
		if (i < 0) {
			return null; // no match found
		}
		return table[i].getValue();
	}

	/**
	 * Associates key k with value v in bucket with hash h, returns old value.
	 */
	protected V bucketPut(int h, K k, V v) {
		int i = findSlot(h, k);
		if (i >= 0) {
			return table[i].setValue(v);
		}
		table[-(i + 1)] = new MapEntry<>(k, v);
		n++;
		return null;
	}

	/**
	 * Removes entry having key k from bucket with hash value h (if any).
	 */
	protected V bucketRemove(int h, K k) {
		int i = findSlot(h, k);
		if (i < 0) {
			return null;
		}
		V entryToRemove = table[i].getValue();
		table[i] = AVAILABLE;
		n--;
		return null;
	}

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 */
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		for (int i = 0; i < capacity; i++) {
			if (!isAvailable(i)) {
				buffer.add(table[i]);
			}
		}
		return buffer;
	}

}
