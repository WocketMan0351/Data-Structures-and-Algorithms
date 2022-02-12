package trees;

public interface Position<E> {
	/**
	 * Returns the element stored at this position
	 * 
	 * @return E element
	 * @throws IllegalStateException
	 */
	E getElement() throws IllegalStateException;
}
