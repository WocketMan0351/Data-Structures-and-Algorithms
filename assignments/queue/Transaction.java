package assignments.queue;

public class Transaction<A, B, C> {
	A type;
	B shares;
	C price;

	public Transaction(A type, B shares, C price) {
		this.type = type;
		this.shares = shares;
		this.price = price;
	}

	public A getType() {
		return type;
	}

	public B getShares() {
		return shares;
	}

	public C getPrice() {
		return price;
	}

	public String toString() {
		return "<" + type + ", " + shares + ", " + price + ">";
	}
}
