import java.util.NoSuchElementException;

public class Subset {
	private RandomizedQueue<String> queue;
	public Subset (){
		queue = new RandomizedQueue<String>();
	}
	
	public static void main(String[] args) {
		Subset subset = new Subset();
		int k = Integer.parseInt(args[0]);
		while(true) {
			try {
				subset.queue.enqueue(StdIn.readString());
			} catch (NoSuchElementException e) {
				break;
			}
		}
		for (int i = 0; i < k; i ++) {
			System.out.println(subset.queue.dequeue());
		}
		
	}
}