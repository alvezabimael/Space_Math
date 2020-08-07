package com.spw.tipo_de_enemigos;

import java.awt.Graphics2D;
import java.util.ArrayList;
import com.spw.game_screen.Jugador;


public abstract class tipoEnemigo {
	

	public abstract void draw(Graphics2D g);
	public abstract void update(double delta, Jugador player);
	public abstract void cambioDireccion(double delta);
	public abstract String getURL();
	public abstract boolean animacionMuerte();
	public abstract boolean colision(int i, Jugador jugador, ArrayList<tipoEnemigo> enemigo);
	public abstract boolean fueraDeRango();
	

}
