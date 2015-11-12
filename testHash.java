import java.lang.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.*;
public class testHash{
	public static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortByValue(Map<K, V> map) {
    		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
    		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
        		public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            			return (o1.getValue()).compareTo(o2.getValue());
        		}
    		});

   		LinkedHashMap<K, V> result = new LinkedHashMap<K, V>();
    		for (Map.Entry<K, V> entry : list) {
        		result.put(entry.getKey(), entry.getValue());
    		}
    		return result;
	}

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
		LinkedHashMap<String,Integer> map2 = sortByValue(map);
		int count = 0;
		for (Map.Entry<String, Integer> entry : map2.entrySet()) {
        		String key = entry.getKey().toString();
        		Integer value = entry.getValue();
        		System.out.println("key: " + key + " value: " + value);
			count += value.intValue();
   		 }
		System.out.println(count);	
	}
	
}
