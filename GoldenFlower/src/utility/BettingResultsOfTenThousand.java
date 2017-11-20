package utility;

public class BettingResultsOfTenThousand {

    public Combo player1;
    public Combo player2;
    public Combo player3;
    public Combo player4;
    public Combo defaultCombo = new Combo();
      
    public BettingResultsOfTenThousand(){
    	resetResult();
    }
    
    public void AttachResult(String player, Combo combo){
    	if (player=="player1"){
    		player1 = combo;
    	}
    	if (player=="player2"){
    		player2 = combo;
    	}
    	if (player=="player3"){
    		player3 = combo;
    	}
    	if (player=="player4"){
    		player4 = combo;
    	}
    	
    }
    
    public void resetResult(){
    	player1 = defaultCombo;
    	player2 = defaultCombo;
        player3 = defaultCombo;
        player4 = defaultCombo;
    }

}
