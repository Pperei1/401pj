public class seqCons implements Runnable{

	private final BlockingQueue<String> queue;
	private final ConcurrentHashMap<String, ConcurrentLinkedDeque<pair>> map;
	private final int seqLen;
	
	public seqCons (BlockingQueue<String> queue, ConcurrentHashMap<String, ConcurrentLinkedDeque<pair>> map, int l){
		this.queue = queue;
		this.map = map;
		this.seqLen = l;
	}
	
	public void run(){
		try{
			while(true) {consume(queue.take());}
		}
		catch{InterruptedException e){
		}
	}
	
	private void consume(String seq){
		l = seq.length();
		ind = seqLen;
		String curr = seq.substring(0,ind-1);
		count = 1;
		char c = seq.charAt(ind);
		Pair p = new pair(count,c);
		addPair(this.map,p,curr);
		ind++;
		count++;
		while(ind<l){
			curr = curr.substring(1)+c;
			c = seq.charAt(ind);
			p = new pair(count,c);
			addPair(this.map,p,curr);
			ind++;
			count++;
		}
	}
	
	private void addPair(ConcurrentHashMap<String, ConcurrentLinkedDeque<pair>> map, Pair p, String key){
		ConcurrentLinkedDeque<pair> list;
		if(map.containsKey(key)){
			map.replace(key, map.get(key).add(p));
		}
		else{
			list = new ConcurrentLinkedDeque<pair>();
			list.add(pair);
			map.put(key,list);
		}
	}
}