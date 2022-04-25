package com.simulation.controller;

import java.util.Random;

public class ExcessClosed {

	public static void main(String[] args) {
		
		Random rand = new Random();
	
		double algoCost = 0; //Algorithm cost
		double badCost = 0;	 //Bad cost
		int numBad = 0;		 //Number of truly bad jobs
		int e = 10;			 //Error ML
		int y = 0;			 //Total job in all windows combined
		int n = 10;			 //Number of bad jobs pushed in each window. Window i has n bad jobs, window i+1 has n*10 bad jobs and so on.
		int numGood = 0;
		
		while(y<10000000) {  //Execute until we see total of 10000000 jobs
			int classGood = 0;
			int classBad = 0;
			int verdict = 0;
			
			//### PUSH BAD JOBS
			for(numBad = 1;numBad<n;numBad++) {  //Push bad jobs first in the window
			verdict =  rand.nextInt(e)+1;
			if(verdict==1)						//1/10 time a bad job is misclassified 
				classGood++;
			badCost += Math.sqrt(numBad);		//Bad cost calculate
			}

			int x = numBad;						//Update x
			n = n+100;							//Increment number of bad jobs for next round
			y += numBad;						//Update y

			float total = (float) (5*Math.sqrt(x*Math.log(y)));
			float excess = (float) (classGood - 0.1*x);
			
			//### PUSH GOOD JOBS
			while(excess <= total) {
				x+=1;
				y+=1;
				numGood++;
				verdict =  rand.nextInt(10)+1;
				if(verdict==1)				//1/10 time a bad job is misclassified
					classBad++;
				else 
					classGood++;
				algoCost +=  Math.sqrt(x);
				total = (float) Math.sqrt(x*Math.log(y));
				excess = (float) (classGood - (e/100)*x);
			}
			//Add provisioning cost
			algoCost+=x;
		
			System.out.println(algoCost+" "+(badCost));
			System.out.println(Math.sqrt(badCost*numGood)+" "+(badCost));

	}
	}

}
