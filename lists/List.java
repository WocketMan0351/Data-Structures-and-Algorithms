package lists;

/**
 * A simplified version of the java.util.List interface
 * 
 * @author Cody Worthen
 */
public interface List<E> {
	/**
	 * Returns the number of elements in the list
	 * 
	 * @return int
	 */
	int size();

	/**
	 * Checks whether the list is empty
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * Returns, but does not remove, the element at index i
	 * 
	 * @param i int
	 * @return int
	 * @throws IndexOutOfBoundsException
	 */
	E get(int i) throws IndexOutOfBoundsException;

	/**
	 * Replaces the elements at index i with e, and returns the replaced element
	 * 
	 * @param i       int
	 * @param element E
	 * @return E
	 * @throws IndexOutOfBoundsException
	 */
	E set(int i, E element) throws IndexOutOfBoundsException;

	/**
	 * Inserts an element e at index i, shifting all subsequent elements later in
	 * the list
	 * 
	 * @param i       int
	 * @param element E
	 * @throws IndexOutOfBoundsException
	 */
	void add(int i, E element) throws IndexOutOfBoundsException;

	/**
	 * Removes and returns the element at index i, shifting subsequent elements
	 * earlier in the list
	 * 
	 * @param i int
	 * @return int
	 * @throws IndexOutOfBoundsException
	 */
	E remove(int i) throws IndexOutOfBoundsException;
}
