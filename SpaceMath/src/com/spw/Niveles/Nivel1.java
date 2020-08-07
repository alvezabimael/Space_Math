package com.spw.Niveles;

import java.awt.Graphics2D;
import java.util.ArrayList;
import com.spw.game_screen.Jugador;
import com.spw.tipo_de_enemigos.enemigoBasico;
import com.spw.tipo_de_enemigos.tipoEnemigo;
import com.spw.Sonido.SonidoJuego;

public class Nivel1 implements SuperNivel{
	
	private Jugador jugador;
	private ArrayList<tipoEnemigo> enemigos = new ArrayList<tipoEnemigo>();
	
	
	private SonidoJuego beep, boop;
	private boolean beepboop;
	
	public Nivel1(Jugador jugador){
		this.jugador = jugador;
		addEnemies();
		
		beep = new SonidoJuego("/com/spw/Sonidos/beep.wav");
		boop = new SonidoJuego("/com/spw/Sonidos/boop.wav");
	}
	
	@Override
	public void draw(Graphics2D g) {
		if(enemigos == null)
			return;
		
		for(int i = 0; i < enemigos.size(); i++){
			enemigos.get(i).draw(g);
		}
	}

	@Override
	public void update(double delta) {
		if(enemigos == null)
			return;
		
		for(int i = 0; i < enemigos.size(); i++){
			enemigos.get(i).update(delta, jugador);
		}
		for(int i = 0; i < enemigos.size(); i++){
			enemigos.get(i).colision(i, jugador,enemigos);
		}
		esCambioDireccion(delta);
		
	}

	@Override
	public void cambioDireccionEnemigos(double delta) {
		for(int i = 0; i < enemigos.size(); i++){
			enemigos.get(i).cambioDireccion(delta);
		}
		if (beepboop) {
			beepboop = false;
			boop.play();
		} else {
			beepboop = true;
			beep.play();
		}
	}
	
	@Override
	public void esCambioDireccion(double delta) {
		if(enemigos == null)
			return;
		
		for(int i = 0; i < enemigos.size(); i++)
		{
			if(enemigos.get(i).fueraDeRango()){
				cambioDireccionEnemigos(delta);
			}
		}
	}


	@Override
	public boolean esGameOver() {
		return jugador.getVida() <= 0;
	}

	@Override
	public void destruir() {
		
	}

	@Override
	public void reset() {
		jugador.reset();
		enemigos.clear();
		addEnemies();
		
		
	}
	
	//añade enemigos
	public void addEnemies() {
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < 10; x++){
				tipoEnemigo e = new enemigoBasico(150 + (x * 40), 25 + (y * 40), 1 , 3);
				enemigos.add(e);
			}
		}
	}

	@Override
	public boolean esCompletado() {
		
		return enemigos.isEmpty();
	}
}
