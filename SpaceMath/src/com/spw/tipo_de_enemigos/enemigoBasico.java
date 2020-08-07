package com.spw.tipo_de_enemigos;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
//import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.spw.Display.Display;
import com.spw.game_screen.GameScreen;
import com.spw.game_screen.Jugador;
import com.spw.Sonido.SonidoJuego;
import com.spw.Sprite.SpriteAnimacion;
//import com.spw.Timer.Timer;

public class enemigoBasico extends tipoEnemigo{

	private double speed = 1.0d; 
	
	private Rectangle rect;
	private SpriteAnimacion enemySprite;
//	private int shootTime;
//	private Timer shootTimer;
	private SonidoJuego explosionSound;
	private String[] URLS = {"/com/spw/imagenes/1.png","/com/spw/imagenes/2.png",
			"/com/spw/imagenes/5.png","/com/spw/imagenes/10.png","/com/spw/imagenes/20.png"};
	int random = ThreadLocalRandom.current().nextInt(0,4);
	
	public enemigoBasico(double xPos, double yPos, int rows, int columns) {
		
		
		 
		enemySprite = new SpriteAnimacion(xPos, yPos, rows, columns, 300, URLS[random]);
		enemySprite.setWidth(25);
		enemySprite.setHeight(25);
		enemySprite.setLimit(2);
		
		this.setRect(new Rectangle((int) enemySprite.getxPos(), (int) enemySprite.getyPos(), enemySprite.getWidth(), enemySprite.getHeight()));
		enemySprite.setLoop(true);
		
//		shootTimer = new Timer();
//		shootTime = new Random().nextInt(12000);
		
		explosionSound = new SonidoJuego("/com/spw/Sonidos/explosion.wav");
	}
	
	@Override
	public void draw(Graphics2D g) {
		enemySprite.draw(g);
		
	}

	@Override
	public void update(double delta, Jugador player) {
		enemySprite.update(delta);
		
		enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
		this.getRect().x = (int) enemySprite.getxPos();
		
		
	}

	@Override
	public void cambioDireccion(double delta) {
		speed *= -1.15d;
		enemySprite.setxPos(enemySprite.getxPos() - (delta *  speed));
		this.getRect().x = (int) enemySprite.getxPos();
		
	}

	@Override
	public boolean animacionMuerte() {
		if(!enemySprite.isPlay())
			return false;
		
		if(enemySprite.estaDestruidoSprite()) {
			if (!explosionSound.isPlaying()) {
				explosionSound.play();
			}
			return true;
		}
		
		return false;
		
	}

	@Override
	public boolean colision(int i, Jugador jugador, ArrayList<tipoEnemigo> enemigo) {
		if(enemySprite.isPlay()) {
			if(enemigo.get(i).animacionMuerte()) {
				enemigo.remove(i);
			}
			return false;
		}
		
		for(int w = 0; w < jugador.armas.armas.size(); w++) {
			if(enemigo != null && jugador.armas.armas.get(w).colisionRect(((enemigoBasico) enemigo.get(i)).getRect())) {
				enemySprite.resetLimit();
				enemySprite.setAnimationSpeed(120);
				enemySprite.setPlay(true, true);
				GameScreen.INTENTO += valorEnemigo(enemigo.get(i).getURL());
				return true;
			}
		}
		
		return false;
	}
	public int valorEnemigo(String url) {
		if(url == URLS[0])
			return 1;
		if(url == URLS[1])
			return 2;
		if(url == URLS[2])
			return 5;
		if(url == URLS[3])
			return 10;
		if(url == URLS[4])
			return 20;
		else {
			return 0;
		}
		
	}

	@Override
	public boolean fueraDeRango() {
		if(rect.x > 0 && rect.x < Display.WIDTH - rect.width)
			return false;
		return true;
	}
	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	public String getURL() {
		return URLS[random];
	}

}


