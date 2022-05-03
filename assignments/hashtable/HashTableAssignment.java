package assignments.hashtable;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class HashTableAssignment {

	public static void main(String[] args) {
		// sum number pairs
		Integer[] nums1 = { 10, 50, 35, 82, 13, 25 };
		int sum1 = 60;
		Integer[] nums2 = { 1, 2, 3, 4 };
		int sum2 = 20;

		System.out.println("*** Part A ***");

		System.out.println("Input 1: " + Arrays.toString(nums1) + ", " + sum1);
		System.out.print("Output 1: ");
		printPairSum(nums1, sum1);
		System.out.println();
		System.out.println();
		System.out.println("Input 2: " + Arrays.toString(nums2) + ", " + sum2);
		System.out.print("Output 2: ");
		printPairSum(nums2, sum2);
		System.out.println();

		// subset Array
		Integer[] numList1 = { 10, 50, 35, 82, 13 };
		Integer[] numList2 = { 10, 35, 13, 1000 };

		System.out.println("\n*** Part B ***");

		System.out.println("Input 1: " + Arrays.toString(nums1) + ", " + Arrays.toString(numList1));
		System.out.println("Output 1: " + isSubset(nums1, numList1));
		System.out.println();
		System.out.println("Input 2: " + Arrays.toString(nums1) + ", " + Arrays.toString(numList2));
		System.out.println("Output 2: " + isSubset(nums1, numList2));
		System.out.println();

		// itinerary
		Hashtable<String, String> flight = new Hashtable();
		flight.put("Chennai", "Banglore");
		flight.put("Bombay", "Delhi");
		flight.put("Goa", "Chennai");
		flight.put("Delhi", "Goa");

		System.out.println("\nItinerary:");
		for (Map.Entry<String, String> entry : flight.entrySet()) {
			System.out.println("   " + entry.getKey() + ":" + entry.getValue().toString());
		}

		printItinerary(flight);
	}

	/**
	 * Prints all Integer pairs in array numbers whose sum is equal to a given int
	 * called sum.
	 * 
	 * @param numbers Integer[]
	 * @param sum     int
	 */
	public static void printPairSum(Integer[] numbers, int sum) {
		Hashtable<Integer, Integer> hashTable = new Hashtable<>();
		for (int i : numbers) {
			if (hashTable.containsKey(i)) {
				System.out.print("(" + i + ", " + hashTable.get(i) + "), ");
			} else {
				hashTable.put(sum - i, i);
			}
		}
	}

	/**
	 * Checks whether miniList is a subset of masterList.
	 * 
	 * @param masterList Integer[]
	 * @param miniList   Integer[]
	 * @return True if miniList is a subset of masterList, otherwise false.
	 */
	public static boolean isSubset(Integer[] masterList, Integer[] miniList) {
		Hashtable<Integer, Integer> hashTable = new Hashtable<>();
		for (int i = 0; i < masterList.length; i++) {
			hashTable.put(masterList[i], masterList[i]);
		}
		for (int i : miniList) {
			if (!hashTable.containsKey(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Given a hash table of non-cyclical flight tickets, constructs and prints a
	 * flight itinerary for a previously unordered set of flight departures and
	 * destinations.
	 * 
	 * @param flights Hashtable
	 */
	public static void printItinerary(Hashtable<String, String> flights) {
		Hashtable<String, String> invertedFlights = new Hashtable<>();
		Set<String> keys = flights.keySet(); // get a set of the original keys

		for (String k : keys) { // check every key (there will be no duplicates because keys is a
								// set)
			if (flights.containsKey(k)) { // flights will contain every key in its own keyset
				invertedFlights.put(flights.get(k), k); // invert the key-value pair and put it in a
														// new hashtable
			}
		}

		String prev = ""; // value will be updated on a rolling basis but we don't know where to
							// start yet

		for (String k : keys) {
			if (!invertedFlights.containsKey(k)) { // start here if a key is not found in the
													// inverted hashtable
				System.out.println("\nFound starting point! " + k + "\n");
				prev = k; // grab this value and save it outside the scope of this loop
			}
		}

		System.out.println("List of flights: Starting from " + prev);

		for (String k : keys) { // lookup each element in the original hashset exactly once, order
								// doesn't //
								// matter
			System.out.println("   " + prev + ":" + flights.get(prev)); // key-value pair (we have
																		// the starting point
																		// initially)
			prev = flights.get(prev); // the preceding value becomes the next key
		}

	}

}