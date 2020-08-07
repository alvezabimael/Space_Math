package com.spw.Municion_Jugador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import com.spw.Display.Display;
//import com.spw.game_screen.BasicBlocks;

public class disparador extends armasJugadorType{

	 
	private Rectangle bala;
	private final double velocidad = 2.5d;
		
	public disparador(double xPos, double yPos, int width,int height){
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setWidth(width);
		this.setHeight(height);
		
		this.bala = new Rectangle((int) getxPos(),(int) getyPos(), getWidth(), getHeight());
	}
	
	@Override
	public void draw(Graphics2D g) {
		if(bala == null)
			return;
		
		g.setColor(Color.GREEN);
		g.fill(bala);
		
	}

	@Override
	public void update(double delta/*, BasicBlocks blocks*/) {
		if(bala == null)
			return;
		
		this.setyPos(getyPos() - (delta * velocidad));
		bala.y = (int) this.getyPos();
		//colisionPared(blocks);
		fueraDeRango();
	}

	@Override
	public boolean colisionRect(Rectangle rect) {
		if(this.bala == null)
			return false;
		
		if(bala.intersects(rect)){
			this.bala = null;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean colisionPoly(Polygon poly) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean destruir() {
		if(bala == null)
			return true;
		
		return false;
	}

	//@Override
/*	protected void colisionPared(BasicBlocks blocks) {
		for(int i = 0; i < blocks.cuadro.size(); i++){
			if(bala.intersects(blocks.cuadro.get(i))){
				blocks.cuadro.remove(i);
				bala = null;
				return;
			}
		}
		
	}*/

	@Override
	protected void fueraDeRango() {
		if(this.bala == null)
			return;
		
		if(bala.y < 0 || bala.y > Display.HEIGHT || bala.x < 0 || bala.x > Display.WIDTH){
			bala = null;
		}
		
	}

}
