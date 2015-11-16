import java.io.*;
public class countline{
	public static void main(String[]args) throws IOException {
		int count = 0;
		BufferedReader br = null;
		String seq = null;
		try{
			br = new BufferedReader(new FileReader("test.csv"));
			while((seq = br.readLine()) != null){
				count = count + 1;
				if(count%10000000 == 0){
					System.out.println(count);
				}
			}
		}
		catch(IOException ex){
		} finally {
		br.close();
		System.out.println(count);
		}
	}
}
