/* 			DM507
 * 			Group members:
 * 			Christian Bjørn Moeslund (chmoe13@student.sdu.dk)
 * 			Alexander Hvilshøj (alhvi15@student.sdu.dk)
 * 
 */
abstract interface PQ {
	public Element extractMin();

	public void insert(Element e);

	public void minHeapify(int i);
}
final class Element {

	public int key;
	public Object data;

	//Constructor
	public Element(int key, Object o) {
		this.key = key;
		this.data = o;
	}

}

public class PQHeap implements PQ {

	private static Element[] array;
	static int ArraySize;
	
	public PQHeap(int maxElms) {

		array = new Element[maxElms];
		ArraySize = maxElms;

	}

	// Using i+1 to make actual code more similar to pseudocode,
	// and -1 at the end to utilize index 0 of arrays

	public int Parent(int i) {
		
		return Math.floorDiv((i + 1), 2) - 1;
	}

	public int Left(int i) {
		return ((i + 1) * 2) - 1;
	}

	public int Right(int i) {
		return ((i + 1) * 2);
	}

	public void minHeapify(int i) {

		// Smallest, left, and right are indices
		int smallest = i;
		// if i = 3, this is the 4th index, and as such, the Left and Right
		// nodes will be the 8th and 9th indices = 7 and 8
		int left = Left(i);
		int right = Right(i);

		// Left and right have the same code, but with "left" replaced by
		// "right"
		if (left < ArraySize) {
			// In a min-heap, the parent must be smaller than the children
			// If a child is smaller than its parent, these two elements are
			// swapped
			if (array[left].key < array[i].key) {

				smallest = left;
				// Swapping elements
				Element temp = array[i];
				array[i] = array[smallest];
				array[smallest] = temp;

			}
			// minHeapify is called on the child, to make sure heap order is
			// maintained throughout the heap.
			minHeapify(left);
		}
		if (right < ArraySize) {
			if (array[right].key < array[i].key) {

				smallest = right;
				// Swapping elements:
				Element temp = array[i];
				array[i] = array[smallest];
				array[smallest] = temp;

			}
			minHeapify(right);

		}

	}

	// Print out the heap with System.out.println(PQ)
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Element e : array) {
			s.append(e.key + " ");
		}

		return s.toString();
	}

	@Override
	public void insert(Element e) {
		int i = 0;
		// Find first empty place in the array
		while (array[i] != null) {
			i++;
		}
		// Inserting the element into the first available empty place in the
		// array
		array[i] = e;
		// if i is 0, there is no point in swapping elements (only one element)
		while (i > 0 && array[Parent(i)].key > array[i].key) {
			// Swapping array[i] with its parent if array[i] is smaller than its
			// parent, using a temporary variable
			Element temp = array[i];
			array[i] = array[Parent(i)];
			array[Parent(i)] = temp;
			i = Parent(i);
		}

	}

	@Override
	public Element extractMin() {
		// Returns the smallest element in the heap and deletes it, making sure
		// the array is still heap ordered
		ArraySize--;

		//Assuming heap-order is maintained, 
		//the first element will always be the smallest.
		Element min = array[0]; 
		//New array with elements from old array, 
		//except one (the extracted element)
		Element[] temp = new Element[(ArraySize)];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = array[i + 1];
		}

		array = temp;

		if (ArraySize > 1) {
			minHeapify(0);

		}
		return min;

	}

}