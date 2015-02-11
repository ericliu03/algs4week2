import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private int N;
	private Item[] itemArray;
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		N = 0;
		itemArray = (Item[]) new Object[2];
	} 

	// is the queue empty?
	public boolean isEmpty() {
		return N == 0;
	} 

	// return the number of items on the queue
	public int size() {
		return N;
	} 

	private void resize(int capacity) {
		assert capacity >= N;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			temp[i] = itemArray[i];
		}
		itemArray = temp;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) throw new NullPointerException();
		if(N == itemArray.length) resize(N * 2);
		itemArray[N++] = item;
	} 

	// delete and return a random item
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException("queue is empty");
		int randIndex = StdRandom.uniform(N);
		Item temp = itemArray[randIndex];
		itemArray[randIndex] = itemArray[N - 1];
		itemArray[N - 1] = null;
		N--;
		if (N > 0 && N == itemArray.length/4) resize(itemArray.length/2);
		return temp;
	} 

	// return (but do not delete) a random item
	public Item sample() {
		if (isEmpty()) throw new NoSuchElementException("queue is empty");
		int randIndex = StdRandom.uniform(N);
		return itemArray[randIndex];
	} 
	
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {return new RandomizedQueueIterator();}
	private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
		private Item[] iterArray = (Item[]) new Object[N];

        public RandomizedQueueIterator() { 
        	i = N;
    		for (int index = 0; index < N; index++) {
    			iterArray[index] = itemArray[index];
    		}
    		StdRandom.shuffle(iterArray);
        }
        public boolean hasNext() { return i > 0; }
        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return iterArray[--i];
        }
	}

	// unit testing
	public static void main(String[] args) {
		RandomizedQueue<Integer> testDeque = new RandomizedQueue<Integer>();
		for (int i = 0; i < 10; i++){
			testDeque.enqueue(i);
		}
		for (int i = 0; i < 11; i++){
			int x = testDeque.dequeue();
			System.out.println(x);
			System.out.println(testDeque.size());
		}
	} 
}