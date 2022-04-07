package com.simulation.controller;
import java.util.Random;

public class Excess {
public static void main(String[] args) {
	
	//Initialization parameters
	int x = 1;                  //Number of request seen in a window (s_x)
	int y = 1;                  //Total request seen from the beginning of time
	float e = (float) 0.2;      //Epsilon of ML blackbox
	Random rand = new Random(); //Random number generator
	float excess = 0; 			//Excess = ClassGood - e*x
	double total = 0; 			//Window ending criteria total = 9*(Math.sqrt(x*Math.log(y)))
	int classGood = 0;			//Classified good request
	int window = 0;				//Window count
	float badCost = 0;			//Bad Cost
	float goodCost = 0;			//Good Cost
	
	//Simulating for 10 windows
	
	while(window<10) {
		while(excess <= total) {
			//Incoming new request, increment x by 1
			x+=1;
			//Incoming new request, increment total request seen y by 1
			y+=1;
			//If probability = 1, then good request. If probability = 2, then bad request.
			int probability = rand.nextInt(10)+1;
			//If good request, then add it to classGood
			if(probability > 5) {
				classGood+=1;
			}
				
			excess = classGood - (e*x);
			total = 9*Math.sqrt(x*Math.log(y));
		}
		//excess>total
		//System.out.println("----------Ending window "+window+"--------------------------");
		//Ended window good and bad cost(Worst case)
		for(int i=1;i<=x-classGood; i++) {
			badCost+=Math.sqrt(i);
		}
		
		for(int i=x-classGood+1;i<=x; i++) {
			goodCost+=Math.sqrt(i);
		}
		
		System.out.println(goodCost+" "+(badCost*x));
		
		//Preparing parameters for next window
		x = 0; 
		classGood = 0;
		excess = 0;
		total = 0;
		window++;
}
}
}

//


