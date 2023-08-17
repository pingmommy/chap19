package com.example;

import util.Color;

public class RandomExample {
	
	int line;
	int column;
	Color fg;
	Color bg;
	char ch;
	
	public RandomExample() {
		line = (int) (Math.random()*20+1);
		column = (int) (Math.random()*40+1);
		
		do {
		//fg = (int) (Math.random()*8+30);
		//bg = (int) (Math.random()*8+40);
		  fg = Color.values()[(int)(Math.random()*8)];
		  bg = Color.values()[(int)(Math.random()*8)];		  		  
		}while(fg==bg);
		
		ch = (char) (Math.random()*26+'A');
	}
	
	public static void main(String[] args) {
		RandomExample r = new RandomExample();
		
		System.out.printf("%d %d %d %d %c",r.line , r.column,r.fg,r.bg,r.ch);
	}

}
