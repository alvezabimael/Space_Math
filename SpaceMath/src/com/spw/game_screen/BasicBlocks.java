package com.spw.game_screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class BasicBlocks {

	public ArrayList<Rectangle> cuadro = new ArrayList<Rectangle>();
	
	public BasicBlocks(){
		basicBlocks(75, 450);
		basicBlocks(275, 450);
		basicBlocks(475, 450);
		basicBlocks(675, 450);
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.RED);
		for(int i = 0; i < cuadro.size(); i++){
			g.fill(cuadro.get(i));
		}
	}
	
	public void basicBlocks(int xPos, int yPos){
		int ancho = 3;
		int x = 0;
		int y = 0;
		
		for(int i = 0; i < 13; i++){
			if((14 + (i * 2) + ancho < 22 + ancho)){
				row(14 + (i * 2) + ancho, xPos - (i * 3), yPos + (i * 3));
				x = (i * 3) + 3;
			}else{
				row(22 + ancho, xPos - x, yPos + (i * 3));
				y = (i * 3);
			}
		}
		
		//Para el lado izquierdo
		for(int i = 0; i < 5; i++){
			row(8 + ancho - i, xPos - x, (yPos + y) + (i * 3));
		}
		
		//Para el lado derecho
		for(int i = 0; i < 5; i++){
			row(8 + ancho - i, (xPos - x + (14 * 3)) + (i * 3), (yPos + y) + (i * 3));
		}
	}
	
	public void row(int rows, int xPos, int yPos){
		for(int i = 0; i < rows; i++){
			Rectangle brick = new Rectangle(xPos + (i * 3), yPos, 3, 3);
			cuadro.add(brick);
		}
	}
	
	public void reset(){
		cuadro.clear();
		
		basicBlocks(75, 450);
		basicBlocks(275, 450);
		basicBlocks(475, 450);
		basicBlocks(675, 450);
	}
}

