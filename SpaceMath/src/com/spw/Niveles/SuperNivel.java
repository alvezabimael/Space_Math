package com.spw.Niveles;

import java.awt.Graphics2D;


public interface SuperNivel {
	
	void draw(Graphics2D g);
	void update(double delta);
	void esCambioDireccion(double delta);
	void cambioDireccionEnemigos(double delta);
	
	boolean esGameOver();
	boolean esCompletado();
	
	void destruir();
	void reset();
}
