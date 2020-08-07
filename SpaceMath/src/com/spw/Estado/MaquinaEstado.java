package com.spw.Estado;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.spw.game_screen.GameScreen;
import com.spw.Pantalla_Menu.PantallaMenu;

public class MaquinaEstado {
	
	private ArrayList<SuperMaquinaEstado> estado = new ArrayList<SuperMaquinaEstado>();
	private Canvas canvas;
	private byte seleccion = 0;
	
	
	
	public MaquinaEstado(Canvas canvas) {
		SuperMaquinaEstado juego = new GameScreen(this);
		SuperMaquinaEstado menu = new PantallaMenu(this);
		estado.add(menu);
		estado.add(juego);
		
		this.canvas = canvas;
	}
	public void draw(Graphics2D g){
		estado.get(seleccion).draw(g);
	}
	
	public void update(double delta){
		estado.get(seleccion).update(delta);
	}
	
	public void setEstado(byte i){
		for(int r = 0; r < canvas.getKeyListeners().length; r++)
			canvas.removeKeyListener(canvas.getKeyListeners()[r]);
		seleccion = i;
		estado.get(seleccion).init(canvas);
	}

	public byte getEstado() {
		return seleccion;
	}
	
}
