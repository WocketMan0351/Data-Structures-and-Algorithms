package assignments.queue;

import java.util.LinkedList;
import java.util.Queue;

public class CapitalGain {

	public static void main(String[] args) {
		String test1 = "buy 100 share(s) at $20 each;buy 20 share(s) at $24 each;buy 200 share(s) at $36 each;sell 150 share(s) at $30 each;buy 50 share(s) at $25 each;sell 200 share(s) at $35 each;";
		String test2 = "buy 100 share(s) at $20 each;buy 20 share(s) at $24 each;buy 200 share(s) at $36 each;sell 150 share(s) at $30 each;buy 50 share(s) at $25 each;sell 2000 share(s) at $35 each;";
		String test3 = "buy 10 shares at $100 each;sell 100 shares at $101 each;";

		System.out.println("Test Case 1:");
		Queue<Transaction> orders1 = loadOrderQueue(test1);
		double capitalGain1 = calculateCapitalGain(orders1);
		System.out.println("Capital Gain: $" + capitalGain1 + "\n");

		System.out.println("Test Case 2:");
		Queue<Transaction> orders2 = loadOrderQueue(test2);
		double capitalGain2 = calculateCapitalGain(orders2);
		System.out.println("Capital Gain: $" + capitalGain2 + "\n");

		System.out.println("Test Case 3:");
		Queue<Transaction> orders3 = loadOrderQueue(test3);
		double capitalGain3 = calculateCapitalGain(orders3);
		System.out.println("Capital Gain: $" + capitalGain3 + "\n");
	}

	/**
	 * Parses a String of transaction data in a specified format: [Buy/Sell] [# of
	 * shares] at [$USD] each; Example input: "buy 100 share(s) at $20 each;"
	 * Returns a LinkedList implementation of a java.util.Queue of type
	 * Transaction[String (type), int (shares), double (price)].
	 * 
	 * @param input String
	 * @return Queue<Transaction>
	 */
	public static Queue<Transaction> loadOrderQueue(String input) {
		Queue<Transaction> queue = new LinkedList<>();
		String[] transactionStrings = input.split(";");

		for (int i = 0; i < transactionStrings.length; i++) {
			String[] transaction = transactionStrings[i].split(" ");

			String type = transaction[0];
			int shares = Integer.parseInt(transaction[1]);
			double price = Double.parseDouble(transaction[4].substring(1));

			queue.add(new Transaction<>(type, shares, price));
		}

		return queue;
	}

	/**
	 * Calculates the capital gain from a Queue of type Transaction[String (type),
	 * int (shares), double (price)].
	 * 
	 * @param queue Queue
	 * @return double
	 */
	public static double calculateCapitalGain(Queue<Transaction> queue) {
		int currentHoldings = 0;
		int totalShares = 0;
		double totalCostBasis = 0.00;
		double totalProceeds = 0.00;

		while (!queue.isEmpty()) {
			Transaction<String, Integer, Double> transaction = queue.remove();
			String type = transaction.getType().toString();

			switch (type) {
			case "buy":
				currentHoldings += transaction.getShares();
				totalCostBasis += (transaction.getShares() * transaction.getPrice());
				System.out.println("Bought " + transaction.getShares() + " shares" + " at $"
						+ transaction.getPrice()
						+ ". Current Holdings: " + currentHoldings + " shares.");
				break;
			case "sell":
				if (transaction.getShares() <= currentHoldings) {
					currentHoldings -= transaction.getShares();
					totalProceeds += transaction.getShares() * transaction.getPrice();
					System.out.println(
							"Requested Sell Quantity: " + transaction.getShares() + " shares. Sold "
									+ transaction.getShares() + " shares at $"
									+ transaction.getPrice() + ". Current Holdings: "
									+ currentHoldings + " shares.");
				} else if (transaction.getShares() > currentHoldings) {
					totalProceeds += currentHoldings * transaction.getPrice();
					System.out.println(
							"Requested Sell Quantity: " + transaction.getShares() + " shares. Sold "
									+ currentHoldings
									+ " shares at $" + transaction.getPrice()
									+ ". Current Holdings: 0 shares");
					currentHoldings = 0;
				}
				break;
			default:
				break;
			}
		}

		return totalProceeds - totalCostBasis;
	}

}
