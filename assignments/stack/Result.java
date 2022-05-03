package assignments.stack;

public class Result<A, B> {
	A first;
	B second;

	public Result(A first, B second) {
		this.first = first;
		this.second = second;
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	public String toString() {
		return "<" + first + ", " + second + ">";
	}
}
