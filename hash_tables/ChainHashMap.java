package hash_tables;

import maps.UnsortedTableMap;

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

}
