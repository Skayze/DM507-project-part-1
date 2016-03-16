
abstract interface PQ {
	public Element extractMin();

	public void insert(Element e);

	public void minHeapify(int i);
}

final class Element {

	public int key;
	public Object data;

	public Element(int key, Object o) {
		this.key = key;
		this.data = o;
	}

}

public class PQHeap implements PQ {

	public PQHeap(int maxElms) {

		array = new Element[maxElms];
		ArraySize = maxElms;

	}

	private static Element[] array;
	static int ArraySize;
	//Skriv hvorfor i+1, og hvad funktionen laver
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

		int smallest = i;
		// if i = 3, this is the 4th index, and as such, the Left and Right
		// nodes will be the 8th and 9th indices = 7 and 8
		int left = Left(i); // SMallest, left and right are indices
		int right = Right(i);

		if (left < ArraySize) {
			if (array[left].key < array[i].key) {

				smallest = left;
				// Swapping elements
				Element temp = array[i];
				array[i] = array[smallest];
				array[smallest] = temp;

			}
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
		// Find first empty index
		while (array[i] != null) {
			i++;
		}
		array[i] = e;
		while (i > 0 && array[Parent(i)].key > array[i].key) {
			// Swapping array[i] with its parent:
			Element temp = array[i];
			array[i] = array[Parent(i)];
			array[Parent(i)] = temp;
			i = Parent(i);
		}

	}
	
	

	@Override
	public Element extractMin() {
		ArraySize--;

		Element min = array[0];
		Element[] temp = new Element[(ArraySize)];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = array[i + 1];
		}

		array = temp;

		if (ArraySize > 0) {
			minHeapify(0);

		}
		return min;

	}

}