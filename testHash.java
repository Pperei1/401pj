import java.lang.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.*;
public class testHash{

	public static void main (String[] args) throws FileNotFoundException, InterruptedException{
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
		FileReader f = new FileReader("./100seqs.fasta");
		Runnable prod = new seqProd(queue,f);
		Runnable cons1 = new seqCons(queue,map,15);
		Runnable cons2 = new seqCons(queue,map,15);
		Thread t1 = new Thread(prod);
		Thread t2 = new Thread(cons1);
		Thread t3 = new Thread(cons2);
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		int count = 0;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
        		String key = entry.getKey().toString();
        		Integer value = entry.getValue();
        		System.out.println("key, " + key + " value " + value);
			count += value.intValue();
   		 }
		System.out.println(count);	
	}
	
}
