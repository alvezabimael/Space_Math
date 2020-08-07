package com.spw.game_screen;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.spw.Municion_Jugador.armasJugadorType;
import com.spw.Municion_Jugador.disparador;
import com.spw.Timer.Timer;
import com.spw.Explosion.ManejoExplosion;
import com.spw.Sonido.SonidoJuego;

public class armasJugador {

	private Timer timer;
	private ManejoExplosion manejoExplosion;
	public ArrayList<armasJugadorType> armas = new ArrayList<armasJugadorType>();
	private SonidoJuego sonidoDisparo;
	
	public armasJugador(){
		manejoExplosion = new ManejoExplosion();
		timer = new Timer();
		sonidoDisparo = new SonidoJuego("/com/spw/Sonidos/shoot.wav");
	}
	
	public void draw(Graphics2D g){
		
		manejoExplosion.draw(g);
		for(int i = 0; i < armas.size(); i++){
			armas.get(i).draw(g);
		}
	}
	
	public void update(double delta){
		
		manejoExplosion.update(delta);
		for(int i = 0; i < armas.size(); i++){
			armas.get(i).update(delta);
			if(armas.get(i).destruir()) {
				ManejoExplosion.createPixelExplosion(armas.get(i).getxPos(), armas.get(i).getyPos());
				armas.remove(i);
			}
		}
	}
	
	public void dispararBala(double xPos, double yPos, int width, int height){
		if(timer.timerEvent(250)) {
			if (sonidoDisparo.isPlaying()) {
				sonidoDisparo.stop();
			}
			sonidoDisparo.play();
			armas.add(new disparador(xPos + 22, yPos + 15, width, height));
		}
	}

	public void reset() {
		armas.clear();
	}
}
