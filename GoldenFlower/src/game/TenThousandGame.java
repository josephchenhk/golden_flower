package game;

import java.util.ArrayList;

import rules.GoldenFlower;
import utility.BettingResultsOfTenThousand;
import utility.Combo;



public class TenThousandGame {
	
	public ArrayList<Integer> deck;
	public GoldenFlower flower;
	public ArrayList<Integer> banker;	
	public ArrayList<Integer> player1;
	public ArrayList<Integer> player2;
	public ArrayList<Integer> player3;		
	public ArrayList<Integer> player4;
	

	public TenThousandGame(){
		GoldenFlower flower = new GoldenFlower();
		
		ArrayList<Integer> banker = flower.DrawCards();		
		ArrayList<Integer> player1 = flower.DrawCards();		
		ArrayList<Integer> player2 = flower.DrawCards();		
		ArrayList<Integer> player3 = flower.DrawCards();		
		ArrayList<Integer> player4 = flower.DrawCards();
		/*
		ArrayList<Integer> banker = flower.BankerDrawCards();		
		ArrayList<Integer> player1 = flower.Player1DrawCards();		
		ArrayList<Integer> player2 = flower.Player2DrawCards();		
		ArrayList<Integer> player3 = flower.Player3DrawCards();		
		ArrayList<Integer> player4 = flower.Player4DrawCards();
		*/
		
		this.flower = flower;
		this.banker = banker;
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
		this.player4 = player4;
	}
	
	public int Odds(String cardType){
		//scatter, pair, straight, flush, straightFlush, leopard, leopardKiller
		if ((cardType=="leopardKiller") || (cardType=="leopard")){
			return 9;
		}else if (cardType=="straightFlush") {
			return 7;
		}else if (cardType=="flush") {
			return 5;
		}else if (cardType=="straight") {
			return 4;
		}else if (cardType=="pair") {
			return 3;
		}else if (cardType=="scatter") {
			return 2;
		}else{
			return 0; // This should never happen, as long as cardType is correct.
		}
	}
	
	public BettingResultsOfTenThousand Bet(String[] betPlayer, int[] betAmount, BettingResultsOfTenThousand bettingResult){
		
		//BettingResultsOfTenThousand bettingResult = new BettingResultsOfTenThousand();
		for (int i=0; i<betPlayer.length; i++){
			
			if (betPlayer[i]=="player1"){
				Combo result = this.flower.Compare(banker, player1);
				bettingResult.AttachResult("player1", result);
				
			}
			
			if (betPlayer[i]=="player2"){
				Combo result = this.flower.Compare(banker, player2);
				bettingResult.AttachResult("player2", result);
			}
			
			if (betPlayer[i]=="player3"){
				Combo result = this.flower.Compare(banker, player3);
				bettingResult.AttachResult("player3", result);
			}
			
			if (betPlayer[i]=="player4"){
				Combo result = this.flower.Compare(banker, player4);
				bettingResult.AttachResult("player4", result);
			}
		}
		return bettingResult;
	}
	
	/*
	public int Bet(String[] betPlayer, int[] betAmount){
		
		int pay = 0;
		for (int i=0; i<betPlayer.length; i++){
			if (betPlayer[i]=="player1"){
				//System.out.println("------------------");
				Combo result = this.flower.Compare(banker, player1);
				//System.out.println(result.type+","+result.winner);
				if (result.winner==2){
					int odd = Odds(result.type);
					//System.out.println("Odd:"+odd);
					pay += (odd*betAmount[i]);
				}				
			}
			
			if (betPlayer[i]=="player2"){
				//System.out.println("------------------");
				Combo result = this.flower.Compare(banker, player2);
				//System.out.println(result.type+","+result.winner);
				if (result.winner==2){
					int odd = Odds(result.type);
					//System.out.println("Odd:"+odd);
					pay += (odd*betAmount[i]);
				}
			}
			
			if (betPlayer[i]=="player3"){
				//System.out.println("------------------");
				Combo result = this.flower.Compare(banker, player3);
				//System.out.println(result.type+","+result.winner);
				if (result.winner==2){
					int odd = Odds(result.type);
					//System.out.println("Odd:"+odd);
					pay += (odd*betAmount[i]);
				}
			}
			
			if (betPlayer[i]=="player4"){
				//System.out.println("------------------");
				Combo result = this.flower.Compare(banker, player4);
				//System.out.println(result.type+","+result.winner);
				if (result.winner==2){
					int odd = Odds(result.type);
					//System.out.println("Odd:"+odd);
					pay += (odd*betAmount[i]);
				}
			}
		}
		return pay;
	}
	*/
}

