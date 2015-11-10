import java.util.concurrent.*;
public class seqCons implements Runnable{

	private final BlockingQueue<String> queue;
	private final ConcurrentHashMap<String, Integer> map;
	private final int seqLen;
	
	public seqCons (BlockingQueue<String> queue, ConcurrentHashMap<String, Integer> map, int l){
		this.queue = queue;
		this.map = map;
		this.seqLen = l;
	}
	
	public void run(){
		try{
			String next = queue.take();
			while(next.charAt(0)!='#') {
				consume(next);
				next = queue.take();
			}
			queue.put("#");
			System.out.println("done");
		}
		catch(InterruptedException e){
		}
	}
	
	private void consume(String seq){
		int l = seq.length();
		int ind = seqLen;
		String curr = seq.substring(0,ind-1);
		addPair(this.map,curr);
		char c = seq.charAt(ind);
		ind++;
		while(ind<l){
			curr = curr.substring(1)+c;
			c = seq.charAt(ind);
			addPair(this.map,curr);
			ind++;
		}
	}
	
	private void addPair(ConcurrentHashMap<String, Integer> map, String key){
		if(map.containsKey(key)){
			map.replace(key, new Integer(map.get(key).intValue()+1));
			System.out.println(key+" "+map.get(key));
		}
		else{
			map.put(key,new Integer(1));
			System.out.println(key+" "+map.get(key));
		}
	}
}
