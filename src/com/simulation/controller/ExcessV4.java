package com.simulation.controller;

import java.util.Random;

public class ExcessV4 {
	public static void main(String[] args) {
		int numGood = 4;
		int numBad = 6;
		
		int classGood = 0;
		int classBad = 0;
		
		Random rand = new Random();
		
			while(numBad>0) {
				numBad--;
				int verdict = rand.nextInt(2)+1;
				if(verdict == 1)
					classGood++;
				else
					classBad++;
			}
			while(numGood>0) {
				numGood--;
				int verdict = rand.nextInt(2)+1;
				if(verdict == 1)
					classGood++;
				else
					classBad++;
			}
			
			System.out.println(classGood+" "+classBad);
		
	}

}
