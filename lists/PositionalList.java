package lists;

public interface PositionalList<E> {
	/**
	 * Returns the number of elements in the list
	 * 
	 * @return int
	 */
	int size();

	/**
	 * Tests whether the list is empty
	 * 
	 * @return boolean
	 */
	boolean isEmpty();

	/**
	 * Returns the first Position in the list (null if empty)
	 * 
	 * @return Position<E>
	 */
	Position<E> first();

	/**
	 * Retunrs the last Position in the list (null if empty)
	 * 
	 * @return Position<E>
	 */
	Position<E> last();

	/**
	 * Returns the Position immediately before Position p (null if empty)
	 * 
	 * @param p Position<E>
	 * @return Position<E>
	 * @throws IllegalArgumentException
	 */
	Position<E> before(Position<E> p) throws IllegalArgumentException;

	/**
	 * Returns the Position immediately after Position p (null if empty)
	 * 
	 * @param p Position<E>
	 * @return Position<E>
	 * @throws IllegalArgumentException
	 */
	Position<E> after(Position<E> p) throws IllegalStateException;

	/**
	 * Inserts element e at the front of the list and returns its new Position
	 * 
	 * @param e
	 * @return Position<E>
	 */
	Position<E> addFirst(E e);

	/**
	 * Inserts element e at the back of the list and returns its new Position
	 * 
	 * @param e
	 * @return Position<E>
	 */
	Position<E> addLast(E e);

	/**
	 * Inserts element e immediately before Position p and returns its new position
	 * 
	 * @param p Position<E>
	 * @param e E
	 * @return Position<E>
	 */
	Position<E> addBefore(Position<E> p, E e);

	/**
	 * 
	 * @param p Position<E>
	 * @param e E
	 * @return Position<E>
	 */
	Position<E> addAfter(Position<E> p, E e);

	/**
	 * Replaces the element stored at Position p and returns the replace element
	 * 
	 * @param p Position<E>
	 * @param e E
	 * @return Position<E>
	 * @throws IllegalStateException
	 */
	E set(Position<E> p, E e) throws IllegalStateException;

	/**
	 * Removes the element stored at Position p and returns it (invalidating it)
	 * 
	 * @param p Position<E>
	 * @return E element
	 * @throws IllegalStateException
	 */
	E remove(Position<E> p) throws IllegalStateException;
}
