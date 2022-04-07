package com.simulation.controller;

import java.util.Random;

public class ExcessV2 {
public static void main(String[] args) {
	
	
	//Initialization parameters
	int x = 0;					//Number of request seen in a window (s_x)
	int numGood = 100;			//Truly Good
	int numBad = 900;			//Truly Bad
	int classGood = 0;			//Classified good request
	int classBad = 0;			//Classified good request
	float e = (float) 0.1;      //Epsilon of ML black-box
	Random rand = new Random(); //Random number generator
	float excess = 0; 			//Excess = ClassGood - e*x
	double total = 0; 			//Window ending criteria total = 9*(Math.sqrt(x*Math.log(y)))
	float goodCost = 0;			//Good Cost
	float badCost = 0;			//Bad Cost
	
	
	for(int y = 1; y<numGood+numBad;y++) {
		//Incoming new request, increment x by 1
		x+=1;
		
		//Classifier
		int verdict = rand.nextInt(2)+1;
		//If probability = 1, then good request. If probability = 2, then bad request.
		if(verdict==1) {
			classGood++;
			goodCost+=Math.sqrt(x);
		}
		else {
			badCost+=Math.sqrt(x);
			classBad++;
		}
		
		excess = classGood - (e*x);
		total = Math.sqrt(x*Math.log(y));
		
		//Check if window ends
		if(excess>total) {
			
			System.out.println("~~~~~~~~~~Ending window~~~~~~~~~~~~~");
			System.out.println("Total request seen: "+y);				
			System.out.println("Request seen in a window: "+x);				
			System.out.println("ClassGood: "+classGood);
			System.out.println("ClassBad: "+classBad);
			
			//Reset the parameters for next window
			x = 0; 
			classGood = 0;
			excess = 0;
			total = 0;
		}
		
	}
	
	//Sometimes the last window never closes as we don't see anymore request
	if(excess<=total)
		System.out.println("#### Last Window Open ####");
	
	System.out.println("==== Execution completed ====");
	System.out.println("Request classified as good: "+(numGood+numBad-classBad));
	System.out.println("Request classified as bad: "+classBad);
	System.out.println("Good cost: "+goodCost+" Bad cost: "+(badCost*numGood));
	
}
}
