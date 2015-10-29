public class pair{

	private int pos;
	private char nextB;
	
	public pair(int pos, char base){
		this.pos = pos;
		this.nextB = base;
	}
	
	public int getPos(){
		return pos;
	}
	
	public char getNextB(){
		return nextB;
	}
}