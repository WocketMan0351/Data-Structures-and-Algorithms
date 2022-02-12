package trees;

public abstract class AbstractTree<E> implements Tree<E> {

	public int numChildren(Position<E> p) throws IllegalArgumentException {
		return 0;
	}

	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) > 0;
	}

	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) == 0;
	}

	public boolean isRoot(Position<E> p) throws IllegalArgumentException {
		return p == root();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int depth(Position<E> p) {
		if (isRoot(p)) {
			return 0;
		} else {
			return 1 + depth(parent(p));
		}
	}

	// O(n^2)
	private int heightBad() {
		int height = 0;
		for (Position<E> p : positions()) {
			if (isExternal(p)) {
				height = Math.max(height, depth(p));
			}
		}
		return height;
	}

	// O(n)
	public int height(Position<E> p) {
		int height = 0; // if p is external
		for (Position<E> child : children(p)) {
			height = Math.max(height, 1 + height(child));
		}
		return height;
	}

}
