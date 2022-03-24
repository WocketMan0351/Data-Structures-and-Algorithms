package hash_tables;

import java.util.ArrayList;

import maps.UnsortedTableMap;
import priority_queues.Entry;

/**
 * Implements a hash table that handles collisions with separate chaining.
 */
public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
	// a fixed capacity array of unsortedTableMap that serve as buckets
	private UnsortedTableMap<K, V>[] table; // initialized within createTable

	public ChainHashMap() {
		super();
	}

	public ChainHashMap(int capacity) {
		super(capacity);
	}

	public ChainHashMap(int capacity, int prime) {
		super(capacity, prime);
	}

	/**
	 * Creates an empty table having length equal to current capacity.
	 */
	protected void createTable() {
		table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
	}

	/**
	 * Returns value associated with key k in bucket with hash value h, or else
	 * null.
	 */
	protected V bucketGet(int h, K k) {
		UnsortedTableMap<K, V> bucket = table[h];
		if (bucket == null) {
			return null;
		}
		return bucket.get(k);
	}

	/**
	 * Associates key k with value v in bucket with hash value h.
	 */
	protected V bucketPut(int h, K k, V v) {
		UnsortedTableMap<K, V> bucket = table[h];
		if (bucket == null) {
			bucket = table[h] = new UnsortedTableMap<>();
		}
		int oldSize = bucket.size();
		V bucketToAdd = bucket.put(k, v);
		n += (bucket.size() - oldSize); // size may have increased
		return bucketToAdd;
	}

	/**
	 * Removes entry having key key from bucket with hash value h (if any).
	 */
	protected V bucketRemove(int h, K k) {
		UnsortedTableMap<K, V> bucket = table[h];
		if (bucket == null) {
			return null;
		}
		int oldSize = bucket.size();
		V bucketToRemove = bucket.remove(k);
		n -= (bucket.size() - oldSize); // size may have decreased
		return bucketToRemove;
	}

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 */
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		for (int h = 0; h < capacity; h++) {
			if (table[h] != null) {
				for (Entry<K, V> entry : table[h].entrySet()) {
					buffer.add(entry);
				}
			}
		}
		return buffer;
	}

}
