package assignments.stack;

public class Main {

	public static void main(String[] args) {
		String str1 = "14 <= 4 - 3 * 2 + 18";
		String input = str1;
		System.out.println("\nInput: " + input);
		Result<?, ?> result = evaluateExpression(input);
		System.out.println("Output: " + result.toString());

		String str2 = "4 - 3 * 2 + 7";
		String input2 = str2;
		System.out.println("\nInput: " + input2);
		Result<?, ?> result2 = evaluateExpression(input2);
		System.out.println("Output: " + result2.toString());

		String str3 = "3 != 5";
		String input3 = str3;
		System.out.println("\nInput: " + input3);
		Result<?, ?> result3 = evaluateExpression(input3);
		System.out.println("Output: " + result3.toString());

		String str4 = "10 >= 50 / 2";
		String input4 = str4;
		System.out.println("\nInput: " + input4);
		Result<?, ?> result4 = evaluateExpression(input4);
		System.out.println("Output: " + result4.toString());

		String str5 = "10 * 10 - 10 == 45 * 2";
		String input5 = str5;
		System.out.println("\nInput: " + input5);
		Result<?, ?> result5 = evaluateExpression(input5);
		System.out.println("Output: " + result5.toString());
	}

	public static Result<?, ?> evaluateExpression(String expression) {
		expression += " ";
		char[] tokens = expression.toCharArray();

		ArrayStack<Integer> valStack = new ArrayStack<>();
		ArrayStack<Character> opStack = new ArrayStack<>();

		// while another token z
		for (int i = 0; i < tokens.length; i++) {
			char z = tokens[i];

			StringBuilder sb = new StringBuilder();
			sb.append(z);

			if (z == ' ') {
				continue;
			} else if (Character.isDigit(z)) {
				if (Character.isDigit(tokens[i + 1])) {
					sb.append(tokens[++i]);
					valStack.push(Integer.parseInt(sb.toString()));
				} else {
					valStack.push(Integer.parseInt(sb.toString()));
				}
			} else {
				// repeatOps(z)
				while (valStack.size() > 1 && precedence(z) <= precedence(opStack.top())) {
					// doOp()
					int x = valStack.pop();
					int y = valStack.pop();
					char op = opStack.pop();
					valStack.push((int) operation(y, op, x));
				}
				opStack.push(z);
			}
		}

		Object result = new Object();

		// repeatOps(z)
		while (valStack.size() > 1 && precedence('$') <= precedence(opStack.top())) {
			int x = valStack.pop();
			int y = valStack.pop();
			char op = opStack.pop();

			if (operation(y, op, x) instanceof Boolean) {
				if (op == '=') {
					char op2 = opStack.pop();
					String operator = String.valueOf(op2) + String.valueOf(op);
					result = operation(y, operator, x);
				} else {
					result = operation(y, op, x);
				}
			} else if (operation(y, op, x) instanceof Integer) {
				valStack.push((int) operation(y, op, x));
				result = valStack.top();
			}
		}

		return new Result<>(((Object) result).getClass().getSimpleName(), result);
	}

	public static Object operation(int a, char op, int b) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		default:
			return false; // makes operation(int a, char op, int b) instanceof Boolean, which calls
							// its
							// override
		}
	}

	public static Object operation(int a, String op, int b) {
		switch (op) {
		case "<=":
			return a <= b;
		case ">=":
			return a >= b;
		case "!=":
			return a != b;
		case "==":
			return a == b;
		default:
			return null;
		}
	}

	public static int precedence(char op) {
		switch (op) {
		case '*':
			return 3;
		case '/':
			return 3;
		case '+':
			return 2;
		case '-':
			return 2;
		case '<':
			return 1;
		case '>':
			return 1;
		case '!':
			return 1;
		case '=':
			return 1;
		case '$':
			return 0;
		default:
			return 0;
		}
	}

}
