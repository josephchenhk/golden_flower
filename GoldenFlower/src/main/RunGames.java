package main;

import java.util.Arrays;
import java.util.HashMap;

import game.TenThousandGame;
import utility.BettingResultsOfTenThousand;

public class RunGames {
	
	private static int NUMSIM = 100000000;
	private static int NUMGAME = 1; // number of games per deck

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("炸金花：萬人場");
		int odd;
		int pay;
		int n;
		HashMap<String, Integer> count = new HashMap<String, Integer>();
		count.put("leopardKiller", 0);
		count.put("leopard", 0);
		count.put("straightFlush", 0);
		count.put("flush", 0);
		count.put("straight", 0);
		count.put("pair", 0);
		count.put("scatter", 0);
		int totalPay = 0;
		int totalRec = 0; 
		BettingResultsOfTenThousand bettingResult = new BettingResultsOfTenThousand();
		for (int i=0; i<NUMSIM; i++){
			TenThousandGame game1 = new TenThousandGame();
			String[] betPlayer = {"player1"};
			int[] betAmount = {1};
			//String[] betPlayer = {"player1", "player2", "player3", "player4"};
			//int[] betAmount = {1, 1, 1, 1};
			for (int j=0; j<betAmount.length; j++){
				totalPay += betAmount[j];
			}
			bettingResult = game1.Bet(betPlayer, betAmount, bettingResult);
			
			if (bettingResult.player1.winner==2){
				count.put(bettingResult.player1.type, count.get(bettingResult.player1.type)+1);
			}
			
			pay = 0;
			if (bettingResult.player1.winner==2){
				odd = game1.Odds(bettingResult.player1.type);
				if (Arrays.asList(betPlayer).contains("player1")){
					n = Arrays.asList(betPlayer).indexOf("player1");
					pay += (odd*betAmount[n]);
				}
			}	
			if (bettingResult.player2.winner==2){
				odd = game1.Odds(bettingResult.player2.type);
				if (Arrays.asList(betPlayer).contains("player2")){
					n = Arrays.asList(betPlayer).indexOf("player2");
					pay += (odd*betAmount[n]);
				}
			}	
			if (bettingResult.player3.winner==2){
				odd = game1.Odds(bettingResult.player3.type);
				if (Arrays.asList(betPlayer).contains("player3")){
					n = Arrays.asList(betPlayer).indexOf("player3");
					pay += (odd*betAmount[n]);
				}
			}	
			if (bettingResult.player4.winner==2){
				odd = game1.Odds(bettingResult.player4.type);
				if (Arrays.asList(betPlayer).contains("player4")){
					n = Arrays.asList(betPlayer).indexOf("player4");
					pay += (odd*betAmount[n]);
				}
			}				
		    totalRec += pay;
		    bettingResult.resetResult();
		}
		System.out.println("leopardkiller:" + count.get("leopardKiller"));
		System.out.println("leopard:" + count.get("leopard"));
		System.out.println("straightFlush:" + count.get("straightFlush"));
		System.out.println("flush:" + count.get("flush"));
		System.out.println("straight:" + count.get("straight"));
		System.out.println("pair:" + count.get("pair"));
		System.out.println("scatter:" + count.get("scatter"));
		System.out.println("-------------------------------------------");
		
		System.out.println("Total Pay: "+totalPay);
		System.out.println("Total Receive: "+totalRec);
		System.out.println("RTP: "+(totalRec*1.0/totalPay));
		
		
		/*
		long startTime = System.nanoTime();
		System.out.println("炸金花：龍鳳對決");
		double totalPay = 0;
		double totalRec = 0; 
		DragonPhoenix game2 = new DragonPhoenix();
		for (int i=0; i<NUMSIM; i++){
			if (i%NUMGAME==0) {
				//System.out.println(i);
				game2 = new DragonPhoenix();
				game2.StartNewGame();
			}else{
				game2.StartNewGame();
			}
			
			//System.out.println(game2.flower.deck.size());
			//System.out.println(game2.flower.deck);
			
			//bet player
			String[] betPlayer = {}; //{"dragon","phoenix"};
			double[] betAmount = {}; //{1};			
			for (int j=0; j<betAmount.length; j++){
				totalPay += betAmount[j];
			}		
		    double pay = game2.WinBet(betPlayer, betAmount);
		    //System.out.println("Pay *:"+pay);
		    totalRec += pay;
		    
		    
		    //bet type
		    String[] betType = {"leopard"}; //{"pair8","flush","straight","straightFlush","leopard"};
			double[] betTypeAmount = {1}; //{1};
		    for (int j=0; j<betTypeAmount.length; j++){
				totalPay += betTypeAmount[j];
			} 
		    double pay2 = game2.TypeBet(betType, betTypeAmount);
		    //System.out.println("Pay2 **:"+pay2);
		    totalRec += pay2;
		}
		System.out.println("Total Pay: "+totalPay);
		System.out.println("Total Receive: "+totalRec);
		System.out.println("RTP: "+(totalRec*1.0/totalPay));
		long estimatedTime = System.nanoTime() - startTime;
		System.out.println("Elapsed Time: "+estimatedTime/(Math.pow(10, 9))+" seconds.");
		*/
	}

}
