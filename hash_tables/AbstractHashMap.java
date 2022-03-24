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

	public AbstractHashMap() {
		this(17); // default capacity
	}

	public AbstractHashMap(int capacity) {
		this(capacity, 109345121); // default prime
	}

	public AbstractHashMap(int capacity, int prime) {
		this.capacity = capacity;
		this.prime = prime;
		Random rand = new Random();
		scale = rand.nextInt(prime - 1) + 1;
		shift = rand.nextInt(prime);
		createTable();
	}

	public int size() {
		return n;
	}

	public V get(K k) {
		return bucketGet(hashValue(k), k);
	}

	public V remove(K k) {
		return bucketRemove(hashValue(k), k);
	}

	public V put(K k, V v) {
		V elementToAdd = bucketPut(hashValue(k), k, v);
		if (n > capacity / 2) {
			resize(2 * capacity - 1);
		}
		return elementToAdd;
	}

	// private utilities
	private int hashValue(K k) {
		return (int) ((Math.abs(k.hashCode() * scale + shift) % prime) % capacity);
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

	// protected abstract methods to be implemented by subclasses

	/**
	 * This method should create an initially empty table ahving size equal to a
	 * designated capacity instance variable.
	 */
	protected abstract void createTable();

	/**
	 * This method should mimic the semantics of the public get method, but for a
	 * key k that is known to hash to a bucket h.
	 * 
	 * @param h
	 * @param k
	 * @return
	 */
	protected abstract V bucketGet(int h, K k);

	/**
	 * This method should mimic the semantics of the public put method, but for a
	 * key k that is known to hash to bucket h.
	 * 
	 * @param h
	 * @param k
	 * @param v
	 * @return
	 */
	protected abstract V bucketPut(int h, K k, V v);

	/**
	 * This method should mimic the semantics of the public remove method, but for a
	 * key k known to hash to bucket h.
	 * 
	 * @param h
	 * @param k
	 * @return
	 */
	protected abstract V bucketRemove(int h, K k);

}
