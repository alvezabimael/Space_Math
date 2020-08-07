package com.spw.game_screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import com.spw.Display.Display;
import com.spw.MathGenerator.multiplicacionRan;

public class Jugador implements KeyListener{

	private final double velocidad = 5.0d;
	private int vida;
	
	private BufferedImage pSprite;
	private Rectangle marco;
	private double xPos, yPos, startXPos, startYPos;
	private int width, height;
	
	
	private boolean left = false, right = false, shoot = false, acierto = false; 
	public boolean enter = false;
	
	
	public armasJugador armas;
	
	public Jugador(double xPos, double yPos, int width, int height){
		this.xPos = xPos;
		this.yPos = yPos;
		this.startXPos = xPos;
		this.startYPos = yPos;
		this.width = width;
		this.height = height;
		this.vida = 3;
		
		marco = new Rectangle((int) xPos,(int) yPos+25, width, height-25);
		
		try{
			URL url = this.getClass().getResource("/com/spw/imagenes/Jugador.png");
			pSprite = ImageIO.read(url);
		}catch(IOException e){};
		
		
		armas = new armasJugador();
	}
	
	public void draw(Graphics2D g){
		g.drawImage(pSprite,(int) xPos,(int) yPos, width, height, null);
		armas.draw(g);
	}
	
	public void update(double delta){
		if(right && !left && xPos < Display.WIDTH-width){
			xPos += velocidad * delta;
			marco.x = (int) xPos;
		}if(!right && left && xPos > 10){
			xPos -= velocidad * delta;
			marco.x = (int) xPos;
		}
		
		armas.update(delta);
		
		if(shoot){
			armas.dispararBala(xPos, yPos, 5, 5);
		}
		if(enter) {
			acierto = multiplicacionRan.esAcierto(GameScreen.INTENTO);
			if(acierto) {
				GameScreen.SCORE +=8;
				GameScreen.INTENTO = 0;
			}
			else {
				GameScreen.INTENTO = 0;
				hit();
			}
			enter = false;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			right = true;
		}else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			left = true;
		}
		
		if (key == KeyEvent.VK_SPACE){
			shoot = true;
		}
		if (key == KeyEvent.VK_ENTER) {
			enter = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			right = false;
		}else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			left = false;
		}
		
		if (key == KeyEvent.VK_SPACE){
			shoot = false;
		}
		if (key == KeyEvent.VK_ENTER) {
			enter = false;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//:)
		
	}
	
	public void hit() {
		setHealth(getVida()-1);
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setHealth(int vida) {
		this.vida = vida;
	}

	public Rectangle getRect() {
		return marco;
	}

	public void reset() {
		vida = 3;
		left = false;
		right = false;
		shoot = false;
		
		xPos = startXPos;
		yPos = startYPos;
		marco.x = (int) xPos;
		marco.y = (int) yPos+25;
		armas.reset();
	}
}
