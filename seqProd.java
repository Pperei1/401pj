import java.util.concurrent.*;
import java.io.*;
import java.lang.*;
public class seqProd implements Runnable {

	private final BlockingQueue<String> queue;
	private final FileReader fastaReader;

	public seqProd (BlockingQueue<String> queue, FileReader reader){
		this.queue = queue;
		this.fastaReader = reader;
	}
	
	public void run(){
		try{
			BufferedReader br = new BufferedReader(fastaReader);
			String seq = br.readLine();
			while((seq = br.readLine()) != null){
				queue.put(seq);
				seq = br.readLine();}
			queue.put("#");
		} catch (InterruptedException|IOException e){
		
		}
	}
}
