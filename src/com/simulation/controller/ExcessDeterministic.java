package com.simulation.controller;

import java.util.Random;

public class ExcessDeterministic{
public static void main(String[] args) {
	
	
	//Initialization parameters
	int x = 0;					//Number of request seen in a window (s_x)
	int numGood = 0;			//Truly Good
	int numBad = 0;				//Truly Bad
	int totalGoodBad = 1000000;	//Total population
	int classGood = 0;			//Classified good request
	int classBad = 0;			//Classified good request
	float e = (float) 0.1;      //Epsilon of ML black-box
	Random rand = new Random(); //Random number generator
	float excess = 0; 			//Excess = ClassGood - e*x
	double total = 0; 			//Window ending criteria total = 9*(Math.sqrt(x*Math.log(y)))
	float goodCost = 0;			//Good Cost
	float badCost = 0;			//Bad Cost
	int window = 0;
	
	for(int ratio = 2; ratio<12;ratio++) {
	
		for(int y = 1; y<totalGoodBad;y++) {
			//Incoming new request, increment x by 1
			x+=1;
			boolean isGood = true;
			//Classifier
			int verdictGroundTruth = rand.nextInt(ratio)+1;
			//If probability = 1, then good request. If probability = 2, then bad request.
			if(verdictGroundTruth==1) {
				numGood++;
				isGood = true;
			}
			else {
				isGood = false;
				numBad++;
			}
			int verdictClassifier = rand.nextInt((int) (10))+1;
			if(isGood && verdictClassifier!=1) {
				classGood++;

			}
			else if(!isGood && verdictClassifier!=1){
				classBad++;
			}
			else if(isGood && verdictClassifier==1){
				classBad++;

			}
			else if(!isGood && verdictClassifier==1){
				classGood++;
			
			}
			excess = classGood - (e*x);
			total = 5*Math.sqrt(x*Math.log(y));
		
			//Check if window ends
			if(excess>total) {		
				for(int i=1;i<=(int) (x-total);i++) {
					badCost+=Math.sqrt(i);
				}
				for(int i=(int) (x-total);i<=x;i++) {
					goodCost+=Math.sqrt(i);
					goodCost+=i;
				}
				goodCost=goodCost + x;
				//Reset the parameters for next window
				x = 0; 
				classGood = 0;
				classBad = 0;
				excess = 0;
				total = 0;
			}
		
		}
		
		if(excess<total) {
			//System.out.println("#### Last Window Open ####");
			for(int i=1;i<(int) (x-total);i++) {
				badCost+=Math.sqrt(i);
			}
	
			for(int i=(int) (x-total);i<=x;i++) {
				goodCost+=Math.sqrt(i);
			}
			//goodCost = goodCost+x;
		}
		System.out.println(goodCost+" "+(badCost*numGood));
		x = 0;
		excess = 0;
		total = 0;
		goodCost = 0;
		badCost = 0;
		numGood = 0;
		numBad = 0;
		classGood = 0;
		classBad = 0;
	
	}
}
}

