package lambdas;

public class LambdaDemo {

	public static void main(String[] args) {
		Foo foo = () -> {
			System.out.println("Hello World!");
		};

		Add add = (int a, int b) -> {
			return a + b;
		};

		foo.foo();
		System.out.println(add.add(2, 2));
	}

}
