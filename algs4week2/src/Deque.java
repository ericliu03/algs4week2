

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private int N;
	private Node first;
	private Node last;
	
	private class Node {
		Item item;
		Node next;
		Node previous;
		
		public Node(Item item) {
			this.item = item;
		}
	}
	
	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		N = 0;
	}            
	
	// is the deque empty?
	public boolean isEmpty() {
		return N == 0;
	}                
	
	// return the number of items on the deque
	public int size() {
		return N;
	}                
	
	// insert the item at the front
	public void addFirst(Item item) {
		if (item == null) throw new NullPointerException();
		Node newOne = new Node(item);
		
		if(isEmpty()) {
			first = last = newOne;
//			if (N == 1) last = first;
		} else {
			newOne.next = first;
			first.previous = newOne;
			first = newOne;
		}
		N++;
	}         
	
	// insert the item at the end
	public void addLast(Item item) {
		if (item == null) throw new NullPointerException();
		Node newOne = new Node(item);
		
		if(isEmpty()) {
			last = first = newOne;
//			if (N == 1) last = first;
		} else {
			newOne.previous = last;
			last.next = newOne;
			last = newOne;
		}
		N++;
	}          
	
	// delete and return the item at the front
	public Item removeFirst() {
		Item temp = null;
		if (isEmpty()) throw new NoSuchElementException();

		else if (size() == 1) {
			temp = first.item;
			first = last = null;
		} else {
			temp = first.item;
			first = first.next;
			first.previous = null;

		}
		N--;
		return temp;
	}    
	
	// delete and return the item at the end
	public Item removeLast() {

		Item temp = null;
		if (isEmpty()) throw new NoSuchElementException();
		else if (size() == 1) {
			temp = last.item;
			first = last = null;
		} else {
			temp = last.item;
			last = last.previous;
			last.next = null;
		}
		N--;
		return temp;
}           
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator()  { return new DequeIterator(); }
	private class DequeIterator implements Iterator<Item> {
		Node current = first;
		public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException();  }
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}       
	
	// unit testing
	public static void main(String[] args) {
		Deque<Integer> testDeque = new Deque<Integer>();
		for (int i = 0; i < 10; i++){
			testDeque.addFirst(i);
		}
		for (int i = 0; i < 5; i++){
			int x = testDeque.removeLast();

			int y = testDeque.removeFirst();
			System.out.println(x);
			System.out.println(y);
			System.out.println("|||||");
			System.out.println(testDeque.size());
			System.out.println("|||||");
		}
		testDeque.removeFirst();
	} 




}