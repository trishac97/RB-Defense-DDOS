package com.simulation.controller;

import java.util.Random;

public class SanityCheck{
public static void main(String[] args) {
	
	
	//Initialization parameters
	int x = 0;					//Number of request seen in a window (s_x)
	int numGood = 1;			//Truly Good
	int numBad = 49;				//Truly Bad
	int classGood = 0;			//Classified good request
	int classBad = 0;			//Classified good request
	float e = (float) 0.1;      //Epsilon of ML black-box
	Random rand = new Random(); //Random number generator
	float excess = 0; 			//Excess = ClassGood - e*x
	double total = 0; 			//Window ending criteria total = 9*(Math.sqrt(x*Math.log(y)))
	float goodCost = 0;			//Good Cost
	float badCost = 0;			//Bad Cost
	int window = 0;
	int y = 0;
	
	while(window<10) {
		for(int i=1;i<=numBad;i++) {
			badCost+=Math.sqrt(i);
		}

		for(int i=numBad+1;i<=numBad+numGood;i++) {
			goodCost+=Math.sqrt(i);
			System.out.println();
		}
		window++;
		System.out.println(goodCost+" "+badCost);
		
	}
	

}
}


