package maps;

import java.util.Iterator;

import priority_queues.Entry;

public abstract class AbstractMap<K, V> implements Map<K, V> {

	protected static class MapEntry<K, V> implements Entry<K, V> {
		private K key;
		private V value;

		public MapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		protected void setKey(K key) {
			this.key = key;
		}

		protected V setValue(V value) {
			V old = value;
			this.value = value;
			return old;
		}
	}

	// Support for public keySet() method...
	private class KeyIterator implements Iterator<K> {
		private Iterator<Entry<K, V>> entries = entrySet().iterator();

		public boolean hasNext() {
			return entries.hasNext();
		}

		public K next() {
			return entries.next().getKey();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class KeyIterable implements Iterable<K> {
		public Iterator<K> iterator() {
			return new KeyIterator();
		}
	}

	public Iterable<K> keySet() {
		return new KeyIterable();
	}

	public boolean containsKey(K key) {
		Iterable<K> keySet = new KeyIterable();
		for (K k : keySet) {
			if (k.equals(key)) {
				return true;
			}
		}
		return false;
	}

	// Support for public values() method...
	private class ValueIterator implements Iterator<V> {
		private Iterator<Entry<K, V>> entries = entrySet().iterator();

		public boolean hasNext() {
			return entries.hasNext();
		}

		public V next() {
			return entries.next().getValue();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class ValueIterable implements Iterable<V> {
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
	}

	public Iterable<V> values() {
		return new ValueIterable();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

}
