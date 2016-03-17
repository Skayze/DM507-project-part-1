/* 			DM507
 * 			Group members:
 * 			Christian Bjørn Moeslund (chmoe13@student.sdu.dk)
 * 			Alexander Hvilshøj (alhvi15@student.sdu.dk)
 * 
 */



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Heapsort {

	public static void main(String[] args) {
		//Initializing Scanner with standard input
		Scanner sc = new Scanner(System.in);
		//Initializing array, for holding input initially
		List<Integer> initArray = new ArrayList<>();
		// Getting input
		while (sc.hasNext()) {
			initArray.add(sc.nextInt());
		}
		sc.close();
		
		
		// Initializing Priority Queue with size based on input-elements
		PQHeap pq = new PQHeap(initArray.size());
		// Inserting elements into the PQ from the initial array:
		for (int i = 0; i < initArray.size(); i++) {
			Element e = new Element(initArray.get(i), null);
			pq.insert(e);
		}
		// min-Heapify, starting with the root:
		pq.minHeapify(0);

		// Extracting all elements, one by one, with the element with the lowest
		// priority first:
		System.out.println("\n--- OUTPUT ---");
		for (int i = 0; i < initArray.size(); i++) {
			int key = pq.extractMin().key;
			System.out.println(key);
		}

	}
}
