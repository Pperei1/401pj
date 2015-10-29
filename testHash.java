import java.lang.*;
import java.util.concurrent.*;
import java.util.*;
public class testHash{

	public static void printQueue(ConcurrentLinkedDeque<pair> queue){
		Iterator<pair> it = queue.iterator();
		pair curr;
		while(it.hasNext()){
			curr = it.next();
			System.out.println(curr.getPos()+" "+curr.getNextB());
		}
	}
	
	public static void main (String[] args){
		BlockingQueue<String> queue = new BlockingQueue<String>();
		ConcurrentHashMap<String, ConcurrentLinkedDeque<pair>> map = new ConcurrentHashMap<String, ConcurrentLinkedDeque<pair>>()
		Runnable prod = new seqProd(queue,new FileReader("~/Documents/100seqs.fasta");
		Runnable cons1 = new seqCons(queue,map,15);
		Runnable cons2 = new seqCons(queue,map,15);
		Thread t1 = new Thread(prod);
		Thread t2 = new Thread(cons1);
		Thread t3 = new Thread(cons2);
		t1.start();
		t2.start();
		t3.start();
		for (Enumeration<String> e = map.keys(); e.hasMoreElements();)
       			printQueue(get(e.nextElement());	
	}
	
}
