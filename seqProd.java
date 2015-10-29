import java.util.concurrent.*;
import java.io.*;
public class seqProd implements Runnable {

	private final BlockingQueue<String> queue;
	private final FileReader fastaReader;
	public seqProd (BlockingQueue<String> queue, FileReader reader){
		this.queue = queue;
		this.fastaReader = reader;
	}
	
	public void run(){
		try{
			try (BufferedReader br = new BufferedReader(fastaReader)){
				String seq;
				seq = br.readline();
				while((seq = br.readLine()) != null){
					queue.put(seq);
					seq = br.readline();
				}
		}
		catch (InterruptedException e){
		}
	}
}
