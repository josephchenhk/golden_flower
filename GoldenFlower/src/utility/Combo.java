package utility;

public class Combo {
	public String type; // scatter, pair, straight, flush, straightFlush, leopard, leopardKiller
    public int winner; // 1 means banker wins; 2 means player wins
    
    public Combo(){
    	setType(null);
    	setWinner(0);
    }
    
    public void setType(String toType){
    	type = toType;
    }
    
    public void setWinner(int toWinner){
    	winner = toWinner;
    }
}
