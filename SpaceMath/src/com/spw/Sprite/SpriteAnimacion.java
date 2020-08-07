package com.spw.Sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import com.spw.Timer.Timer;

public class SpriteAnimacion {

	//arraylist con un buffer donde almacenamos las imagenes de animacion
	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private byte spriteActual;
	
	private boolean loop = false;
	private boolean activo = false;
	public boolean destruirDespuesDe = false;
	
	private Timer timer;
	private int velocidadAnimacion;
	private double xPos;
	private double yPos;
	private int width, height;
	private int limit;
	
	public SpriteAnimacion(double xPos, double yPos, int filas, int columnas, int velocidad, String direccion) {
		this.velocidadAnimacion = velocidad;
		this.xPos = xPos;
		this.yPos = yPos;
		
		try{
			URL url = this.getClass().getResource(direccion);
			BufferedImage pSprite = ImageIO.read(url);
			int spriteWidth = pSprite.getWidth() / columnas;
			int spriteHeight = pSprite.getHeight() / filas;
			for(int y = 0; y < filas; y++) {
				for(int x = 0; x < columnas; x++){
					addSprite(pSprite
							, 0 + (x * spriteWidth)
							, 0 + (y * spriteHeight)
							, spriteWidth
							, spriteHeight);
				}
			}
					
			
		}catch(IOException e){};

		timer = new Timer();
		limit = sprites.size() - 1;
	}

	
	public void draw(Graphics2D g) {
		if (estaDestruido())
			return;
		
		g.drawImage(sprites.get(spriteActual), (int) getxPos(), (int) getyPos(), width, height, null);
	}
	
	public void update(double delta) {
		if (estaDestruido())
			return;
		
		if (loop && !activo)
			loopAnimacion();
		if (activo && !loop)
			activarAnimacion();
		
	}

	public void pararAnimacion() {
		activo = false;
		loop = false;
	}
	public void reiniciarSprite() {
		activo = false;
		loop = false;
		spriteActual = 0;
	}
	private void loopAnimacion() {
		if (timer.isTimerReady(velocidadAnimacion) && spriteActual == limit) {
			spriteActual = 0;
			timer.resetTimer();
		}else if (timer.timerEvent(velocidadAnimacion) && spriteActual != limit) {
			spriteActual++;
		} 
	}
	private void activarAnimacion() {
		if (timer.isTimerReady(velocidadAnimacion) && spriteActual != limit && !estaDestruido()) {
			activo = false;
			spriteActual = 0;
		} else if (timer.isTimerReady(velocidadAnimacion) && spriteActual == limit && estaDestruido()) {
			sprites = null;
		}else if (timer.timerEvent(velocidadAnimacion) && spriteActual != limit) {
			spriteActual++;
		}
	}
	public byte getSpriteActual() {
		return spriteActual;
	}

	public void setSpriteActual(byte spriteActual) {
		this.spriteActual = spriteActual;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public boolean estaDestruidoSprite() {
		if(sprites == null)
			return true;
		return false;
	}
	public void addSprite(BufferedImage spriteMap, int xPos, int yPos, int width, int height) {
		sprites.add(spriteMap.getSubimage(xPos, yPos, width, height));
	}
	
	//si destruir = true destruira la clase al finalizar el loop
	public void animacionJugador(boolean activo, boolean destruir) {
		this.activo = activo;
		this.setDestruir(destruir);
		
	}
	public void setDestruirDespuesDe(boolean destruirDespues) {
		this.destruirDespuesDe = destruirDespues;
	}
	public void setPlay(boolean play, boolean destruirDespuesDe) {
		if(loop) {
			loop = false;
		}
		
		this.activo = play;
		this.setDestruirDespuesDe(destruirDespuesDe);
	}

	public double getyPos() {
		
		return yPos;
	}
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public double getxPos() {
		
		return xPos;
	}
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	public boolean estaDestruido() {
		return destruirDespuesDe;
	}
	
	public void setDestruir(boolean destruir) {
		this.destruirDespuesDe = destruir;
	}

	public void setWidth(int i) {
		this.width = i;
		
	}
	public void setHeight(int i) {
		this.height = i;
	}
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		if(limit > 0) {
			this.limit = limit - 1;
		} else {
			this.limit = limit;
		}
	}
	
	public void resetLimit() {
		limit = sprites.size() - 1;
	}

	public boolean isPlay() {
		return activo;
	}
	public int getVelocidadAnimacion() {
		return velocidadAnimacion;
	}

	public void setAnimationSpeed(int velocidad) {
		this.velocidadAnimacion = velocidad;
	}


	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
}
