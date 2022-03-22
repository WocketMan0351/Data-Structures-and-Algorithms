package hash_tables;

import java.util.ArrayList;
import java.util.Random;

import maps.AbstractMap;
import priority_queues.Entry;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {
	protected int n = 0; // number of entries in the dictionary
	protected int capacity; // length of the table
	private int prime; // prime factor
	private long scale, shift; // the shift and scaling factors

	public AbstractHashMap(int capacity, int prime) {
		this.capacity = capacity;
		this.prime = prime;
		Random rand = new Random();
		scale = rand.nextInt(prime - 1) + 1;
		shift = rand.nextInt(prime);
		createTable();
	}

	public AbstractHashMap(int capacity) {
		this(capacity, 109345121); // default prime
	}

	public AbstractHashMap() {
		this(17); // default capacity
	}

	public int size() {
		return n;
	}

	public V get(K key) {
		return bucketGet(hashValue(key), key);
	}

	public V remove(K key) {
		return bucketRemove(hashValue(key), key);
	}

	public V put(K key, V value) {
		V elementToAdd = bucketPut(hashValue(key), key, value);
		if (n > capacity / 2) {
			resize(2 * capacity - 1);
		}
		return elementToAdd;
	}

	// private utilities
	private int hashValue(K key) {
		return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
	}

	private void resize(int newCapacity) {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>(n);
		for (Entry<K, V> e : entrySet()) {
			buffer.add(e);
		}
		capacity = newCapacity;
		createTable(); // based on updated capacity
		n = 0; // will be recomputed while reinserting entries
		for (Entry<K, V> e : buffer) {
			put(e.getKey(), e.getValue());
		}
	}

	// protected absract methods to be implemented by subclasses
	protected abstract void createTable();

	protected abstract V bucketGet(int hash, K key);

	protected abstract V bucketPut(int hash, K key, V value);

	protected abstract V bucketRemove(int hash, K key);
}
