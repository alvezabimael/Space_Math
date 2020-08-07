package com.spw.MathGenerator;



public class multiplicacionRan {

	static int numA;
	static int numB;
	
		
	public multiplicacionRan() {
		numA = (int)(Math.random()*((10-1)+1))+0;
		numB = (int)(Math.random()*((10-1)+1))+0;
	}
	
	public static boolean esAcierto(int intento) {
		if(intento == numA*numB) {
			updateMulti();
			return true;
		}
		updateMulti();
		return false;
	}
	public static void updateMulti() {
		numA = (int)(Math.random()*((9-0)+1))+0;
		numB = (int)(Math.random()*((9-0)+1))+0;
	}
	public int getNumA(){
		return numA;
	}
	public int getNumB(){
		return numB;
	}
}
