package game;

import java.util.ArrayList;

//import rules.GoldenFlower;
//import rules.GoldenFlower.Card;
import rules.GoldenFlowerMultiDeck;
import rules.GoldenFlowerMultiDeck.Card;
import utility.Combo;

public class DragonPhoenix {
	
	//public GoldenFlower flower;
	public GoldenFlowerMultiDeck flower;
	public ArrayList<Integer> dragon;
	public ArrayList<Integer> phoenix;
	public static int NUM_DECK = 1;
	
	public DragonPhoenix(){
		//GoldenFlower flower = new GoldenFlower();	
		GoldenFlowerMultiDeck flower = new GoldenFlowerMultiDeck(NUM_DECK);
		this.flower = flower;
		
	/*	ArrayList<Integer> dragon = flower.DrawCards();		
		ArrayList<Integer> phoenix = flower.DrawCards();		
		this.dragon = dragon;
		this.phoenix = phoenix;*/
		
		//StartNewGame();
	}
	
	public void StartNewGame(){
		ArrayList<Integer> dragon = this.flower.DrawCards();		
		ArrayList<Integer> phoenix = this.flower.DrawCards();
		// Half chance for banker to hit cards first
		if (Math.random()>0.5){
			this.dragon = dragon;
			this.phoenix = phoenix;
		}else{
			this.dragon = phoenix;
			this.phoenix = dragon;
		}
		
		//System.out.println("dragon:"+dragon);
		//System.out.println("phoenix:"+phoenix);
	}
	
	private static int TypeOdds(String cardType, int pairPoint){
		//scatter, pair, straight, flush, straightFlush, leopard, leopardKiller
		if (cardType=="leopard"){
			return 150;
		}else if (cardType=="straightFlush") {
			return 180;
		}else if (cardType=="flush") {
			return 9;
		}else if (cardType=="straight") {
			return 15;
		}else if ((cardType=="pair") && (pairPoint>8)) {
			return 3;
		}else{
			return 0; // This should never happen, as long as cardType is correct.
		}
	}
	
	private static double WinOdds(int winner){
		if (winner==1){
			return 1.95;
		}else if (winner==2){
			return 1.95;
		}else if (winner==0){
			return 1.0; // refund if tie!
		}else{
			System.out.println("The result must be one of dragon/phoenix/tie!");
			return 0.0; // this should never happen
		}
	}
	
	public double WinBet(String[] betPlayer, double[] betAmount){
		double pay = 0;
		for (int i=0; i<betPlayer.length; i++){
			//System.out.println("------------------");
			Combo result = this.flower.Compare(this.dragon, this.phoenix);
			//System.out.println(result.type+","+result.winner);
			if ((betPlayer[i]=="dragon") && ((result.winner==1) || (result.winner==0))){
				double odd = WinOdds(result.winner);
				//System.out.println("Odd:"+odd);
				pay += (odd*betAmount[i]);
			}
			
			if ((betPlayer[i]=="phoenix") && ((result.winner==2) || (result.winner==0))){
				double odd = WinOdds(result.winner);
				//System.out.println("Odd:"+odd);
				pay += (odd*betAmount[i]);
			}
		}
		return pay;
		
	}
	
	public double TypeBet(String[] betType, double[] betAmount){
		double pay = 0;
		for (int i=0; i<betType.length; i++){
			//System.out.println("------------------");
			Combo result = this.flower.Compare(this.dragon, this.phoenix);
			//System.out.println(result.type+","+result.winner);
			if (result.winner==0){
				double odd = 1;
				//System.out.println("Tie! Odd:"+odd);
				pay += (odd*betAmount[i]);
			}else if (betType[i]=="pair8") {				
				int pairPoint = 0;
				if (result.type=="pair"){
					if (result.winner==1){
						ArrayList<Card> card = this.flower.Int2Card(this.dragon);
						pairPoint = this.flower.CheckPairPoint(card);
					}else if (result.winner==2){
						ArrayList<Card> card = this.flower.Int2Card(this.phoenix);
						pairPoint = this.flower.CheckPairPoint(card);
					}	
					double odd = TypeOdds(result.type, pairPoint);
					pay += (odd*betAmount[i]);
				}else if ((result.type=="leopard") || (result.type=="straightFlush") || (result.type=="flush") || (result.type=="straight")){
					double odd = TypeOdds("pair", 9);
					pay += (odd*betAmount[i]);
				}
				//System.out.println("Odd:"+odd);
				
			}else if (betType[i]==result.type){
				int pairPoint = 0;
				double odd = TypeOdds(result.type, pairPoint);
				pay += (odd*betAmount[i]);
				
				//To display the winner's cards:
				/*
				if (result.winner==1){
					ArrayList<Card> card = this.flower.Int2Card(this.dragon);
					for(int kk=0; kk<3; kk++){
						Card c = card.get(kk);
						System.out.print(c.rank + "," + c.suit);
						System.out.print(" | ");
					}
					System.out.println();
	
				}else if (result.winner==2){
					ArrayList<Card> card = this.flower.Int2Card(this.phoenix);
					for(int kk=0; kk<3; kk++){
						Card c = card.get(kk);
						System.out.print(c.rank + "," + c.suit);
						System.out.print(" | ");
					}
					System.out.println();
					
				}else{
					System.out.println("Impossible!");
				}
				*/
				
			}
		}
		return pay;		
	}
	
/*	public double TypeBet(String[] betType, double[] betAmount){
		double pay = 0;
		for (int i=0; i<betType.length; i++){
			//System.out.println("------------------");
			Combo result = this.flower.Compare(this.dragon, this.phoenix);
			//System.out.println(result.type+","+result.winner);
			if (result.winner==0){
				double odd = 1;
				//System.out.println("Tie! Odd:"+odd);
				pay += (odd*betAmount[i]);
			}else if (betType[i]==result.type) {
				int pairPoint = 0;
				if (result.type=="pair"){
					if (result.winner==1){
						ArrayList<Card> card = this.flower.Int2Card(this.dragon);
						pairPoint = this.flower.CheckPairPoint(card);
					}else if (result.winner==2){
						ArrayList<Card> card = this.flower.Int2Card(this.phoenix);
						pairPoint = this.flower.CheckPairPoint(card);
					}					
				}
				double odd = TypeOdds(result.type, pairPoint);
				//System.out.println("Odd:"+odd);
				pay += (odd*betAmount[i]);
			}
		}
		return pay;		
	}*/
}
