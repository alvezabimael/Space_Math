package com.spw.Municion_Jugador;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public abstract class armasJugadorType {

	protected double xPos, yPos;
	protected int width, height;
	
	public abstract void draw(Graphics2D g);
	public abstract void update(double delta);
	public abstract boolean colisionRect(Rectangle rect);
	public abstract boolean colisionPoly(Polygon poly);
	public abstract boolean destruir();
	
	//protected abstract void colisionPared(BasicBlocks blocks);
	protected abstract void fueraDeRango();
	
	public double getxPos() {
		return xPos;
	}
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	public double getyPos() {
		return yPos;
	}
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
